package com.github.dobrosi.cloudscanner.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface BarcodeUserRepository extends CrudRepository<BarcodeUser, Long> {
	@Override
	<S extends BarcodeUser> S save(S entity);

	BarcodeUser findByLoginId(String loginId);

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Override
	Iterable<BarcodeUser> findAll();
}