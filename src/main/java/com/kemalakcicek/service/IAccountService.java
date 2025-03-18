package com.kemalakcicek.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kemalakcicek.dto.DtoAccount;
import com.kemalakcicek.dto.DtoAccountIU;
import com.kemalakcicek.model.Account;

public interface IAccountService {

	public Page<Account> findAllPageable(Pageable pageable);

	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);

	public List<DtoAccount> allAccountList(List<Account> listAccounts);

	public DtoAccount findAccountById(long id);

	public DtoAccount updateAccountById(long id, DtoAccountIU dtoAccountIU);

	public Boolean deleteAccountById(long id);

}
