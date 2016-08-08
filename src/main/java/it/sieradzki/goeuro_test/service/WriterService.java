package it.sieradzki.goeuro_test.service;

import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;
import it.sieradzki.goeuro_test.model.GeoPosition;
import it.sieradzki.goeuro_test.model.Location;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class WriterService {

	public void writeAsCsv(List<Location> locations, OutputStream outputStream) {

		CsvWriterSettings settings = new CsvWriterSettings();
		settings.setHeaders("_id", "name", "type", "latitude", "longitude");
		CsvWriter writer = new CsvWriter(outputStream, StandardCharsets.UTF_8, settings);

		writer.writeHeaders();
		for (Location location : locations) {
			writeRow(writer, location);
		}
		writer.close();
	}

	private void writeRow(CsvWriter writer, Location location) {

		writer.addValue(location.getId());
		writer.addValue(location.getName());
		writer.addValue(location.getType());

		GeoPosition geoPosition = location.getGeoPosition();
		writer.addValue(geoPosition.getLatitude());
		writer.addValue(geoPosition.getLongitude());

		writer.writeValuesToRow();
	}
}
