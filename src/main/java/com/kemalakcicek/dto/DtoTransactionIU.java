package com.kemalakcicek.dto;

import java.time.LocalDate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
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
public class DtoTransactionIU {

	// Var olmayan kullanıclar arasında para transferine izin verilmez

	@Positive(message = "amount is not negatif")
	@Max(value = 150000000)
	private int amount;

	@Size(max = 30)
	@NotNull(message = "description is not null")
	@NotBlank(message = "description is not blank")
	@NotEmpty(message = "description is not empty")
	private String description;

	// @CreationTimestamp bu kullanım sadece entity sınıfında kullanılır
	private LocalDate transcationDate = LocalDate.now();

	@Valid
	private DtoAccountIU senderAccount;

	@Valid
	private DtoAccountIU receiveAccount;

	// private Account account;
}
