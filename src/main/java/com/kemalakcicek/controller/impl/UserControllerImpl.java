package com.kemalakcicek.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kemalakcicek.controller.IUserController;
import com.kemalakcicek.dto.DtoUser;
import com.kemalakcicek.dto.DtoUserIU;
import com.kemalakcicek.model.RootEntity;
import com.kemalakcicek.model.User;
import com.kemalakcicek.service.IUserService;
import com.kemalakcicek.utils.RestPageableRequest;
import com.kemalakcicek.utils.RestPageableResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/user")
public class UserControllerImpl extends RestBaseController implements IUserController {

	@Autowired
	private IUserService iUserService;

	/*
	 * BU SERVİS ZATEN REGİSTER KAYDETTİĞİMİZ İÇİN BU SERVİS KAPANDI
	 * 
	 * @Override
	 * 
	 * @PostMapping(path = "/save") public RootEntity<DtoUserSave>
	 * saveUser(@Valid @RequestBody DtoUserSave dtoUserSave) {
	 * 
	 * return ok(iUserService.saveUser(dtoUserSave)); }
	 */
	@Override
	@GetMapping(path = "/lists")
	public RootEntity<RestPageableResponse<DtoUser>> getUserLists(RestPageableRequest pageableRequest) {

		// Burada db pageable olarak verdik bana pageable olarak gerekli değerleri
		// verdi(pageNumber,pageSize,totalElements, list<user>)
		Page<User> pageable = iUserService.findAllPageable(toPageable(pageableRequest));

		List<DtoUser> dtoUserList = iUserService.getUserList(pageable.getContent());

		// Burada ise db aldığımız pageable kendi formatımdızda döndük
		RestPageableResponse<DtoUser> pageableResponse = toPageableResponse(pageable, dtoUserList);

		return ok(pageableResponse);
	}

	@Override
	@GetMapping(path = "/{id}")
	public RootEntity<DtoUser> findUserById(@PathVariable Long id) {

		DtoUser dtoUser = iUserService.findUserById(id);

		if (dtoUser == null) {
			return error("Hatalı işlem yaptınız");
		}

		return ok(iUserService.findUserById(id));
	}

	@Override
	@PutMapping(path = "/update/{id}")
	public RootEntity<DtoUser> updateUserById(@Valid @RequestBody DtoUserIU dtoUserIU, @PathVariable Long id) {

		return ok(iUserService.updateUserById(dtoUserIU, id));
	}

	@Override
	@DeleteMapping(path = "delete/{id}")
	public RootEntity<String> deleteUserById(@PathVariable Long id) {
		boolean result = iUserService.deleteUserById(id);

		if (result == false) {

			return error("id bulunamadi");
		}
		return ok("user başarıyla silindi");
	}

	@Override
	@GetMapping("/example")
	public RootEntity<List<DtoUser>> getUserByName(@RequestParam String name) {

		return ok(iUserService.getUserByName(name));

	}

	@Override
	@GetMapping(path = "/role")
	public RootEntity<List<DtoUser>> getUsersByRole(@RequestParam Long roleId) {

		return ok(iUserService.getUserByRole(roleId));
	}

}
