package com.kemalakcicek.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RootEntity<T> {

	private boolean result;

	private String errorMessage;

	private T data;

	public static <T> RootEntity<T> ok(T data) {

		RootEntity<T> rootEntity = new RootEntity<>();
		rootEntity.setData(data);
		rootEntity.setErrorMessage(null);
		rootEntity.setResult(true);

		return rootEntity;
	}

	public static <T> RootEntity<T> error(String message) {
		
		RootEntity<T> rootEntity = new RootEntity<>();
		rootEntity.setData(null);
		rootEntity.setResult(false);
		rootEntity.setErrorMessage(message);

		return rootEntity;
	}

}
