package com.github.dobrosi.cloudscanner.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.dobrosi.cloudscanner.repository.BarcodeUser;
import com.github.dobrosi.cloudscanner.repository.BarcodeUserRepository;

@RestController
public class Registration {
	@Autowired
	private BarcodeUserRepository barcodeUserRepository;

	@PostMapping("/signUp")
	public ResponseEntity<String> signUp(@RequestBody @Valid BarcodeUser barcodeUser) {
		barcodeUser = barcodeUserRepository.save(barcodeUser);
		return ResponseEntity.ok(barcodeUser.getLoginId());
	}
}