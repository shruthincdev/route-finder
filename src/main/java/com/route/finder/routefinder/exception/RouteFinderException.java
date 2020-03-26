package com.route.finder.routefinder.exception;

/**
 * Used for throwing bad request exceptions
 * 
 * @author shruthin
 *
 */
public class RouteFinderException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RouteFinderException() {
		super();
	}

	public RouteFinderException(String message) {
		super(message);
	}

}
