package com.drama.house.services.imp;

import com.drama.house.config.JwtService;
import com.drama.house.dtos.UserDTO;
import com.drama.house.dtos.auth.AuthenticateDto;
import com.drama.house.dtos.auth.RegisterDto;
import com.drama.house.dtos.auth.ResponseDto;
import com.drama.house.entities.PermissionEntity;
import com.drama.house.entities.RoleEntity;
import com.drama.house.entities.User;
import com.drama.house.exception.CustomException;
import com.drama.house.repositories.UserRepository;
import com.drama.house.services.RoleService;
import com.drama.house.services.UserService;
import jakarta.validation.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           JwtService jwtService, AuthenticationManager authenticationManager,
                           RoleService roleService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        users.remove(getCurrentUser());
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        try {
            User user = userRepository.findById(id).get();
            return convertToDTO(user);
        }catch (CustomException e){
            throw new CustomException("User not found");
        }

    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        user = userRepository.save(user);
        return convertToDTO(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public ResponseDto register(RegisterDto registerDto) throws ValidationException {
        Optional<User> existingUser = this.userRepository.findByEmail(registerDto.getEmail());
        if(existingUser.isPresent()) throw new ValidationException("This email already exists !");
        RoleEntity userRole = roleService.getRoleByName("USER");

        User user = User.builder()
                .username(registerDto.getUsername())
                .email(registerDto.getEmail())
                .roles(Collections.singleton(userRole))
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .profilePicture("https://dramahousee.s3.eu-west-3.amazonaws.com/user-avatar.png")
                .build();
        ResponseDto responseDto = mapUserToResponseDTO(this.userRepository.save(user));
        responseDto.setAccessToken(jwtService.generateToken(user));
        return responseDto;
    }
    public ResponseDto authenticate(AuthenticateDto authenticateDto){
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticateDto.getEmail(), authenticateDto.getPassword())
        );
        User user = this.findByEmail(authenticateDto.getEmail()).get();
        ResponseDto responseDto = mapUserToResponseDTO(user);
        responseDto.setAccessToken(jwtService.generateToken(user));

        return responseDto;
    }

    private UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public ResponseDto mapUserToResponseDTO(User user) {

        return new ResponseDto(
                user.getUsername(),
                user.getEmail(),
                null,
                user.getRoles().stream().findFirst().map(RoleEntity::getName).orElse(null),
                user.getProfilePicture())   ;
    }
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            return userRepository.findByEmail(username).get();
        }

        return null;
    }

    @Override
    public UserDTO updateUser(UserDTO updatedUser) {
        String currentUserEmail = getCurrentUser().getEmail();
        User currentUser = userRepository.findByEmail(currentUserEmail).orElse(null);

        if (updatedUser.getEmail() != null && !userRepository.findByEmail(updatedUser.getEmail()).equals(currentUser)) {
            throw new IllegalArgumentException("Email is already in use");
        }
        assert currentUser != null;
        currentUser.setUsername(updatedUser.getUsername() != null ? updatedUser.getUsername() : currentUser.getUsername());
        currentUser.setEmail(updatedUser.getEmail() != null ? updatedUser.getEmail() : currentUser.getEmail());
        currentUser.setPassword(updatedUser.getPassword() != null ? passwordEncoder.encode(updatedUser.getPassword()) : currentUser.getPassword());
        currentUser.setId(currentUser.getId());
        userRepository.save(currentUser);
        return convertToDTO(currentUser);

    }
}
