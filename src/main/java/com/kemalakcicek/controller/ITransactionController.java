package com.kemalakcicek.controller;

import java.util.List;

import com.kemalakcicek.dto.DtoTransaction;
import com.kemalakcicek.dto.DtoTransactionIU;
import com.kemalakcicek.model.RootEntity;
import com.kemalakcicek.utils.RestPageableRequest;
import com.kemalakcicek.utils.RestPageableResponse;

public interface ITransactionController {

	public RootEntity<DtoTransaction> saveTransaction(DtoTransactionIU dtoTransactionIU);

	public RootEntity<RestPageableResponse<DtoTransaction>> getTransactionsList(RestPageableRequest pageableRequest);

	public RootEntity<DtoTransaction> findTransactionById(long id);

	public List<DtoTransaction> getAccountTransaction(long id);

}
