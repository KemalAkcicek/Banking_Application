package com.kemalakcicek.controller;

import java.util.List;

import com.kemalakcicek.dto.DtoUser;
import com.kemalakcicek.dto.DtoUserIU;
import com.kemalakcicek.model.RootEntity;
import com.kemalakcicek.utils.RestPageableRequest;
import com.kemalakcicek.utils.RestPageableResponse;

public interface IUserController {

	// public RootEntity<DtoUserSave> saveUser(DtoUser dtoUserSave);

	public RootEntity<RestPageableResponse<DtoUser>> getUserLists(RestPageableRequest pageableRequest);

	public RootEntity<DtoUser> findUserById(Long id);

	public RootEntity<DtoUser> updateUserById(DtoUserIU dtoUserIU, Long id);

	public RootEntity<String> deleteUserById(Long id);

	public RootEntity<List<DtoUser>> getUserByName(String name);

	public RootEntity<List<DtoUser>> getUsersByRole(Long roleId);

}
