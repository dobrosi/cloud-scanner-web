package com.github.dobrosi.cloudscanner.repository;

import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;

@Entity
class Barcode {
	private @GeneratedValue @Id Long id;

	private @CreatedDate LocalDateTime createdDate;
	
	private String value;
	
	@Embedded
	private GpsInfo gpsInfo;
	
	public Barcode() {}

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

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
}