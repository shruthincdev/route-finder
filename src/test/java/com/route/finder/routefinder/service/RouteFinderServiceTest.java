package com.route.finder.routefinder.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.route.finder.routefinder.utility.RouteUtility;

@ExtendWith(SpringExtension.class)
public class RouteFinderServiceTest {

	@Mock
	private RouteUtility routeUtility;

	@InjectMocks
	private RouteFinderService omniItemIdServiceImpl;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testisRouteAvailable_happyPath_Positive() {
		Mockito.when(routeUtility.isConnected("Charlotte", "New York")).thenReturn(true);
		Assert.assertEquals("yes", omniItemIdServiceImpl.isRouteAvailable("Charlotte", "New York"));
	}

	@Test
	public void testisRouteAvailable_happyPath_Negative() {
		Mockito.when(routeUtility.isConnected("Charlotte", "New York")).thenReturn(false);
		Assert.assertEquals("no", omniItemIdServiceImpl.isRouteAvailable("Charlotte", "New York"));
	}

}
