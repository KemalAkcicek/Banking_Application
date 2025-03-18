package com.kemalakcicek.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kemalakcicek.controller.IAuthController;
import com.kemalakcicek.dto.DtoUserRegister;
import com.kemalakcicek.dto.DtoUserRegisterGet;
import com.kemalakcicek.jwt.AuthRequest;
import com.kemalakcicek.jwt.AuthResponse;
import com.kemalakcicek.jwt.RefreshTokenRequest;
import com.kemalakcicek.model.RootEntity;
import com.kemalakcicek.service.IAuthService;

import jakarta.validation.Valid;

@RestController
public class AuthControllerImpl extends RestBaseController implements IAuthController {

	@Autowired
	private IAuthService iAuthService;

	@Override
	@PostMapping(path = "/register")
	public RootEntity<DtoUserRegisterGet> register(@Valid @RequestBody DtoUserRegister request) {

		return ok(iAuthService.register(request));
	}

	@Override
	@PostMapping(path = "/authenticate")
	public AuthResponse authentication(@RequestBody AuthRequest request) {

		return iAuthService.authentication(request);
	}

	@Override
	@PostMapping(path = "/refreshtoken")
	public AuthResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {

		return iAuthService.refreshToken(refreshTokenRequest);
	}

}
