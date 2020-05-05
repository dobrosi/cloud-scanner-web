package com.github.dobrosi.cloudscanner.repository;

import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@EntityListeners(AuditingEntityListener.class)
class Barcode {
	private @GeneratedValue @Id Long id;

	@JsonIgnore
	private @CreatedDate LocalDateTime createdDate;

	@ManyToOne
	private BarcodeUser barcodeUser;

	private String value;

	@Embedded
	private GpsInfo gpsInfo;

	public Barcode() {
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public GpsInfo getGpsInfo() {
		return gpsInfo;
	}

	public void setGpsInfo(GpsInfo gpsInfo) {
		this.gpsInfo = gpsInfo;
	}

	public BarcodeUser getBarcodeUser() {
		return barcodeUser;
	}
}