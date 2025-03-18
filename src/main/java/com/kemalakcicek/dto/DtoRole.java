package com.kemalakcicek.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DtoRole {

	private long id;

	private String roleName;

	private List<DtoUser> users;

}
