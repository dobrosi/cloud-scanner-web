package com.github.dobrosi.cloudscanner.repository;

public class BarcodeUserFactory {
	private BarcodeUser barcodeUser;

	private BarcodeUserFactory() {
		barcodeUser = new BarcodeUser();
	}

	public static BarcodeUserFactory create() {
		return new BarcodeUserFactory();
	}

	public BarcodeUserFactory createBarcodeUser() {
		return this;
	}

	public BarcodeUserFactory withEmail(String email) {
		barcodeUser.setEmail(email);
		return this;
	}

	public BarcodeUserFactory withFirstName(String firstName) {
		barcodeUser.setFirstName(firstName);
		return this;
	}

	public BarcodeUserFactory withLastName(String lastName) {
		barcodeUser.setLastName(lastName);
		return this;
	}

	public BarcodeUserFactory asAdmin() {
		barcodeUser.setAdmin(true);
		return this;
	}
	
	public BarcodeUserFactory addBarcode(String value) {
		barcodeUser.getBarcodes().add(new Barcode(value));
		return this;
	}

	public BarcodeUser build() {
		return barcodeUser;
	}
}
