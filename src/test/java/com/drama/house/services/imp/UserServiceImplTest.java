package com.drama.house.services.imp;

import com.drama.house.config.JwtService;
import com.drama.house.dtos.UserDTO;
import com.drama.house.dtos.auth.AuthenticateDto;
import com.drama.house.dtos.auth.RegisterDto;
import com.drama.house.dtos.auth.ResponseDto;
import com.drama.house.entities.RoleEntity;
import com.drama.house.entities.User;
import com.drama.house.exception.CustomException;
import com.drama.house.repositories.UserRepository;
import com.drama.house.services.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
    private JwtService jwtService = Mockito.mock(JwtService.class);
    private AuthenticationManager authenticationManager = Mockito.mock(AuthenticationManager.class);
    private RoleService roleService = Mockito.mock(RoleService.class);
    private ModelMapper modelMapper = Mockito.mock(ModelMapper.class);
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(userRepository, passwordEncoder, jwtService, authenticationManager, roleService, modelMapper);
    }

    @Test
    public void testGetUserById_UserFound() {
        User user = new User();

        user.setUsername("test@test.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("test@test.com");
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        UserDTO returnedUserDTO = userService.getUserById(1L);

        assertEquals("test@test.com", returnedUserDTO.getEmail());
    }

    @Test
    public void testGetUserById_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> userService.getUserById(1L));
    }

    @Test
    public void testRegister_Success() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUsername("testUser");
        registerDto.setPassword("testPassword");
        registerDto.setEmail("test@test.com");

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName("USER");
        when(roleService.getRoleByName("USER")).thenReturn(roleEntity);
        when(userRepository.findByEmail(registerDto.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(registerDto.getPassword())).thenReturn("encodedPassword");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setEmail("test@test.com");
        when(userRepository.save(Mockito.any(User.class))).thenReturn(savedUser);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setEmail("test@test.com");

        ResponseDto actualResponseDto = userService.register(registerDto);

        assertEquals("test@test.com", actualResponseDto.getEmail());
    }

    @Test
    public void testRegister_UserExists() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUsername("testUser");
        registerDto.setPassword("testPassword");
        registerDto.setEmail("test@test.com");

        when(userRepository.findByEmail(registerDto.getEmail())).thenReturn(Optional.of(new User()));

        assertThrows(RuntimeException.class, () -> userService.register(registerDto));
    }

    @Test
    public void testAuthenticate_Success() {
        AuthenticateDto authenticateDto = new AuthenticateDto();
        authenticateDto.setEmail("test@test.com");
        authenticateDto.setPassword("testPassword");

        User user = new User();
        user.setEmail("test@test.com");
        when(userRepository.findByEmail(authenticateDto.getEmail())).thenReturn(Optional.of(user));

        ResponseDto responseDto = new ResponseDto();
        responseDto.setEmail("test@test.com");
        when(modelMapper.map(user, ResponseDto.class)).thenReturn(responseDto);

        ResponseDto actualResponseDto = userService.authenticate(authenticateDto);

        assertEquals("test@test.com", actualResponseDto.getEmail());
    }

    @Test
    public void testAuthenticate_InvalidCredentials() {
        AuthenticateDto authenticateDto = new AuthenticateDto();
        authenticateDto.setEmail("test@test.com");
        authenticateDto.setPassword("invalidPassword");

        when(userRepository.findByEmail(authenticateDto.getEmail())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.authenticate(authenticateDto));
    }
}
