package com.route.finder.routefinder.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.route.finder.routefinder.constants.RouteFinderErrorMessages;
import com.route.finder.routefinder.exception.RouteFinderBadRequestException;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Setter
public class RouteUtility {

	private final Map<String, Set<String>> lkpMap = new HashMap<String, Set<String>>();

	@Value("${FEED_FILE_PATH}")
	String feedFilePath;

	/**
	 * Loads the input file to HashMap during the spring application context
	 * initialization
	 */
	@PostConstruct
	public void fetchConnectedNodes() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(feedFilePath));
			String line;
			while ((line = br.readLine()) != null && line.length() != 0) {
				/**
				 * Splitting the line based on comma (,)
				 */
				String[] attr = line.trim().split(",");

				/**
				 * Inserting both lookups since if there is a route between A -> B then there
				 * will also be a route between B -> A
				 */
				putValue(attr[0].trim(), attr[1].trim());
				putValue(attr[1].trim(), attr[0].trim());
			}
			br.close();
		} catch (FileNotFoundException e) {
			log.error(RouteFinderErrorMessages.FILE_NOT_FOUND_EXCEPTION);
			throw new RouteFinderBadRequestException(RouteFinderErrorMessages.FILE_NOT_FOUND_EXCEPTION);
		} catch (IOException e) {
			log.error(RouteFinderErrorMessages.IO_EXCEPTION);
			throw new RouteFinderBadRequestException(RouteFinderErrorMessages.IO_EXCEPTION);
		}
	}

	/**
	 * Checks and adds the source and destination to the lkpMap
	 * 
	 * @param source
	 * @param destination
	 */
	public void putValue(String source, String destination) {
		Set<String> target = lkpMap.get(source);
		if (target == null) {
			target = new HashSet<String>();
			target.add(destination);
			lkpMap.put(source, target);
		} else {
			target.add(destination);
		}
	}

	/**
	 * Checks if node1 is connected with node2
	 * 
	 * @param node1
	 * @param node2
	 * @return true if node is connected else returns false
	 */
	public boolean isConnected(String node1, String node2) {

		log.info("Checking if {} and {} are connected", node1, node2);

		/**
		 * If node1 == node2 then route is not required. Hence returning true
		 */
		if (node1 == node2) {
			return true;
		}

		HashSet<String> citiesChecked = new HashSet<String>();

		/**
		 * Adding both node1 and node2 for citiesToCheck since to support if there is a
		 * route between node1 to node2 then there will also be a route between node2 to
		 * node1
		 */
		Queue<String> citiesToCheck = new LinkedList<String>();
		citiesToCheck.add(node1);
		citiesToCheck.add(node2);

		// loop until all cities are checked
		while (citiesToCheck.size() != 0) {
			String currentlyChekingCity = citiesToCheck.poll();
			if (isDirectlyConnected(currentlyChekingCity, node2)) {
				return true;
			} else {
				citiesChecked.add(currentlyChekingCity);
				if (lkpMap.get(currentlyChekingCity) != null) {
					for (String neighbour : lkpMap.get(currentlyChekingCity)) {
						if (!citiesChecked.contains(neighbour)) {
							citiesToCheck.add(neighbour);
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Returns true if there is a direct connection between node1 and node2
	 * 
	 * @param node1
	 * @param node2
	 * @return
	 */
	private boolean isDirectlyConnected(String node1, String node2) {
		return (lkpMap.get(node1) != null && lkpMap.get(node1).contains(node2))
				|| (lkpMap.get(node2) != null && lkpMap.get(node2).contains(node1));
	}

}