package com.kemalakcicek.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kemalakcicek.controller.IRoleController;
import com.kemalakcicek.dto.DtoRole;
import com.kemalakcicek.dto.DtoRoleSave;
import com.kemalakcicek.model.Role;
import com.kemalakcicek.model.RootEntity;
import com.kemalakcicek.service.IRoleService;
import com.kemalakcicek.utils.RestPageableRequest;
import com.kemalakcicek.utils.RestPageableResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/role")
public class RoleControllerImpl extends RestBaseController implements IRoleController {

	@Autowired
	private IRoleService iRoleService;

	@Override
	@PostMapping(path = "/save")
	public RootEntity<DtoRole> saveRole(@Valid @RequestBody DtoRoleSave dtoRoleSave) {

		return ok(iRoleService.saveRole(dtoRoleSave));
	}

	@Override
	@GetMapping(path = "/{id}")
	public RootEntity<DtoRole> findRoleById(@PathVariable Long id) {

		return ok(iRoleService.findRoleById(id));
	}

	@Override
	@GetMapping(path = "/all")
	public RootEntity<RestPageableResponse<DtoRole>> listRole(RestPageableRequest pageableRequest) {

		Page<Role> pageable = iRoleService.findAllPageable(toPageable(pageableRequest));

		RestPageableResponse<DtoRole> pageableResponse = toPageableResponse(pageable,
				iRoleService.listRole(pageable.getContent()));

		return ok(pageableResponse);
	}

	@Override
	@DeleteMapping(path = "/delete/{id}")
	public RootEntity<String> deleteRoleById(@PathVariable Long id) {

		Boolean result = iRoleService.deleteRoleById(id);

		if (result) {
			return ok("Kayıt başarıyla silinmiştir");
		}

		return ok("İd yanlış verdiniz");
	}

	@Override
	// hepsini güncellemek için put sadece belirli bir alanı güncellemek için patch
	// kullanılır put hepsini vermek zorundasın yoksa null döner
	@PutMapping(path = "/update/{id}")
	public RootEntity<DtoRole> updateRoleById(@RequestBody DtoRoleSave dtoRoleSave, @PathVariable Long id) {

		return ok(iRoleService.updateRoleById(dtoRoleSave, id));
	}

}
