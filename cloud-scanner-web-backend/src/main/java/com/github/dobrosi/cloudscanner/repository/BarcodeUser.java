package com.github.dobrosi.cloudscanner.repository;

import static java.lang.System.currentTimeMillis;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hashids.Hashids;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class BarcodeUser {
	public enum Role {
		ROLE_ADMIN, ROLE_BARCODEUSER
	}
	
	private @GeneratedValue @Id Long id;

	private @Version Long version;

	@JsonIgnore
	private @CreatedDate LocalDateTime createdDate;

	@JsonIgnore
	private @LastModifiedDate LocalDateTime lastModifiedDate;

	@JsonIgnore
	private String loginId;

	@NotBlank
	@Pattern(regexp = "\\w*")
	private String firstName;

	@NotBlank
	@Pattern(regexp = "\\w*")
	private String lastName;

	@NotBlank
	@Email
	private String email;

	@JsonIgnore
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Barcode> barcodes = new ArrayList<Barcode>();

	@JsonIgnore
	private boolean admin;

	public BarcodeUser() {
	}

	@PrePersist
	protected void generateLoginId() {
		Hashids hashids = new Hashids("this is my salt");
		loginId = hashids.encode(currentTimeMillis());
	}

	public String getLoginId() {
		return loginId;
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

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	@Override
	public String toString() {
		return loginId;
	}
}
