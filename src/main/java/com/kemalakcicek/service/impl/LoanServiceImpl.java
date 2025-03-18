package com.kemalakcicek.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kemalakcicek.dto.DtoLoan;
import com.kemalakcicek.dto.DtoLoanIU;
import com.kemalakcicek.dto.DtoRole;
import com.kemalakcicek.dto.DtoUserSave;
import com.kemalakcicek.exception.BasePackage;
import com.kemalakcicek.exception.ErrorMessage;
import com.kemalakcicek.exception.MessageType;
import com.kemalakcicek.model.Loan;
import com.kemalakcicek.model.User;
import com.kemalakcicek.repository.LoanRepository;
import com.kemalakcicek.repository.UserRepository;
import com.kemalakcicek.service.ILoanService;

import jakarta.transaction.Transactional;

@Service
public class LoanServiceImpl implements ILoanService {

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Page<Loan> findAllPageable(Pageable pageable) {

		return loanRepository.findAll(pageable);
	}

	@Override
	public DtoLoan saveLoan(DtoLoanIU dtoLoanIU, Long id) {

		Optional<User> optional = userRepository.findById(id);

		if (optional.isEmpty()) {
			throw new BasePackage(new ErrorMessage(MessageType.NO_RECORD_EXİST, id.toString()));
		}

		Loan loan = new Loan();

		BeanUtils.copyProperties(dtoLoanIU, loan);

		// user
		User user = optional.get();
		loan.setUser(user);

		loanRepository.save(loan);

		// Geriye döndürme

		DtoLoan dtoLoan = new DtoLoan();

		BeanUtils.copyProperties(dtoLoanIU, dtoLoan);

		// user
		DtoUserSave dtoUser = new DtoUserSave();
		BeanUtils.copyProperties(user, dtoUser);

		dtoLoan.setUser(dtoUser);

		return dtoLoan;
	}

	@Override
	public List<DtoLoan> allLoanLists(List<Loan> listLoan) {

		List<Loan> loanList = listLoan;

		List<DtoLoan> dtoLoanList = new ArrayList<>();

		for (Loan loan : loanList) {

			DtoLoan dtoLoan = new DtoLoan();

			BeanUtils.copyProperties(loan, dtoLoan);

			// USER

			DtoUserSave dtoUser = new DtoUserSave();

			BeanUtils.copyProperties(loan.getUser(), dtoUser);

			dtoLoan.setUser(dtoUser);

			dtoLoanList.add(dtoLoan);

		}

		return dtoLoanList;
	}

	@Override
	public DtoLoan findLoanById(Long id) {

		Optional<Loan> optional = loanRepository.findById(id);

		if (optional.isEmpty()) {

			throw new BasePackage(new ErrorMessage(MessageType.NO_RECORD_EXİST, id.toString()));
		}
		Loan loan = optional.get();

		DtoLoan dtoLoan = new DtoLoan();

		BeanUtils.copyProperties(loan, dtoLoan);

		// user

		DtoUserSave dtoUserSave = new DtoUserSave();

		BeanUtils.copyProperties(loan.getUser(), dtoUserSave);

		DtoRole dtoRole = new DtoRole();

		BeanUtils.copyProperties(loan.getUser().getRole(), dtoRole);

		dtoUserSave.setRole(dtoRole);

		dtoLoan.setUser(dtoUserSave);

		return dtoLoan;
	}

	@Override
	public Boolean deleteLoanById(Long id) {

		Optional<Loan> optional = loanRepository.findById(id);

		if (optional.isEmpty()) {

			throw new BasePackage(new ErrorMessage(MessageType.NO_RECORD_EXİST, id.toString()));

		}
		loanRepository.delete(optional.get());

		return true;
	}

	// Transactional işlemleri veri bütünlüğünü sağlamak için yaptığım bir işlemdir
	// eğer db (crud) işlemlerinde herhangi bir hata meydana gelirse yapılan
	// işlemlerini geriye alınmasını sağlar
	@Override
	@Transactional
	public DtoLoan updateLoanById(DtoLoanIU dtoLoanIU, Long id) {

		Optional<Loan> optional = loanRepository.findById(id);

		if (optional.isEmpty()) {

			throw new BasePackage(new ErrorMessage(MessageType.NO_RECORD_EXİST, id.toString()));

		}
		Loan loan = optional.get();

		BeanUtils.copyProperties(dtoLoanIU, loan, "id");

		// user

		User user = optional.get().getUser();

		BeanUtils.copyProperties(dtoLoanIU.getUser(), user);

		loan.setUser(user);

		loanRepository.save(loan);

		// Return

		return findLoanById(loan.getId());
	}

}
