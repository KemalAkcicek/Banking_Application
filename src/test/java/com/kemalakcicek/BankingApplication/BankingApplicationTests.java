package com.kemalakcicek.BankingApplication;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kemalakcicek.dto.DtoRole;
import com.kemalakcicek.dto.DtoRoleSave;
import com.kemalakcicek.dto.DtoUserRegister;
import com.kemalakcicek.service.IAccountService;
import com.kemalakcicek.service.IRoleService;
import com.kemalakcicek.service.impl.AuthServiceImpl;
import com.kemalakcicek.starter.BankingApplicationStarter;

import jakarta.transaction.Transactional;

//Test işlemi sırasında Spring Boot, Hibernate Session'ı açmaz sadece test işlemlerinde session açması için @Transactional kullandık
//Bu şekilde Lazy loading hatası almaktan kurtulduk
@SpringBootTest(classes = { BankingApplicationStarter.class })
@Transactional
class BankingApplicationTests {

	@Autowired
	private IAccountService iAccountService;

	@Autowired
	private IRoleService iRoleService;

	@Autowired
	private AuthServiceImpl authServiceImpl;

	@BeforeEach
	public void beforEach() {
		System.out.println("Before each çalıştı");
	}

	@Test
	public void testAuthRegister() {

		DtoUserRegister dtoUserRegister = new DtoUserRegister();
		dtoUserRegister.setEMail("kemal@gmail.com");
		dtoUserRegister.setFirstName("Kemal");
		dtoUserRegister.setLastName("Akçiçek");
		dtoUserRegister.setPassword("kemal.44");
		dtoUserRegister.setPhoneNumber("123456789");

		DtoRole dtoRole = iRoleService.findRoleById(2L);

		dtoUserRegister.setRole(dtoRole);

		authServiceImpl.register(dtoUserRegister);

	}

	@Test
	public void testSaveRole() {

		DtoRoleSave dtoRole = new DtoRoleSave();

		dtoRole.setRoleName("doktor");

		iRoleService.saveRole(dtoRole);

		assertNotNull(dtoRole);

	}

	@AfterEach
	public void afterEach() {
		System.out.println("After each çalıştı");
	}

}
