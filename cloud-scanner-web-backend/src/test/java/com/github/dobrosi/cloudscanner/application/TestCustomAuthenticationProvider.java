package com.github.dobrosi.cloudscanner.application;

import static com.github.dobrosi.cloudscanner.application.TestHelper.createAdminBarcodeUser;
import static com.github.dobrosi.cloudscanner.application.TestHelper.createAuthentication;
import static com.github.dobrosi.cloudscanner.application.TestHelper.createNotAdminBarcodeUser;
import static com.github.dobrosi.cloudscanner.repository.BarcodeUser.Role.ROLE_ADMIN;
import static com.github.dobrosi.cloudscanner.repository.BarcodeUser.Role.ROLE_BARCODEUSER;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.dobrosi.cloudscanner.configuration.CustomAuthenticationProvider;
import com.github.dobrosi.cloudscanner.repository.BarcodeUser;
import com.github.dobrosi.cloudscanner.repository.BarcodeUser.Role;
import com.github.dobrosi.cloudscanner.repository.BarcodeUserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCustomAuthenticationProvider {
	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;

	@MockBean
	private BarcodeUserRepository barcodeUserRepository;
	
	@Test
	public void testAuthenticateWithAdmin() {
		when(barcodeUserRepository.findByLoginId(Mockito.anyString())).thenReturn(createAdminBarcodeUser());

		Authentication res = customAuthenticationProvider.authenticate(createAuthentication());

		assertRoles(res, asList(ROLE_BARCODEUSER, ROLE_ADMIN));
	}

	@Test
	public void testAuthenticateWithNotAdmin() {
		when(barcodeUserRepository.findByLoginId(Mockito.anyString())).thenReturn(createNotAdminBarcodeUser());

		Authentication res = customAuthenticationProvider.authenticate(createAuthentication());

		assertRoles(res, asList(ROLE_BARCODEUSER));
	}

	@Test
	public void testAuthenticateWhenNotAuthenticated() {
		when(barcodeUserRepository.findByLoginId(Mockito.anyString())).thenReturn(null);

		assertThrows(UsernameNotFoundException.class, () -> customAuthenticationProvider.authenticate(createAuthentication()));
	}

	private void assertRoles(Authentication res, List<Role> assertedRoles) {
		Assert
				.assertThat(
						res.getAuthorities().stream().map(a -> BarcodeUser.Role.valueOf(a.getAuthority())).collect(toList()),
						Matchers.is(assertedRoles));
	}
}
