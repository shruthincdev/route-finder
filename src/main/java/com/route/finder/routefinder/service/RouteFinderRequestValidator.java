package com.route.finder.routefinder.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.route.finder.routefinder.exception.RouteFinderBadRequestException;

/**
 * Provides utility methods to validate the request parameters
 * 
 * @author shruthin
 *
 */
@Service
public class RouteFinderRequestValidator {

	public void validateRequestParam(String origin, String destination) {
		if (StringUtils.isEmpty(origin)) {
			throw new RouteFinderBadRequestException("origin cannot be empty");
		}

		if (StringUtils.isEmpty(destination)) {
			throw new RouteFinderBadRequestException("destination cannot be empty");
		}
	}

}
