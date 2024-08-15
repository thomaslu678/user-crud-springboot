package com.foodfinder.controllers;

import com.foodfinder.dtos.ApiResponseDto;
import com.foodfinder.dtos.SignUpRequestDto;
import com.foodfinder.exceptions.UserAlreadyExistsException;
import com.foodfinder.exceptions.UserNotFoundException;
import com.foodfinder.exceptions.UserServiceLogicException;
import com.foodfinder.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping("/new")
    public ResponseEntity<ApiResponseDto<?>> registerUser(@Valid @RequestBody SignUpRequestDto userDetailsRequestDto)
            throws UserAlreadyExistsException, UserServiceLogicException {
        return userService.registerUser(userDetailsRequestDto);
    }

    @GetMapping("/get/all")
    public ResponseEntity<ApiResponseDto<?>> getAllUsers()
            throws UserServiceLogicException {
        return userService.getAllUsers();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseDto<?>> updateUser(@Valid @RequestBody SignUpRequestDto userDetailsRequestDto,
                                                        @PathVariable int id)
            throws UserNotFoundException, UserServiceLogicException {
        return userService.updateUser(userDetailsRequestDto, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDto<?>> deleteUser(@PathVariable int id)
            throws UserNotFoundException, UserServiceLogicException {
        return userService.deleteUser(id);
    }

}
