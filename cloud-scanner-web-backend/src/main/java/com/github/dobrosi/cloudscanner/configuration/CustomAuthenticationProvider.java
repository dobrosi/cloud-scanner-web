package com.github.dobrosi.cloudscanner.configuration;

import static com.github.dobrosi.cloudscanner.repository.BarcodeUser.Role.ROLE_ADMIN;
import static com.github.dobrosi.cloudscanner.repository.BarcodeUser.Role.ROLE_BARCODEUSER;
import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
		return authenticate(barcodeUserRepository.findByLoginId(authentication.getName()));
	}

	private Authentication authenticate(BarcodeUser barcodeUser) {
		if (isNull(barcodeUser)) {
			throw new UsernameNotFoundException("User not found.");
		} else {
			Collection<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
			roles.add(createSimpleGrantedAuthority(ROLE_BARCODEUSER));
			return createUsernamePasswordAuthenticationToken(barcodeUser, roles);
		}
	}

	private Authentication createUsernamePasswordAuthenticationToken(BarcodeUser barcodeUser,
			Collection<GrantedAuthority> roles) {
		if (barcodeUser.isAdmin()) {
			roles.add(createSimpleGrantedAuthority(ROLE_ADMIN));
		}
		return new UsernamePasswordAuthenticationToken(barcodeUser, null, roles);
	}

	private SimpleGrantedAuthority createSimpleGrantedAuthority(BarcodeUser.Role role) {
		return new SimpleGrantedAuthority(role.name());
	}
}