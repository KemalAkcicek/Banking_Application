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

import com.kemalakcicek.controller.ILoanController;
import com.kemalakcicek.dto.DtoLoan;
import com.kemalakcicek.dto.DtoLoanIU;
import com.kemalakcicek.model.Loan;
import com.kemalakcicek.model.RootEntity;
import com.kemalakcicek.service.ILoanService;
import com.kemalakcicek.utils.RestPageableRequest;
import com.kemalakcicek.utils.RestPageableResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/loan")
public class LoanControllerImpl extends RestBaseController implements ILoanController {

	@Autowired
	private ILoanService iLoanService;

	@Override
	@PostMapping(path = "/save/{id}")
	public RootEntity<DtoLoan> saveLoan(@Valid @RequestBody DtoLoanIU dtoLoanIU, @PathVariable Long id) {

		return ok(iLoanService.saveLoan(dtoLoanIU, id));
	}

	@Override
	@GetMapping(path = "/list")
	public RootEntity<RestPageableResponse<DtoLoan>> allLoanLists(RestPageableRequest pageableRequest) {

		Page<Loan> pageable = iLoanService.findAllPageable(toPageable(pageableRequest));

		RestPageableResponse<DtoLoan> pageableResponse = toPageableResponse(pageable,
				iLoanService.allLoanLists(pageable.getContent()));

		return ok(pageableResponse);
	}

	@Override
	@GetMapping(path = "/{id}")
	public RootEntity<DtoLoan> findLoanById(@PathVariable Long id) {

		return ok(iLoanService.findLoanById(id));
	}

	@Override
	@DeleteMapping(path = "/delete/{id}")
	public RootEntity<String> deleteLoan(@PathVariable Long id) {

		Boolean result = iLoanService.deleteLoanById(id);

		if (result) {
			return ok("Kayıt başarıyla silindi");
		}

		return ok("Hatalı id verdiniz");
	}

	@Override
	@PostMapping(path = "/update/{id}")
	public RootEntity<DtoLoan> updateLoanById(@RequestBody DtoLoanIU dtoLoanIU, @PathVariable Long id) {

		return ok(iLoanService.updateLoanById(dtoLoanIU, id));
	}

}
