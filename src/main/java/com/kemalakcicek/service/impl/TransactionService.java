package com.kemalakcicek.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kemalakcicek.dto.DtoAccountIU;
import com.kemalakcicek.dto.DtoTransaction;
import com.kemalakcicek.dto.DtoTransactionIU;
import com.kemalakcicek.exception.BasePackage;
import com.kemalakcicek.exception.ErrorMessage;
import com.kemalakcicek.exception.MessageType;
import com.kemalakcicek.model.Account;
import com.kemalakcicek.model.Transaction;
import com.kemalakcicek.repository.TransactionRepository;
import com.kemalakcicek.service.ITransactionService;

@Service
public class TransactionService implements ITransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	// @Scheduled(cron = "0 47 17 * * *")
	public void deleteTransaction10Day() {

		transactionRepository.deleteTransactionFrom10();
		System.out.println("10 GÜN ÖNCEKİ DATALAR SİLİNDİ");
	}

	@Override
	public Page<Transaction> findAllPageable(Pageable pageable) {

		return transactionRepository.findAll(pageable);
	}

	@Override
	public DtoTransaction saveTransaction(DtoTransactionIU dtoTransactionIU) {

		Transaction transaction = new Transaction();

		BeanUtils.copyProperties(dtoTransactionIU, transaction);

		// Sender
		Account senderAccount = new Account();

		if (dtoTransactionIU.getSenderAccount().getId() == 0 || dtoTransactionIU.getSenderAccount().getId() == 0) {
			throw new BasePackage(new ErrorMessage(MessageType.NO_EXİSTS_ID, "0"));

		}

		if (dtoTransactionIU.getSenderAccount() != null) {

			BeanUtils.copyProperties(dtoTransactionIU.getSenderAccount(), senderAccount);

			transaction.setSenderAccount(senderAccount);

		} else {

			throw new BasePackage(new ErrorMessage(MessageType.SENDER_ACCOUNT_EXİST, "vfdg"));
		}

		// Receive
		Account receiveAccount = new Account();

		if (dtoTransactionIU.getReceiveAccount() != null) {

			BeanUtils.copyProperties(dtoTransactionIU.getReceiveAccount(), receiveAccount);

			transaction.setReceiveAccount(receiveAccount);
		} else {

			throw new BasePackage(new ErrorMessage(MessageType.RECEİVE_ACCOUNT_EXİST, ""));

		}

		transactionRepository.save(transaction);

		// Geriye döndüre işi
		DtoTransaction dtoTransaction = new DtoTransaction();

		BeanUtils.copyProperties(transaction, dtoTransaction);

		// Sender

		// SENDER ACCOUNT VE RECEİVE ACCOUNT NULL KONTROL EDİLECEK
		DtoAccountIU senderAccountReturn = new DtoAccountIU();

		BeanUtils.copyProperties(transaction.getSenderAccount(), senderAccountReturn);

		dtoTransaction.setSenderAccount(senderAccountReturn);

		// Receive

		DtoAccountIU receiveAccountReturn = new DtoAccountIU();

		BeanUtils.copyProperties(transaction.getReceiveAccount(), receiveAccountReturn);

		dtoTransaction.setReceiveAccount(receiveAccountReturn);

		return dtoTransaction;
	}

	@Override
	public List<DtoTransaction> getTransactionsList(List<Transaction> transactions) {

		List<Transaction> listTransactions = transactions;

		List<DtoTransaction> listDtoTransactions = new ArrayList<>();

		for (Transaction transaction : listTransactions) {

			DtoTransaction dtoTransaction = new DtoTransaction();

			BeanUtils.copyProperties(transaction, dtoTransaction);

			// sender
			DtoAccountIU senderAccount = new DtoAccountIU();

			BeanUtils.copyProperties(transaction.getSenderAccount(), senderAccount);

			dtoTransaction.setSenderAccount(senderAccount);

			// Receive

			DtoAccountIU receiveAccount = new DtoAccountIU();

			BeanUtils.copyProperties(transaction.getReceiveAccount(), receiveAccount);

			dtoTransaction.setReceiveAccount(receiveAccount);

			listDtoTransactions.add(dtoTransaction);

		}

		return listDtoTransactions;
	}

	@Override
	public DtoTransaction findTransactionById(long id) {

		Optional<Transaction> optional = transactionRepository.findById(id);

		if (optional.isEmpty()) {
			throw new BasePackage(new ErrorMessage(MessageType.NO_RECORD_EXİST, String.valueOf(id)));
		}

		Transaction transaction = optional.get();

		DtoTransaction dtoTransaction = new DtoTransaction();

		BeanUtils.copyProperties(transaction, dtoTransaction);

		// Sender

		DtoAccountIU senderAccountIU = new DtoAccountIU();

		BeanUtils.copyProperties(transaction.getSenderAccount(), senderAccountIU);

		dtoTransaction.setSenderAccount(senderAccountIU);

		// receive

		DtoAccountIU receiveAccountIU = new DtoAccountIU();

		BeanUtils.copyProperties(transaction.getReceiveAccount(), receiveAccountIU);

		dtoTransaction.setReceiveAccount(receiveAccountIU);

		return dtoTransaction;
	}

	@Override
	public List<DtoTransaction> getAccountTransaction(long id) {

		List<Transaction> transactionList = transactionRepository.findSenderAccount(id);

		if (transactionList.isEmpty()) {
			throw new BasePackage(new ErrorMessage(MessageType.NO_RECORD_EXİST, String.valueOf(id)));

		}

		List<DtoTransaction> dtoTransactionList = new ArrayList<>();

		for (Transaction transaction : transactionList) {

			DtoTransaction dtoTransaction = new DtoTransaction();

			BeanUtils.copyProperties(transaction, dtoTransaction);

			// Sender

			DtoAccountIU senderAccount = new DtoAccountIU();

			BeanUtils.copyProperties(transaction.getSenderAccount(), senderAccount);

			dtoTransaction.setSenderAccount(senderAccount);

			// RECEİVE

			DtoAccountIU rececAccountIU = new DtoAccountIU();

			BeanUtils.copyProperties(transaction.getReceiveAccount(), rececAccountIU);

			dtoTransaction.setReceiveAccount(rececAccountIU);

			dtoTransactionList.add(dtoTransaction);

		}

		return dtoTransactionList;
	}

}
