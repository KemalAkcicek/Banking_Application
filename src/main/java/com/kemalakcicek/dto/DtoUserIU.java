package com.kemalakcicek.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoUserIU {

	@NotEmpty
	private String firstName;

	private String lastName;

	private String eMail;

	private String phoneNumber;

	private List<DtoAccountIU> accounts;

	private List<DtoLoanIU> loans;

	private DtoRoleIU role;

}
