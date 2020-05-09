package com.github.dobrosi.cloudscanner.application.controller;

import static com.github.dobrosi.cloudscanner.application.TestHelper.createNotAdminBarcodeUser;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dobrosi.cloudscanner.application.TestHelper;
import com.github.dobrosi.cloudscanner.repository.BarcodeUserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestRegistrationController {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private BarcodeUserRepository barcodeUserRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void signup() throws Exception {
		String source = objectMapper.writeValueAsString(createNotAdminBarcodeUser());

		String loginId = TestHelper.performOk(mockMvc, post("/signUp").contentType(APPLICATION_JSON).content(source));

		String saved = objectMapper.writeValueAsString(barcodeUserRepository.findByLoginId(loginId));
		Assert.assertThat(saved, is(source));
	}
}
