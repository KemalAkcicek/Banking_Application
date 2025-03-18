package com.kemalakcicek.exception;

import lombok.Getter;

@Getter
public enum MessageType {

	NO_RECORD_EXİST("1001", "Kayıt Bulunamadı"), GENERAL_EXCEPTİON("9999", "Genel bir hata oluşturuldu"),
	EXPİRED_DATE("2002", "Token zaman Doldu"), INPUT_VALİDATİON_ERROR("30003", "Hatalı Giriş"),
	NO_RECORD_LİST("4004", "Liste bulunamadı"), SENDER_ACCOUNT_EXİST("5005", "Sender account cannot be null"),
	RECEİVE_ACCOUNT_EXİST("5005", "Receive account cannot be null"), NO_EXİSTS_ID("6006", "Id değeri boş olamaz"),
	NO_RECORD_TOKEN("7007", "Token girmemişsiniz");

	private String code;
	private String message;

	private MessageType(String code, String message) {
		this.code = code;
		this.message = message;
	}

}
