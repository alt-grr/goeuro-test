package it.sieradzki.goeuro_test.service;

import it.sieradzki.goeuro_test.model.Location;

import java.io.InputStream;
import java.util.List;

public class PositionSuggestionsService {

	private final String baseUrl;

	private final ReaderService readerService;

	public PositionSuggestionsService(String baseUrl, ReaderService readerService) {
		this.baseUrl = baseUrl;
		this.readerService = readerService;
	}

	public List<Location> getSuggestions(String forCity) {
		InputStream inputStream = readerService.readFrom(baseUrl + forCity);
		return readerService.parse(inputStream);
	}
}
