package com.kemalakcicek.jwt;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

	@Email(message = "Email format is incorrect")
	private String username;

	@NotEmpty(message = "password is not null")
	private String password;

}
