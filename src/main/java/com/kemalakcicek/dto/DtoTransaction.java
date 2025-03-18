package com.kemalakcicek.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoTransaction {

	private int amount;

	private String description;

	private LocalDate transcationDate;

	private DtoAccountIU senderAccount;

	private DtoAccountIU receiveAccount;

	// private Account account;

}
