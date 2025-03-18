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
import com.kemalakcicek.dto.DtoLoan;
import com.kemalakcicek.dto.DtoLoanIU;
import com.kemalakcicek.dto.DtoRole;
import com.kemalakcicek.dto.DtoUser;
import com.kemalakcicek.dto.DtoUserIU;
import com.kemalakcicek.exception.BasePackage;
import com.kemalakcicek.exception.ErrorMessage;
import com.kemalakcicek.exception.MessageType;
import com.kemalakcicek.model.Account;
import com.kemalakcicek.model.Loan;
import com.kemalakcicek.model.Role;
import com.kemalakcicek.model.User;
import com.kemalakcicek.repository.RoleRepository;
import com.kemalakcicek.repository.UserRepository;
import com.kemalakcicek.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Page<User> findAllPageable(Pageable pageable) {

		return userRepository.findAll(pageable);
	}

	@Override
	public List<DtoUser> getUserList(List<User> pageableUser) {

		List<DtoUser> listDtoUsers = new ArrayList<>();

		List<User> users = pageableUser;

		for (User user : users) {

			// user
			DtoUser dtoUser = new DtoUser();

			BeanUtils.copyProperties(user, dtoUser);

			// role
			DtoRole dtoRole = new DtoRole();

			BeanUtils.copyProperties(user.getRole(), dtoRole);

			dtoUser.setRole(dtoRole);

			// Account
			List<DtoAccount> listDtoAccounts = new ArrayList<>();
			for (Account account : user.getAccounts()) {

				DtoAccount dtoAccount = new DtoAccount();

				BeanUtils.copyProperties(account, dtoAccount);

				listDtoAccounts.add(dtoAccount);

			}
			dtoUser.setAccounts(listDtoAccounts);

			// loans
			List<DtoLoan> listDtoLoans = new ArrayList<>();
			for (Loan loan : user.getLoans()) {

				DtoLoan dtoLoan = new DtoLoan();

				BeanUtils.copyProperties(loan, dtoLoan);

				listDtoLoans.add(dtoLoan);

			}
			dtoUser.setLoans(listDtoLoans);

			listDtoUsers.add(dtoUser);

		}

		return listDtoUsers;
	}

	@Override
	public DtoUser findUserById(Long id) {

		Optional<User> user = userRepository.findById(id);

		if (user.isEmpty()) {

			throw new BasePackage(new ErrorMessage(MessageType.NO_RECORD_EXİST, id.toString()));
		}
		DtoUser dtoUser = new DtoUser();

		BeanUtils.copyProperties(user.get(), dtoUser);

		// Role
		DtoRole dtoRole = new DtoRole();

		BeanUtils.copyProperties(user.get().getRole(), dtoRole);

		dtoUser.setRole(dtoRole);

		// Account
		List<DtoAccount> listDtoAccounts = new ArrayList<>();
		for (Account account : user.get().getAccounts()) {

			DtoAccount dtoAccount = new DtoAccount();
			BeanUtils.copyProperties(account, dtoAccount);

			listDtoAccounts.add(dtoAccount);

		}
		dtoUser.setAccounts(listDtoAccounts);

		// Loan
		List<DtoLoan> listDtoLoans = new ArrayList<>();

		for (Loan loan : user.get().getLoans()) {

			DtoLoan dtoLoan = new DtoLoan();

			BeanUtils.copyProperties(loan, dtoLoan);

			listDtoLoans.add(dtoLoan);

		}
		dtoUser.setLoans(listDtoLoans);

		return dtoUser;
	}

	@Override
	public DtoUser updateUserById(DtoUserIU dtoUserIU, long id) {

		Optional<User> optional = userRepository.findById(id);

		if (optional.isEmpty()) {

			throw new BasePackage(new ErrorMessage(MessageType.NO_RECORD_EXİST, String.valueOf(id)));

		}

		User user = optional.get();

		BeanUtils.copyProperties(dtoUserIU, user);

		// role
		Optional<Role> role = roleRepository.findById(dtoUserIU.getRole().getId());
		user.setRole(role.get());

		// Account
		// Account ve loan güncellerken önceki değerleri alıp güncellersin
		// yoksa farklı bir account ve loan oluşturur
		List<Account> listAccount = user.getAccounts();
		for (DtoAccountIU dtoAccountIU : dtoUserIU.getAccounts()) {

			// Burada id ile bulduğumuz account aldık yani güncellenecek // id eşleşmesi
			// yaptık
			Account account = (Account) user.getAccounts().stream().filter(a -> a.getId() == dtoAccountIU.getId())
					.findFirst().orElse(new Account());
			// Burada db mevcut account aldık
			// yoksa yeni account oluşturduk

			BeanUtils.copyProperties(dtoAccountIU, account);
			account.setUser(user);
			listAccount.add(account);

		}
		user.setAccounts(listAccount);

		// loan
		List<Loan> listLoans = user.getLoans();
		for (DtoLoanIU dtoLoanIU : dtoUserIU.getLoans()) {

			// Loan loan = new Loan(); böyle yaptığımız zaman yeni bir tane oluşturusun

			Loan loan = (Loan) user.getLoans().stream().filter(l -> l.getId() == dtoLoanIU.getId()).findFirst()
					.orElse(new Loan());

			BeanUtils.copyProperties(dtoLoanIU, loan);
			loan.setUser(user);
			listLoans.add(loan);

		}
		user.setLoans(listLoans);

		User updateUser = userRepository.save(user);

		return findUserById(updateUser.getId());

	}

	@Override
	public boolean deleteUserById(Long id) {

		Optional<User> optional = userRepository.findById(id);

		if (optional.isEmpty()) {
			throw new BasePackage(new ErrorMessage(MessageType.NO_RECORD_EXİST, id.toString()));
		}
		userRepository.deleteById(id);

		return true;
	}

	@Override
	public List<DtoUser> getUserByName(String name) {

		List<User> listUsers = userRepository.getUserByName(name);

		List<DtoUser> listDtoUsers = new ArrayList<>();

		for (User user : listUsers) {

			DtoUser dtoUser = findUserById(user.getId());

			listDtoUsers.add(dtoUser);
		}

		return listDtoUsers;
	}

	@Override
	public List<DtoUser> getUserByRole(Long roleId) {

		List<User> listUsers = userRepository.getUserByRole(roleId);

		List<DtoUser> listDtoUsers = new ArrayList<>();

		for (User user : listUsers) {

			DtoUser dtoUser = findUserById(user.getId());

			listDtoUsers.add(dtoUser);
		}

		return listDtoUsers;
	}

}
