package com.route.finder.routefinder.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.route.finder.routefinder.constants.RouteFinderConstants;
import com.route.finder.routefinder.exception.RouteFinderBadRequestException;
import com.route.finder.routefinder.service.RouteFinderRequestValidator;
import com.route.finder.routefinder.service.RouteFinderService;

@ExtendWith(SpringExtension.class)
@WebMvcTest({ RouteFinderController.class })
public class RouteFinderControllerTest {

	@MockBean
	private RouteFinderService serivce;

	@MockBean
	RouteFinderRequestValidator validator;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testController_checkRoute_happyPath() throws Exception {
		Mockito.doNothing().when(validator).validateRequestParam(Mockito.anyString(), Mockito.anyString());
		Mockito.when(serivce.isRouteAvailable("New York", "Charlotte")).thenReturn(RouteFinderConstants.YES);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/connected").param("origin", "New York").param("destination", "Charlotte"))
				.andDo(print()).andExpect(status().is(200));
	}

	@Test
	public void testController_checkRoute_invalidDestination() throws Exception {
		Mockito.doThrow(new RouteFinderBadRequestException("Bad request")).when(validator)
				.validateRequestParam("Charlotte", "");
		mockMvc.perform(MockMvcRequestBuilders.get("/connected").param("origin", "Charlotte").param("destination", ""))
				.andDo(print()).andExpect(status().is(400));
	}

	@Test
	public void testController_checkRoute_invalidOrigin() throws Exception {
		Mockito.doThrow(new RouteFinderBadRequestException("Bad request")).when(validator).validateRequestParam("",
				"Charlotte");
		mockMvc.perform(MockMvcRequestBuilders.get("/connected").param("origin", "").param("destination", "Charlotte"))
				.andDo(print()).andExpect(status().is(400));
	}

	@Test
	public void testController_checkRoute_internalServerException() throws Exception {
		Mockito.doNothing().when(validator).validateRequestParam(Mockito.anyString(), Mockito.anyString());
		Mockito.when(serivce.isRouteAvailable("New York", "Charlotte")).thenThrow(new RuntimeException());
		mockMvc.perform(
				MockMvcRequestBuilders.get("/connected").param("origin", "New York").param("destination", "Charlotte"))
				.andDo(print()).andExpect(status().is(500));
	}

}
