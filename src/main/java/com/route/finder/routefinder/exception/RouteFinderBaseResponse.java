package com.route.finder.routefinder.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RouteFinderBaseResponse is used to return a generic response body when an
 * exception happens
 * 
 * @author shruthin
 *
 */
@Getter
@Setter
@Builder
@ToString
public class RouteFinderBaseResponse {

	// error codes in Product Service
	public static final int INVALID_INPUT = 1;
	public static final int INTERNAL_SERVER_ERROR = 2;

	protected boolean success;
	protected String message;
	protected Integer code;
	protected String traceId;

}
