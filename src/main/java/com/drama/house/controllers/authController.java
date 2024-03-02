package com.drama.house.controllers;


import com.drama.house.dtos.auth.AuthenticateDto;
import com.drama.house.dtos.auth.RegisterDto;
import com.drama.house.dtos.auth.ResponseDto;
import com.drama.house.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class authController {

    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<ResponseDto> register(@Valid @RequestBody RegisterDto registerDto) throws ValidationException {
        return ResponseEntity.ok(userService.register(registerDto));
    }
    @PostMapping("login")
    public ResponseEntity<ResponseDto> authenticate(@Valid @RequestBody AuthenticateDto authenticateDto){
        return ResponseEntity.ok(userService.authenticate(authenticateDto));
    }


}
