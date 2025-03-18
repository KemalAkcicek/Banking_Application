package com.kemalakcicek.dto;

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
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DtoLoanIU {

	private Long id;

	@Positive(message = "amount is not negatif")
	@Max(value = 150000000)
	private int amount;

	@Positive(message = "interest is not negatif")
	@Max(value = 100)
	private double interestRate;

	@NotNull(message = "status is not null")
	@NotEmpty(message = "status is not empty")
	@NotBlank(message = "status is not blank")
	@Size(max = 30, min = 4)
	private String status;

	@Valid
	private DtoUserSave user;

}
