package com.github.dobrosi.cloudscanner.application.controller;

import static com.github.dobrosi.cloudscanner.application.TestHelper.createAdminBarcodeUser;
import static com.github.dobrosi.cloudscanner.application.TestHelper.httpBasicAuth;
import static com.github.dobrosi.cloudscanner.application.TestHelper.performOk;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dobrosi.cloudscanner.repository.Barcode;
import com.github.dobrosi.cloudscanner.repository.BarcodeUser;
import com.github.dobrosi.cloudscanner.repository.BarcodeUserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestBarcodeController {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private BarcodeUserRepository barcodeUserRepository;

	private BarcodeUser adminBarcodeUser;

	@Before
	@Transactional
	public void init() {
		adminBarcodeUser = barcodeUserRepository.save(createAdminBarcodeUser());
	}

	@Test
	public void listBarcodes() throws Exception {
		String source = objectMapper.writeValueAsString(adminBarcodeUser.getBarcodes());

		String barcodes = performOk(mockMvc, httpBasicAuth(get("/barcode/list"), adminBarcodeUser));

		assertThat(barcodes, is(source));
	}

	@Test
	@Transactional
	public void saveBarcode() throws Exception {
		String source = objectMapper.writeValueAsString(new Barcode("test"));

		String result = performOk(mockMvc, httpBasicAuth(put("/barcode/save").contentType(APPLICATION_JSON).content(source), adminBarcodeUser));

		assertEquals(result, "true");
		assertTrue(barcodeUserRepository.findByLoginId(adminBarcodeUser.getLoginId()).getBarcodes().size() == 2);
	}

	@Test
	@Transactional
	public void deleteBarcodes() throws Exception {
		String source = objectMapper.writeValueAsString(adminBarcodeUser.getBarcodes());
		
		String result = performOk(mockMvc, httpBasicAuth(delete("/barcode/delete").contentType(APPLICATION_JSON).content(source), adminBarcodeUser));

		assertEquals(result, "true");
		assertTrue(barcodeUserRepository.findByLoginId(adminBarcodeUser.getLoginId()).getBarcodes().isEmpty());
	}
}
