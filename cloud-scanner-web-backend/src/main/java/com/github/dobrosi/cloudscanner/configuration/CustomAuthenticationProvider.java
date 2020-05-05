package com.github.dobrosi.cloudscanner.configuration;

import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.github.dobrosi.cloudscanner.repository.BarcodeUser;
import com.github.dobrosi.cloudscanner.repository.BarcodeUserRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private BarcodeUserRepository barcodeUserRepository;

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	@Override
	public Authentication authenticate(Authentication authentication)
			throws org.springframework.security.core.AuthenticationException {
		String loginId = authentication.getName();
		BarcodeUser barcodeUser = barcodeUserRepository.findByLoginId(loginId);
		if (isNull(barcodeUser)) {
			return null;
		} else {
			return new UsernamePasswordAuthenticationToken(barcodeUser, null, emptyList());
		}
	}
}