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
public class DtoUserSave {

	private Long id;

	@NotNull(message = "isim alanı null olamaz")
	@NotEmpty(message = "isim alanı boş olamaz")
	@Size(max = 20)
	private String firstName;

	@NotNull(message = "soyisim alanı null olamaz")
	@NotEmpty(message = "soyisim alanı boş olamaz")
	@Size(max = 20)
	private String lastName;

	@Email(message = "Lütfen email formatında giriniz")
	private String eMail;

	@Size(min = 9, max = 9)
	@Positive(message = "phone is not negativ")
	private String phoneNumber;

	private DtoRole role;

}
