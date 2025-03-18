package com.kemalakcicek.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kemalakcicek.controller.ITransactionController;
import com.kemalakcicek.dto.DtoTransaction;
import com.kemalakcicek.dto.DtoTransactionIU;
import com.kemalakcicek.model.RootEntity;
import com.kemalakcicek.model.Transaction;
import com.kemalakcicek.service.ITransactionService;
import com.kemalakcicek.utils.RestPageableRequest;
import com.kemalakcicek.utils.RestPageableResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/transaction")
public class TransactionController extends RestBaseController implements ITransactionController {

	@Autowired
	private ITransactionService iTransactionService;

	@Override
	@PostMapping(path = "/save")
	public RootEntity<DtoTransaction> saveTransaction(@Valid @RequestBody DtoTransactionIU dtoTransactionIU) {

		return ok(iTransactionService.saveTransaction(dtoTransactionIU));
	}

	@Override
	@GetMapping(path = "/all")
	public RootEntity<RestPageableResponse<DtoTransaction>> getTransactionsList(RestPageableRequest pageableRequest) {

		Page<Transaction> pageable = iTransactionService.findAllPageable(toPageable(pageableRequest));

		RestPageableResponse<DtoTransaction> pageableResponse = toPageableResponse(pageable,
				iTransactionService.getTransactionsList(pageable.getContent()));

		return ok(pageableResponse);
	}

	@Override
	@GetMapping(path = "/{id}")
	public RootEntity<DtoTransaction> findTransactionById(@PathVariable long id) {

		return ok(iTransactionService.findTransactionById(id));
	}

	@Override
	@GetMapping(path = "/account/{id}")
	public List<DtoTransaction> getAccountTransaction(@PathVariable long id) {

		return iTransactionService.getAccountTransaction(id);
	}

}
