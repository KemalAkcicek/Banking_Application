package com.kemalakcicek.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kemalakcicek.dto.DtoLoan;
import com.kemalakcicek.dto.DtoLoanIU;
import com.kemalakcicek.model.Loan;

public interface ILoanService {

	public Page<Loan> findAllPageable(Pageable pageable);

	public DtoLoan saveLoan(DtoLoanIU dtoLoanIU, Long id);

	public List<DtoLoan> allLoanLists(List<Loan> listLoan);

	public DtoLoan findLoanById(Long id);

	public Boolean deleteLoanById(Long id);

	public DtoLoan updateLoanById(DtoLoanIU dtoLoanIU, Long id);
}
