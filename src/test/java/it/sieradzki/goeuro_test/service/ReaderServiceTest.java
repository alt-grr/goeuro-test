package it.sieradzki.goeuro_test.service;

import it.sieradzki.goeuro_test.model.GeoPosition;
import it.sieradzki.goeuro_test.model.Location;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ReaderServiceTest {

	private final ReaderService objectUnderTest = new ReaderService();

	@Test(expected = IllegalArgumentException.class)
	public void readFromFailsOnInvalidUrl() throws Exception {

		// given:

		// when:
		objectUnderTest.readFrom("xyz://bad.url");
	}

	@Test(expected = ConnectionException.class)
	public void readFromFailsOnConnectionError() throws Exception {

		// given:

		// when:
		objectUnderTest.readFrom("http://localhost/__bad-local-url");
	}

	@Test
	public void parseValidJsonWithTwoLocationsIgnoringUnknownProperties() throws Exception {

		// given:
		String testInputData = "[{\"_id\":398532,\"name\":\"Warsaw\",\"type\":\"location\",\"iata_airport_code\":null," +
				"\"geo_position\":{\"latitude\":52.22977,\"longitude\":21.01178}}," +
				"{\"_id\":409912,\"name\":\"Warsaw, USA\",\"type\":\"location\",\"inEurope\":false," +
				"\"geo_position\":{\"latitude\":41.2381,\"longitude\":-85.85305}}]";
		byte[] testDataAsBytes = testInputData.getBytes(StandardCharsets.UTF_8);

		Location warsawPoland = createLocation(398532, "Warsaw", "location", 52.22977, 21.01178);
		Location warsawUsa = createLocation(409912, "Warsaw, USA", "location", 41.2381, -85.85305);

		// when:
		List<Location> result = objectUnderTest.parse(new ByteArrayInputStream(testDataAsBytes));


		// then:
		Assert.assertThat("Lists are equal", result, CoreMatchers.hasItems(warsawPoland, warsawUsa));
	}

	private Location createLocation(long id, String name, String type, double latitude, double longitude) {

		GeoPosition geoPosition = new GeoPosition();
		geoPosition.setLatitude(latitude);
		geoPosition.setLongitude(longitude);

		Location location = new Location();
		location.setId(id);
		location.setName(name);
		location.setType(type);
		location.setGeoPosition(geoPosition);

		return location;
	}
}
