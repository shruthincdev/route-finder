package com.route.finder.routefinder.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class RouteFinderRequestValidatorTest {

	private RouteFinderRequestValidator validator;

	@BeforeAll
	public void init() {
		validator = new RouteFinderRequestValidator();
	}

	@Test
	public void validateRequestParam_happyPath() {
		try {
			validator.validateRequestParam("New York", "Charlotte");
		} catch (Exception e) {
			Assert.fail();
		}
	}

	@Test
	public void validateRequestParam_EmptyOrigin() {
		try {
			validator.validateRequestParam("", "Charlotte");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(e.getMessage(), "origin cannot be empty");
		}
	}

	@Test
	public void validateRequestParam_EmptyDestination() {
		try {
			validator.validateRequestParam("Charlotte", "");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(e.getMessage(), "destination cannot be empty");
		}
	}

}
