package com.github.dobrosi.cloudscanner.application;

import static com.github.dobrosi.cloudscanner.repository.BarcodeUserFactory.create;
import static java.lang.String.format;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.Base64Utils.encodeToString;

import java.io.UnsupportedEncodingException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.github.dobrosi.cloudscanner.repository.BarcodeUser;
import com.github.dobrosi.cloudscanner.repository.BarcodeUserFactory;

public class TestHelper {

	public static BarcodeUser createAdminBarcodeUser() {
		return createBarcodeUserFactory().asAdmin().build();
	}

	public static BarcodeUser createNotAdminBarcodeUser() {
		return createBarcodeUserFactory().build();
	}

	public static BarcodeUserFactory createBarcodeUserFactory() {
		return create().withEmail("email@email.com").withFirstName("Elek").withLastName("Teszt").addBarcode("123456789");
	}

	public static Authentication createAuthentication() {
		return new UsernamePasswordAuthenticationToken("user", null);
	}

	public static MockHttpServletRequestBuilder httpBasicAuth(
			MockHttpServletRequestBuilder httpServletRequestBuilder,
			BarcodeUser barcodeUser) {
		return httpBasicAuth(httpServletRequestBuilder, barcodeUser.getLoginId());
	}

	public static MockHttpServletRequestBuilder httpBasicAuth(MockHttpServletRequestBuilder mockHttpServletRequestBuilder, String loginId) {
		return mockHttpServletRequestBuilder.header(AUTHORIZATION, "Basic " + encodeToString(format("%s:", loginId).getBytes()));
	}

	public static String performOk(MockMvc mockMvc, MockHttpServletRequestBuilder mockHttpServletRequestBuilder)
			throws UnsupportedEncodingException, Exception {
		return mockMvc
				.perform(mockHttpServletRequestBuilder)
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
	}
}
