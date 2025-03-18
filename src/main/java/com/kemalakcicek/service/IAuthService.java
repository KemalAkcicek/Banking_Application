package com.kemalakcicek.service;

import com.kemalakcicek.dto.DtoUserRegister;
import com.kemalakcicek.dto.DtoUserRegisterGet;
import com.kemalakcicek.jwt.AuthRequest;
import com.kemalakcicek.jwt.AuthResponse;
import com.kemalakcicek.jwt.RefreshTokenRequest;

public interface IAuthService {

	public DtoUserRegisterGet register(DtoUserRegister register);

	public AuthResponse authentication(AuthRequest request);

	public AuthResponse refreshToken(RefreshTokenRequest refreshToken);

}
