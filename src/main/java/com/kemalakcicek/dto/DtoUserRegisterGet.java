package com.kemalakcicek.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoUserRegisterGet {

	@Email(message = "Email format is incorrect")
	private String username;

	@NotEmpty(message = "password is not null")
	private String password;

}
