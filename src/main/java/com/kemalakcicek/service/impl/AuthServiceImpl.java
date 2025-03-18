package com.kemalakcicek.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kemalakcicek.dto.DtoRole;
import com.kemalakcicek.dto.DtoUserRegister;
import com.kemalakcicek.dto.DtoUserRegisterGet;
import com.kemalakcicek.exception.BasePackage;
import com.kemalakcicek.exception.ErrorMessage;
import com.kemalakcicek.exception.MessageType;
import com.kemalakcicek.jwt.AuthRequest;
import com.kemalakcicek.jwt.AuthResponse;
import com.kemalakcicek.jwt.JwtService;
import com.kemalakcicek.jwt.RefreshTokenRequest;
import com.kemalakcicek.model.RefreshToken;
import com.kemalakcicek.model.Role;
import com.kemalakcicek.model.User;
import com.kemalakcicek.repository.RefreshTokenRepository;
import com.kemalakcicek.repository.UserRepository;
import com.kemalakcicek.service.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	public boolean isRefreshTokenExpired(Date date) {

		return new Date().before(date);
	}

	public RefreshToken createRefreshToken(User user) {

		RefreshToken refreshToken = new RefreshToken();

		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 7)));
		refreshToken.setUser(user);

		System.out.println("time:" + new Date());
		System.out.println("refresh:" + refreshToken.getExpiredDate());

		return refreshToken;
	}

	@Override
	public DtoUserRegisterGet register(DtoUserRegister register) {

		User user = new User();

		BeanUtils.copyProperties(register, user);

		user.setPassword(passwordEncoder.encode(register.getPassword()));

		// Role için
		DtoRole dtorole = register.getRole();
		Role role = new Role();
		BeanUtils.copyProperties(dtorole, role);

		user.setRole(role);

		User savedUser = userRepository.save(user);

		DtoUserRegisterGet userGet = new DtoUserRegisterGet();

		userGet.setUsername(savedUser.getEMail());
		userGet.setPassword(savedUser.getPassword());

		return userGet;
	}

	@Override
	public AuthResponse authentication(AuthRequest request) {

		try {
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(request.getUsername(),
					request.getPassword());

			authenticationProvider.authenticate(auth);

			Optional<User> optional = userRepository.findByeMail(request.getUsername());

			String accessToken = jwtService.generateToken(optional.get());

			RefreshToken refreshToken = createRefreshToken(optional.get());

			refreshTokenRepository.save(refreshToken);

			return new AuthResponse(accessToken, refreshToken.getRefreshToken());

		} catch (Exception e) {
			throw new BasePackage(new ErrorMessage(MessageType.INPUT_VALİDATİON_ERROR, request.getUsername()));
		}
	}

	@Override
	public AuthResponse refreshToken(RefreshTokenRequest refreshToken) {

		Optional<RefreshToken> optional = refreshTokenRepository.findByRefreshToken(refreshToken.getRefreshToken());

		if (optional.isEmpty()) {

			throw new BasePackage(new ErrorMessage(MessageType.NO_RECORD_EXİST, refreshToken.getRefreshToken()));

		}

		RefreshToken dbRefreshToken = optional.get();

		if (!isRefreshTokenExpired(dbRefreshToken.getExpiredDate())) {

			throw new BasePackage(new ErrorMessage(MessageType.EXPİRED_DATE, refreshToken.getRefreshToken()));
		}

		String accessToken = jwtService.generateToken(dbRefreshToken.getUser());

		RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(dbRefreshToken.getUser()));

		return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
	}

}
