package com.kemalakcicek.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoAccountSaveUser {

	private long id;

	@NotNull(message = "firstname alanı null olamaz")
	@NotEmpty(message = "firstname alanı boş olamaz")
	@Size(max = 20)
	private String firstName;

	@NotNull(message = "lastname alanı null olamaz")
	@NotEmpty(message = "lastname alanı boş olamaz")
	@Size(max = 20)
	private String lastName;

	@Email(message = "Lütfen email formatında giriniz")
	private String eMail;

	@Size(min = 9, max = 9)
	@Positive(message = "phone is not negativ")
	private String phoneNumber;

}
