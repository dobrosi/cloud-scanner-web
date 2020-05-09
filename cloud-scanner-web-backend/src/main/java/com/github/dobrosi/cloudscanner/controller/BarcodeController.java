package com.github.dobrosi.cloudscanner.controller;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.dobrosi.cloudscanner.repository.Barcode;
import com.github.dobrosi.cloudscanner.repository.BarcodeUser;
import com.github.dobrosi.cloudscanner.repository.BarcodeUserRepository;

@CrossOrigin
@RestController
@Transactional
public class BarcodeController {
	@Autowired
	private BarcodeUserRepository barcodeUserRepository;
	
	@GetMapping("/barcode/list")
	public ResponseEntity<List<Barcode>> list() {
		return ok(getBarcodes());
	}

	@PutMapping("/barcode/save")
	public ResponseEntity<Boolean> save(@RequestBody @Valid Barcode barcode) {
		return ok(getBarcodes().add(barcode));
	}
	
	@DeleteMapping("/barcode/delete")
	public ResponseEntity<Boolean> delete(@RequestBody @Valid Collection<Barcode> barcodes) {
		return ok(getBarcodes().removeAll(barcodes));
	}

	private List<Barcode> getBarcodes() {
		return getBarcodeUser().getBarcodes();
	}

	private BarcodeUser getBarcodeUser() {
		return barcodeUserRepository
				.findByLoginId(((BarcodeUser) getContext().getAuthentication().getPrincipal()).getLoginId());
	}
}