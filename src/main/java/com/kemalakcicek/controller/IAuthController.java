package com.kemalakcicek.controller;

import com.kemalakcicek.dto.DtoUserRegister;
import com.kemalakcicek.dto.DtoUserRegisterGet;
import com.kemalakcicek.jwt.AuthRequest;
import com.kemalakcicek.jwt.AuthResponse;
import com.kemalakcicek.jwt.RefreshTokenRequest;
import com.kemalakcicek.model.RootEntity;

public interface IAuthController {

	public RootEntity<DtoUserRegisterGet> register(DtoUserRegister request);

	public AuthResponse authentication(AuthRequest request);

	public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
