package com.lakshay.accountingManagementRest.controller;

import com.lakshay.accountingManagementRest.dto.AuthenticationResponse;
import com.lakshay.accountingManagementRest.dto.LoginRequest;
import com.lakshay.accountingManagementRest.dto.RegisterRequest;
import com.lakshay.accountingManagementRest.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
	
	private final AuthService authService;

	
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
		authService.signup(registerRequest);
		return new ResponseEntity<>("User Registration successful", OK);
	}

	
	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}
}
