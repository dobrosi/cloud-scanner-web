package com.github.dobrosi.cloudscanner.repository;

import org.springframework.data.repository.CrudRepository;

public interface BarcodeUserRepository extends CrudRepository<BarcodeUser, Long> {
	BarcodeUser findByLoginId(String loginId);
}