package com.kemalakcicek.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kemalakcicek.dto.DtoAccount;
import com.kemalakcicek.dto.DtoAccountIU;
import com.kemalakcicek.dto.DtoAccountSaveUser;
import com.kemalakcicek.dto.DtoTransaction;
import com.kemalakcicek.exception.BasePackage;
import com.kemalakcicek.exception.ErrorMessage;
import com.kemalakcicek.exception.MessageType;
import com.kemalakcicek.model.Account;
import com.kemalakcicek.model.Transaction;
import com.kemalakcicek.model.User;
import com.kemalakcicek.repository.AccountRepository;
import com.kemalakcicek.repository.UserRepository;
import com.kemalakcicek.service.IAccountService;

import jakarta.transaction.Transactional;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Page<Account> findAllPageable(Pageable pageable) {

		return accountRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {

		Account account = new Account();

		// Burada id alanı dışında olanları kopyaladık çünkü id alanı öyle kopyalarsak
		// vermesek ıd 0 alır
		BeanUtils.copyProperties(dtoAccountIU, account, "id");

		// user
		Optional<User> optional = userRepository.findById(dtoAccountIU.getUser().getId());

		if (optional.isEmpty()) {

			throw new BasePackage(
					new ErrorMessage(MessageType.NO_RECORD_EXİST, String.valueOf(dtoAccountIU.getUser().getId())));
		}
		account.setUser(optional.get());

		accountRepository.save(account);

		// Geriye döndürme

		DtoAccount dtoAccount = new DtoAccount();

		BeanUtils.copyProperties(account, dtoAccount);

		// user
		DtoAccountSaveUser dtoUser = new DtoAccountSaveUser();

		BeanUtils.copyProperties(account.getUser(), dtoUser);

		dtoAccount.setUser(dtoUser);

		return dtoAccount;
	}

	@Override
	public List<DtoAccount> allAccountList(List<Account> listAccount) {

		List<Account> listAccounts = listAccount;

		List<DtoAccount> listDtoAccounts = new ArrayList<>();

		if (listAccounts.isEmpty()) {
			throw new BasePackage(new ErrorMessage(MessageType.NO_RECORD_LİST, "[]"));
		}
		for (Account account : listAccounts) {

			DtoAccount dtoAccount = new DtoAccount();

			BeanUtils.copyProperties(account, dtoAccount);

			// user

			DtoAccountSaveUser accountSaveUser = new DtoAccountSaveUser();

			BeanUtils.copyProperties(account.getUser(), accountSaveUser);

			dtoAccount.setUser(accountSaveUser);

			listDtoAccounts.add(dtoAccount);

		}

		return listDtoAccounts;
	}

	@Override
	public DtoAccount findAccountById(long id) {

		Optional<Account> optional = accountRepository.findById(id);

		if (optional.isEmpty()) {
			throw new BasePackage(new ErrorMessage(MessageType.NO_RECORD_EXİST, String.valueOf(id)));
		}
		DtoAccount dtoAccount = new DtoAccount();

		BeanUtils.copyProperties(optional.get(), dtoAccount);

		// user
		DtoAccountSaveUser dtoAccountSaveUser = new DtoAccountSaveUser();

		BeanUtils.copyProperties(optional.get().getUser(), dtoAccountSaveUser);

		dtoAccount.setUser(dtoAccountSaveUser);

		// senttransactions
		List<Transaction> sentTransactions = optional.get().getSentTransactions();

		List<DtoTransaction> dtoSentTransactions = new ArrayList<>();

		for (Transaction sent : sentTransactions) {

			DtoTransaction dtoTransaction = new DtoTransaction();

			BeanUtils.copyProperties(sent, dtoTransaction);

			dtoSentTransactions.add(dtoTransaction);

		}
		dtoAccount.setSentTransactions(dtoSentTransactions);
		// receivetransactions
		List<Transaction> receiveTransactions = optional.get().getReceiveTransactions();

		List<DtoTransaction> dtoReceiveTransactions = new ArrayList<>();

		for (Transaction receive : receiveTransactions) {

			DtoTransaction dtoTransaction = new DtoTransaction();

			BeanUtils.copyProperties(receive, dtoTransaction);

			dtoReceiveTransactions.add(dtoTransaction);

		}
		dtoAccount.setReceiveTransactions(dtoReceiveTransactions);

		return dtoAccount;
	}

	@Override
	public DtoAccount updateAccountById(long id, DtoAccountIU dtoAccountIU) {

		Optional<Account> optional = accountRepository.findById(id);

		if (optional.isEmpty()) {

			throw new BasePackage(new ErrorMessage(MessageType.NO_RECORD_EXİST, String.valueOf(id)));

		}
		// db işlemleri
		Account account = optional.get();

		BeanUtils.copyProperties(dtoAccountIU, account);

		// user
		User user = optional.get().getUser();

		BeanUtils.copyProperties(dtoAccountIU.getUser(), user);

		account.setUser(user);

		accountRepository.save(account);

		// Return işlemleri

		return findAccountById(account.getId());
	}

	@Override
	public Boolean deleteAccountById(long id) {

		Optional<Account> optional = accountRepository.findById(id);

		if (optional.isEmpty()) {

			throw new BasePackage(new ErrorMessage(MessageType.NO_RECORD_EXİST, String.valueOf(id)));
		}
		accountRepository.deleteById(id);

		return true;
	}

}
