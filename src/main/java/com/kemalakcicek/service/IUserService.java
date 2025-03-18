package com.kemalakcicek.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kemalakcicek.dto.DtoUser;
import com.kemalakcicek.dto.DtoUserIU;
import com.kemalakcicek.model.User;

public interface IUserService {

	// public DtoUserSave saveUser(DtoUserSave dtoUserSave);

	public Page<User> findAllPageable(Pageable pageable);

	public List<DtoUser> getUserList(List<User> pageableUser);

	public DtoUser findUserById(Long id);

	public DtoUser updateUserById(DtoUserIU dtoUserIU, long id);

	public boolean deleteUserById(Long id);

	public List<DtoUser> getUserByName(String name);

	public List<DtoUser> getUserByRole(Long roleId);

}
