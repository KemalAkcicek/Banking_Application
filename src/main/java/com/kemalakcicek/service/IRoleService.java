package com.kemalakcicek.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kemalakcicek.dto.DtoRole;
import com.kemalakcicek.dto.DtoRoleSave;
import com.kemalakcicek.model.Role;

public interface IRoleService {

	public Page<Role> findAllPageable(Pageable pageable);

	public DtoRole saveRole(DtoRoleSave dtoRoleSave);

	public DtoRole findRoleById(Long id);

	public List<DtoRole> listRole(List<Role> roleLists);

	public Boolean deleteRoleById(Long id);

	public DtoRole updateRoleById(DtoRoleSave dtoRoleSave, Long id);
}
