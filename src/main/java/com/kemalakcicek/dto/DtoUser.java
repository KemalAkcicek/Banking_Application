package com.kemalakcicek.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoUser {

	private long id;

	private String firstName;

	private String lastName;

	private String eMail;

	private String phoneNumber;

	private List<DtoAccount> accounts;

	private List<DtoLoan> loans;

	private DtoRole role;
}
