package com.github.dobrosi.cloudscanner.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BarcodeRepository extends CrudRepository<Barcode, Long> {
	List<Barcode> findByBarcodeUser(BarcodeUser barcodeUser);
}