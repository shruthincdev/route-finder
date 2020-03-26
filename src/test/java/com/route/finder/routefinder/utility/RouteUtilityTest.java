package com.route.finder.routefinder.utility;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.route.finder.routefinder.constants.RouteFinderErrorMessages;

@ExtendWith(SpringExtension.class)
public class RouteUtilityTest {

	@Test
	public void testisConnected_happyPath() {
		RouteUtility routeUtility = new RouteUtility();
		routeUtility.setFeedFilePath("src/test/java/com/route/finder/routefinder/utility/input-test");
		routeUtility.fetchConnectedNodes();
		Assert.assertTrue(routeUtility.isConnected("A", "B"));
		Assert.assertTrue(routeUtility.isConnected("A", "A"));
		Assert.assertTrue(routeUtility.isConnected("A", "Z"));
		Assert.assertFalse(routeUtility.isConnected("A", "P"));
		Assert.assertTrue(routeUtility.isConnected("Z", "A"));
		Assert.assertTrue(routeUtility.isConnected("F", "A"));
	}

	@Test
	public void testisConnected_FileNotFount() {
		try {
			RouteUtility routeUtility = new RouteUtility();
			routeUtility.setFeedFilePath("src/test/java/com/route/finder/routefinder/utility/input-test-dummy");
			routeUtility.fetchConnectedNodes();
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(RouteFinderErrorMessages.FILE_NOT_FOUND_EXCEPTION, e.getMessage());
		}
	}

}
