package com.kemalakcicek.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoRoleSave {

	@NotNull(message = "rolename is not null")
	@NotEmpty(message = "rolename is not empty")
    @NotBlank(message = "rolename is not blank")
	@Size(max = 30,min = 4)
	private String roleName;

}
