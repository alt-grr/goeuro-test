package it.sieradzki.goeuro_test.service;

import it.sieradzki.goeuro_test.model.GeoPosition;
import it.sieradzki.goeuro_test.model.Location;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class WriterServiceTest {

	private final WriterService objectUnderTest = new WriterService();

	@Test
	public void writeCsvFileWithNoRecords() throws Exception {

		// given:
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		// when:
		objectUnderTest.writeAsCsv(new ArrayList<>(), outputStream);

		// then:
		Assert.assertEquals("_id,name,type,latitude,longitude", outputStream.toString().trim());
	}

	@Test
	public void writeCsvFileWithOneRecord() throws Exception {

		// given:
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		GeoPosition geoPosition = new GeoPosition();
		geoPosition.setLatitude(10.1d);
		geoPosition.setLongitude(15.1d);

		Location location = new Location();
		location.setId(17);
		location.setName("someLocation");
		location.setType("SOME_TYPE");
		location.setGeoPosition(geoPosition);

		// when:
		objectUnderTest.writeAsCsv(Collections.singletonList(location), outputStream);

		// then:
		Assert.assertEquals(
				"_id,name,type,latitude,longitude\r\n" + "17,someLocation,SOME_TYPE,10.1,15.1",
				outputStream.toString().trim()
		);
	}
}
