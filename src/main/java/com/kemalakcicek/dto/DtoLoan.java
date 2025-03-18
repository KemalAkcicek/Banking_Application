package com.kemalakcicek.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoLoan {

	private int amount;

	private double interestRate;

	private String status;

	private DtoUserSave user;
}
