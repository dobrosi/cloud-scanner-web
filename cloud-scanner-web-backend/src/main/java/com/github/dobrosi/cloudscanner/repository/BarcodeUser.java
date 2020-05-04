package com.github.dobrosi.cloudscanner.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
public class BarcodeUser {

	private @GeneratedValue @Id Long id;
	
	private @CreatedDate LocalDateTime createdDate;

	private @Version Long version;

	private @LastModifiedDate LocalDateTime lastModifiedDate;

	private String loginId;

	private String firstName;

	private String lastName;

	private String email;
	
	@OneToMany(orphanRemoval = true)
	private List<Barcode> barcodes;

	public BarcodeUser() {
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	public List<Barcode> getBarcodes() {
		return barcodes;
	}

	public void setBarcodes(List<Barcode> barcodes) {
		this.barcodes = barcodes;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
}
