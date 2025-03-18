package com.kemalakcicek.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kemalakcicek.dto.DtoRole;
import com.kemalakcicek.dto.DtoRoleSave;
import com.kemalakcicek.dto.DtoUser;
import com.kemalakcicek.exception.BasePackage;
import com.kemalakcicek.exception.ErrorMessage;
import com.kemalakcicek.exception.MessageType;
import com.kemalakcicek.model.Role;
import com.kemalakcicek.model.User;
import com.kemalakcicek.repository.RoleRepository;
import com.kemalakcicek.service.IRoleService;
import com.kemalakcicek.service.IUserService;

import jakarta.transaction.Transactional;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private IUserService iUserService;

	@Override
	public Page<Role> findAllPageable(Pageable pageable) {

		return roleRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public DtoRole saveRole(DtoRoleSave dtoRoleSave) {

		Role role = new Role();

		BeanUtils.copyProperties(dtoRoleSave, role);

		roleRepository.save(role);

		// Return

		DtoRole dtoRole = new DtoRole();

		BeanUtils.copyProperties(role, dtoRole);

		return dtoRole;
	}

	@Override
	public DtoRole findRoleById(Long id) {

		Optional<Role> optional = roleRepository.findById(id);

		if (optional.isEmpty()) {
			throw new BasePackage(new ErrorMessage(MessageType.NO_RECORD_EXİST, id.toString()));
		}
		DtoRole dtoRole = new DtoRole();

		BeanUtils.copyProperties(optional.get(), dtoRole);

		// user
		List<DtoUser> listDtoRoles = new ArrayList<>();

		List<User> listRoles = optional.get().getUsers();

		for (User user : listRoles) {

			DtoUser dtoUser = iUserService.findUserById(user.getId());

			listDtoRoles.add(dtoUser);

		}
		dtoRole.setUsers(listDtoRoles);

		return dtoRole;
	}

	@Override
	public List<DtoRole> listRole(List<Role> roleLists) {

		List<Role> optional = roleLists;

		if (optional.isEmpty()) {

			throw new BasePackage(new ErrorMessage(MessageType.NO_RECORD_LİST, ""));

		}

		List<DtoRole> listDtoRoles = new ArrayList<>();

		for (Role role : optional) {

			List<DtoUser> listDtoUsers = new ArrayList<>();

			DtoRole dtoRole = new DtoRole();

			BeanUtils.copyProperties(role, dtoRole);

			listDtoRoles.add(dtoRole);

			// user

			for (User user : role.getUsers()) {

				DtoUser dtoUser = new DtoUser();

				BeanUtils.copyProperties(user, dtoUser);

				listDtoUsers.add(dtoUser);

			}
			dtoRole.setUsers(listDtoUsers);

		}

		return listDtoRoles;
	}

	@Override
	public Boolean deleteRoleById(Long id) {

		Optional<Role> optional = roleRepository.findById(id);

		if (optional.isEmpty()) {
			throw new BasePackage(new ErrorMessage(MessageType.NO_RECORD_EXİST, id.toString()));
		}

		roleRepository.deleteById(id);

		return true;
	}

	@Override
	@Transactional
	public DtoRole updateRoleById(DtoRoleSave dtoRoleSave, Long id) {

		Optional<Role> optional = roleRepository.findById(id);

		if (optional.isEmpty()) {

			throw new BasePackage(new ErrorMessage(MessageType.NO_RECORD_EXİST, id.toString()));

		}

		Role role = optional.get();

		BeanUtils.copyProperties(dtoRoleSave, role);

		roleRepository.save(role);

		// return

		return findRoleById(role.getId());
	}

}
