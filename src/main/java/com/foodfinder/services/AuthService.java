package com.foodfinder.services;

import com.foodfinder.dtos.ApiResponseDto;
import com.foodfinder.dtos.SignUpRequestDto;
import com.foodfinder.exceptions.RoleNotFoundException;
import com.foodfinder.exceptions.UserAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    ResponseEntity<ApiResponseDto<?>> signUpUser(SignUpRequestDto signUpRequestDto) throws UserAlreadyExistsException, RoleNotFoundException;
}
