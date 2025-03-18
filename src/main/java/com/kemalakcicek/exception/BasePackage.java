package com.kemalakcicek.exception;

public class BasePackage extends RuntimeException {

	public BasePackage() {

	}

	public BasePackage(ErrorMessage errorMessage) {
		super(errorMessage.prepareErrorMessage());
	}

}
