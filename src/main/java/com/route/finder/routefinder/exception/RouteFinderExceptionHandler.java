package com.route.finder.routefinder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller advice class to handle all exceptions and return & set appropriate
 * error messages and http codes
 * 
 * @author shruthin
 *
 */
@ControllerAdvice
@Slf4j
public class RouteFinderExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handles RouteFinderException
	 * 
	 * @param e
	 * @return
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public ResponseEntity<RouteFinderBaseResponse> badRequestException(RouteFinderBadRequestException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(RouteFinderBaseResponse.builder().message(e.getMessage())
						.code(RouteFinderBaseResponse.INVALID_INPUT).success(false)
						.traceId(String.valueOf(System.currentTimeMillis())).build());
	}

	/**
	 * Handles RouteFinderException
	 * 
	 * @param e
	 * @return
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public ResponseEntity<RouteFinderBaseResponse> internalException(RouteFinderException e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(RouteFinderBaseResponse.builder().message(e.getMessage())
						.code(RouteFinderBaseResponse.INTERNAL_SERVER_ERROR).success(false)
						.traceId(String.valueOf(System.currentTimeMillis())).build());
	}

	/**
	 * Handle Generic Exception
	 *
	 * @param e uncertain generic exception
	 * @return response body for generic exception
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<RouteFinderBaseResponse> genericException(Exception e) {
		log.error("Internal server error occured", e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(RouteFinderBaseResponse.builder().message(e.getMessage())
						.code(RouteFinderBaseResponse.INTERNAL_SERVER_ERROR).success(false)
						.traceId(String.valueOf(System.currentTimeMillis())).build());
	}

}
