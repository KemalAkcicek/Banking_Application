package com.kemalakcicek.exception;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Date;

import org.springframework.http.HttpStatus;

import com.kemalakcicek.handler.ApiErrorException;
import com.kemalakcicek.handler.Exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SecurityExceptionHandler {

	public static <E> ApiErrorException<E> createError(E message, HttpServletRequest httpServletRequest) {

		ApiErrorException<E> apiError = new ApiErrorException<>();

		apiError.setStatus(HttpStatus.BAD_REQUEST.value());

		Exception<E> exception = new Exception<>();
		exception.setCreateTime(new Date());
		exception.setHostName(getHostName());
		exception.setMessage(message);
		exception.setPath(httpServletRequest.getRequestURL().toString());

		apiError.setException(exception);

		return apiError;
	}

	private static String getHostName() {

		try {
			return Inet4Address.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			System.out.println("Hata oluştu" + e.getMessage());
		}
		return null;

	}

	public static void handleException(HttpServletResponse response, BasePackage basePackage,
			HttpServletRequest httpServletRequest) throws IOException {

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);// 401 hata kodu
		response.setContentType("application/json");// json formatında donsün diye
		response.setCharacterEncoding("UTF-8");

		ApiErrorException<String> error = createError(basePackage.getMessage(), httpServletRequest);

		String jsonResponse = error.toString();

		response.getWriter().write(jsonResponse);

	}
}
