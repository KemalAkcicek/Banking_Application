package com.kemalakcicek.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
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
public class DtoAccountIU {

	private long id;

	// @Size(max = 9, min = 9, message = "Hesap numarası 9 haneli olması lazım")
	// size sadece length olan değişkenlerde kullanılır int kullanılmaz

	// int değer kontrole için digits kullanabilirsin max 10, fraction = 0 bu kısım
	// ise kaç tane ondalık kısmı oluncağı söyluyorsun
	@Digits(integer = 10, fraction = 0)
	@Positive(message = "Hesap numarası negatif olamaz")
	private int accountNumber;

	@Positive(message = "bakiye negatif olamaz")
	private int balance;

	@NotNull(message = "accountype is not null")
	@NotEmpty(message = "accountype is not empty")
	@NotBlank(message = "accountype is not blank")
	@Size(max = 30, min = 4)
	private String accountType;

	private DtoAccountSaveUser user;

}
