package com.ss.service;

import org.springframework.stereotype.Service;

import com.ss.response.JwtRequest;
import com.ss.response.JwtResponse;

@Service
public interface TokenService {

	JwtResponse generateToken(JwtRequest jwtRequest) throws Exception;

}
