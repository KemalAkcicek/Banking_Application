package com.kemalakcicek.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoRoleIU {

	private long id;
	// burada id değeri verisek ve id değerini kaydedersek hata verir çünkü id
	// değeri au dir

	private String roleName;

	// private List<User> users;
}
