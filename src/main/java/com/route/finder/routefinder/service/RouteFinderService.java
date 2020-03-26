package com.route.finder.routefinder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.route.finder.routefinder.constants.RouteFinderConstants;
import com.route.finder.routefinder.utility.RouteUtility;

import lombok.extern.slf4j.Slf4j;

/**
 * RouteFinderService provides utility methods to check the routing availability
 * 
 * @author shruthin
 *
 */
@Service
@Slf4j
public class RouteFinderService {

	@Autowired
	RouteUtility routeUtility;

	/**
	 * Checks if route is available by calling the utility class.
	 * 
	 * @param origin
	 * @param destination
	 * @return yes if route is found else returns false
	 */
	public String isRouteAvailable(String origin, String destination) {
		log.info("Fetching route between origin {} & destination {} ", origin, destination);

		if (routeUtility.isConnected(origin, destination)) {
			return RouteFinderConstants.YES;
		}
		return RouteFinderConstants.NO;
	}

}
