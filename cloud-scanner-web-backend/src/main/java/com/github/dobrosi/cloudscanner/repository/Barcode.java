package com.github.dobrosi.cloudscanner.repository;

import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Barcode {
	private @GeneratedValue @Id Long id;

	@JsonIgnore
	private @CreatedDate LocalDateTime createdDate;

	private String value;

	@Embedded
	private GpsInfo gpsInfo;

	public Barcode() {}

	public Barcode(String value) {
		this();
		this.value = value;
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

	public Long getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Barcode)) {
			return false;
		}
		Barcode that = (Barcode) obj;
		return new EqualsBuilder().append(this.id, that.id).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).toHashCode();
	}
}
