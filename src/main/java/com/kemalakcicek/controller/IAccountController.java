package com.kemalakcicek.controller;

import com.kemalakcicek.dto.DtoAccount;
import com.kemalakcicek.dto.DtoAccountIU;
import com.kemalakcicek.model.RootEntity;
import com.kemalakcicek.utils.RestPageableRequest;
import com.kemalakcicek.utils.RestPageableResponse;

public interface IAccountController {

	public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);

	public RootEntity<RestPageableResponse<DtoAccount>> allAccountList(RestPageableRequest pageableRequest);

	public RootEntity<DtoAccount> findAccountById(long id);

	public RootEntity<DtoAccount> updateAccountById(long id, DtoAccountIU dtoAccountIU);

	public RootEntity<String> deleteAccountById(long id);
}
