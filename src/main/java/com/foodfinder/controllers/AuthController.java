package com.foodfinder.controllers;

import com.foodfinder.dtos.ApiResponseDto;
import com.foodfinder.dtos.SignInRequestDto;
import com.foodfinder.dtos.SignUpRequestDto;
import com.foodfinder.exceptions.RoleNotFoundException;
import com.foodfinder.exceptions.UserAlreadyExistsException;
import com.foodfinder.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto<?>> registerUser(@Valid SignUpRequestDto signUpRequestDto)
            throws UserAlreadyExistsException, RoleNotFoundException {
        return authService.signUpUser(signUpRequestDto);
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponseDto<?>> signInUser(@RequestBody @Valid SignInRequestDto signInRequestDto){
        return authService.signInUser(signInRequestDto);
    }

}
