package com.kemalakcicek.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kemalakcicek.dto.DtoTransaction;
import com.kemalakcicek.dto.DtoTransactionIU;
import com.kemalakcicek.model.Transaction;

public interface ITransactionService {

	public Page<Transaction> findAllPageable(Pageable pageable);

	public DtoTransaction saveTransaction(DtoTransactionIU dtoTransactionIU);

	public List<DtoTransaction> getTransactionsList(List<Transaction> transactions);

	public DtoTransaction findTransactionById(long id);

	public List<DtoTransaction> getAccountTransaction(long id);

}
