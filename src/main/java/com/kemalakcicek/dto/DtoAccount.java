package com.kemalakcicek.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoAccount {

	private int accountNumber;

	private int balance;

	private String accountType;

	private DtoAccountSaveUser user;

	// private List<DtoTransaction> transactions;

	private List<DtoTransaction> sentTransactions;

	private List<DtoTransaction> receiveTransactions;

}
