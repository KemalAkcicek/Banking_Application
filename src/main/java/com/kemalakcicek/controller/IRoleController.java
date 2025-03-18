package com.kemalakcicek.controller;

import com.kemalakcicek.dto.DtoRole;
import com.kemalakcicek.dto.DtoRoleSave;
import com.kemalakcicek.model.RootEntity;
import com.kemalakcicek.utils.RestPageableRequest;
import com.kemalakcicek.utils.RestPageableResponse;

public interface IRoleController {

	public RootEntity<DtoRole> saveRole(DtoRoleSave dtoRoleSave);

	public RootEntity<DtoRole> findRoleById(Long id);

	public RootEntity<RestPageableResponse<DtoRole>> listRole(RestPageableRequest pageableRequest);

	public RootEntity<String> deleteRoleById(Long id);

	public RootEntity<DtoRole> updateRoleById(DtoRoleSave dtoRoleSave, Long id);

}
