package com.route.finder.routefinder.exception;

/**
 * Used for throwing bad request exceptions
 * 
 * @author shruthin
 *
 */
public class RouteFinderBadRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RouteFinderBadRequestException() {
		super();
	}

	public RouteFinderBadRequestException(String message) {
		super(message);
	}

}
