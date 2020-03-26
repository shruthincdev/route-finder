package com.route.finder.routefinder.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.route.finder.routefinder.exception.RouteFinderException;
import com.route.finder.routefinder.service.RouteFinderRequestValidator;
import com.route.finder.routefinder.service.RouteFinderService;

import lombok.extern.slf4j.Slf4j;

/**
 * Rest Controller
 * 
 * @author shruthin
 *
 */
@RestController
@Slf4j
public class RouteFinderController {

	@Autowired
	RouteFinderService serivce;

	@Autowired
	RouteFinderRequestValidator validator;

	/**
	 * HTTP Get endpoint to check if origin & destination are connected
	 * 
	 * @param origin
	 * @param destination
	 * @return String (yes/no)
	 */
	@GetMapping("/connected")
	public ResponseEntity<String> checkRoute(@RequestParam String origin, @RequestParam String destination) {
		long startTime = System.currentTimeMillis();
		validator.validateRequestParam(origin, destination);
		return Optional.ofNullable(serivce.isRouteAvailable(origin, destination)).map(availability -> {
			log.info("Time taken to fetch route :: " + (System.currentTimeMillis() - startTime) + " ms");
			return ResponseEntity.ok().body(availability);
		}).orElseThrow(RouteFinderException::new);
	}

}
