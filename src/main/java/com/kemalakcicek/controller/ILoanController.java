package com.kemalakcicek.controller;

import com.kemalakcicek.dto.DtoLoan;
import com.kemalakcicek.dto.DtoLoanIU;
import com.kemalakcicek.model.RootEntity;
import com.kemalakcicek.utils.RestPageableRequest;
import com.kemalakcicek.utils.RestPageableResponse;

public interface ILoanController {

	public RootEntity<DtoLoan> saveLoan(DtoLoanIU dtoLoanIU, Long id);

	public RootEntity<RestPageableResponse<DtoLoan>> allLoanLists(RestPageableRequest pageableRequest);

	public RootEntity<DtoLoan> findLoanById(Long id);

	public RootEntity<String> deleteLoan(Long id);

	public RootEntity<DtoLoan> updateLoanById(DtoLoanIU dtoLoanIU, Long id);

}
