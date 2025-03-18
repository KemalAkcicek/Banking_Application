package com.kemalakcicek.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kemalakcicek.controller.IAccountController;
import com.kemalakcicek.dto.DtoAccount;
import com.kemalakcicek.dto.DtoAccountIU;
import com.kemalakcicek.model.Account;
import com.kemalakcicek.model.RootEntity;
import com.kemalakcicek.service.IAccountService;
import com.kemalakcicek.utils.RestPageableRequest;
import com.kemalakcicek.utils.RestPageableResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/account")
public class AccountControllerImpl extends RestBaseController implements IAccountController {

	@Autowired
	private IAccountService iAccountService;

	@Override
	@PostMapping(path = "/save")
	public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {

		return ok(iAccountService.saveAccount(dtoAccountIU));
	}

	@Override
	@GetMapping(path = "/all")
	public RootEntity<RestPageableResponse<DtoAccount>> allAccountList(RestPageableRequest pageableRequest) {

		Page<Account> pageable = iAccountService.findAllPageable(toPageable(pageableRequest));

		RestPageableResponse<DtoAccount> pageableResponse = toPageableResponse(pageable,
				iAccountService.allAccountList(pageable.getContent()));

		return ok(pageableResponse);
	}

	@Override
	@GetMapping(path = "/{id}")
	public RootEntity<DtoAccount> findAccountById(@PathVariable long id) {

		return ok(iAccountService.findAccountById(id));
	}

	@Override
	@PostMapping(path = "/update/{id}")
	public RootEntity<DtoAccount> updateAccountById(@PathVariable long id, @RequestBody DtoAccountIU dtoAccountIU) {

		return ok(iAccountService.updateAccountById(id, dtoAccountIU));
	}

	@Override
	@DeleteMapping(path = "/delete/{id}")
	public RootEntity<String> deleteAccountById(@PathVariable long id) {

		Boolean result = iAccountService.deleteAccountById(id);

		if (result == false) {

			return ok("Hatalı id verdiniz");
		}

		return ok("Silme işlemi başarıyla gerçekleştirildi");
	}

}
