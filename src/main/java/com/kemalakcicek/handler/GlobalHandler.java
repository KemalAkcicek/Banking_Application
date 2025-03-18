package com.kemalakcicek.handler;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.kemalakcicek.exception.ApiError;
import com.kemalakcicek.exception.BasePackage;

@ControllerAdvice
public class GlobalHandler {

	// Validasyondaki hataları yakalıyoruz
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handlerMethodargumentNotValidException(MethodArgumentNotValidException ex) {

		Map<String, List<String>> errorsMap = new HashMap<>();

		for (ObjectError objError : ex.getBindingResult().getAllErrors()) {

			String filedName = ((FieldError) objError).getField();

			if (errorsMap.containsKey(filedName)) {

				errorsMap.put(filedName, addMapValue(errorsMap.get(filedName), objError.getDefaultMessage()));

			} else {
				errorsMap.put(filedName, addMapValue(new ArrayList<>(), objError.getDefaultMessage()));

			}

		}

		return ResponseEntity.badRequest().body(createApiError(errorsMap));
	}

	private List<String> addMapValue(List<String> list, String newValue) {

		list.add(newValue);

		return list;
	}

	private <T> ApiError<T> createApiError(T errors) {

		ApiError<T> apiError = new ApiError<>();

		apiError.setId(UUID.randomUUID().toString());
		apiError.setErrorDateTime(new Date());
		apiError.setErrors(errors);

		return apiError;
	}

	// Kendi oluşturduğumuz exception hataları yakalıyoruz

	@ExceptionHandler(value = { BasePackage.class })
	public ResponseEntity<ApiErrorException> handlerBaseException(BasePackage exception, WebRequest webRequest) {

		return ResponseEntity.badRequest().body(createError(exception.getMessage(), webRequest));
	}

	public <E> ApiErrorException<E> createError(E message, WebRequest webRequest) {

		ApiErrorException<E> apiError = new ApiErrorException<>();

		apiError.setStatus(HttpStatus.BAD_REQUEST.value());

		Exception<E> exception = new Exception<>();
		exception.setCreateTime(new Date());
		exception.setHostName(getHostName());
		exception.setMessage(message);
		exception.setPath(webRequest.getDescription(false).substring(4));

		apiError.setException(exception);

		return apiError;
	}

	private String getHostName() {

		try {
			return Inet4Address.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			System.out.println("Hata oluştu" + e.getMessage());
		}
		return null;

	}

}
