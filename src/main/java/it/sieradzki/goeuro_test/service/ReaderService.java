package it.sieradzki.goeuro_test.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.sieradzki.goeuro_test.model.Location;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ReaderService {

	public InputStream readFrom(String url) {

		URL parsedUrl;
		try {
			parsedUrl = new URL(url);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException("Invalid URL: " + url);
		}

		try {
			return parsedUrl.openStream();
		} catch (IOException e) {
			throw new ConnectionException(e);
		}
	}

	public List<Location> parse(InputStream inputStream) {

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(inputStream, new TypeReference<List<Location>>() {
			});
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
