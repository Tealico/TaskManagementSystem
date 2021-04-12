package com.task.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.dto.LoginRequestDto;
import com.task.dto.LoginResponseDto;
import com.task.dto.UserDto;
import com.task.dto.UserDtoForCreate;
import com.task.security.JwtService;
import com.task.security.UserPrincipal;
import com.task.service.UserService;
@RestController
public class AuthenticationController {
	
	private final JwtService jwtProvider;
	private final AuthenticationManager authenticationManager;
	 
	@Autowired
	UserService userService;
	
	@Autowired
    public AuthenticationController(JwtService jwtProvider, AuthenticationManager authenticationManager) {
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
	}
	
	@PostMapping("/register")
    public UserDto createUser(@RequestBody UserDtoForCreate user){
		return userService.createUser(user);
    }
	
	@PostMapping("/login")
    public LoginResponseDto authenticate(@RequestBody LoginRequestDto request) {
        Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication authenticationResult = authenticationManager.authenticate(authenticationRequest);
        LoginResponseDto response = createResponse(authenticationResult);
        System.out.println(response);
        return response;
    }
 
 	private LoginResponseDto createResponse(Authentication authentication) {
    	UserPrincipal agilityUserDetail = (UserPrincipal) authentication.getPrincipal();
    	LoginResponseDto response = new LoginResponseDto();
        response.setUsername(agilityUserDetail.getUsername());
        String rolesAsStringList = agilityUserDetail.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()).get(0);
        response.setRole(rolesAsStringList);
        String generatedToken = jwtProvider.issueToken(agilityUserDetail);
        response.setToken(generatedToken);
        return response;
 	}
}
