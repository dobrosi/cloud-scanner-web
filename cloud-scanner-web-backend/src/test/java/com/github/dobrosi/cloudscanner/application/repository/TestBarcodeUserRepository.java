package com.github.dobrosi.cloudscanner.application.repository;

import static com.github.dobrosi.cloudscanner.repository.BarcodeUserFactory.create;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.dobrosi.cloudscanner.repository.BarcodeUser;
import com.github.dobrosi.cloudscanner.repository.BarcodeUserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBarcodeUserRepository {
	@Autowired
	private BarcodeUserRepository barcodeUserRepository;

	@Test
	public void testSave() {
		barcodeUserRepository.save(createBarcodeUser("goodEmail@test.com", "Elek", "Teszt"));
	}

	@Test
	public void testSaveWithWrongEmailShouldExcpetion() {
		assertThrows(RuntimeException.class, () -> barcodeUserRepository.save(createBarcodeUser("wrongEmail", "Elek", "Teszt")));
	}

	private BarcodeUser createBarcodeUser(String email, String firstName, String lastName) {
		return create().withEmail(email).withFirstName(firstName).withLastName(lastName).asAdmin().build();
	}
}
