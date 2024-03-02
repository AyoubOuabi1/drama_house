package com.drama.house.services;

import com.drama.house.dtos.UserDTO;
import com.drama.house.dtos.auth.AuthenticateDto;
import com.drama.house.dtos.auth.RegisterDto;
import com.drama.house.dtos.auth.ResponseDto;
import com.drama.house.entities.User;
import jakarta.validation.ValidationException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO saveUser(UserDTO userDTO);
    void deleteUser(Long id);
    Optional<User> findByEmail(String email);

    ResponseDto authenticate(AuthenticateDto authenticateDto);
    ResponseDto register(RegisterDto registerDto) throws ValidationException;
}

