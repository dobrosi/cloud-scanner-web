package com.github.dobrosi.cloudscanner.application.controller;

import static com.github.dobrosi.cloudscanner.application.TestHelper.createAdminBarcodeUser;
import static com.github.dobrosi.cloudscanner.application.TestHelper.httpBasicAuth;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.github.dobrosi.cloudscanner.repository.BarcodeUser;
import com.github.dobrosi.cloudscanner.repository.BarcodeUserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestIndexController {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private BarcodeUserRepository barcodeUserRepository;

	private BarcodeUser adminBarcodeUser;

	@Before
	@Transactional
	public void init() {
		adminBarcodeUser = barcodeUserRepository.save(createAdminBarcodeUser());
	}

	@Test
	public void index() throws Exception {
		mockMvc.perform(httpBasicAuth(get("/"), adminBarcodeUser)).andDo(print()).andExpect(status().isOk());
	}
}
