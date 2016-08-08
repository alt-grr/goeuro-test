package it.sieradzki.goeuro_test;

import it.sieradzki.goeuro_test.model.Location;
import it.sieradzki.goeuro_test.service.ArgumentParserService;
import it.sieradzki.goeuro_test.service.PositionSuggestionsService;
import it.sieradzki.goeuro_test.service.ReaderService;
import it.sieradzki.goeuro_test.service.WriterService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class GoeuroTest {

	private static final String BASE_URL = "http://api.goeuro.com/api/v2/position/suggest/en/";

	private static final ArgumentParserService ARGUMENT_PARSER_SERVICE = new ArgumentParserService();

	private static final ReaderService READER_SERVICE = new ReaderService();

	private static final PositionSuggestionsService POSITION_SUGGESTIONS_SERVICE =
			new PositionSuggestionsService(BASE_URL, READER_SERVICE);

	private static final WriterService WRITER_SERVICE = new WriterService();


	public static void main(String[] args) throws FileNotFoundException {

		String city = null;
		try {
			city = ARGUMENT_PARSER_SERVICE.parseCity(args);
		} catch (IllegalArgumentException e) {
			System.err.println("Error during parsing arguments: " + e.getMessage());
			System.exit(1);
		}


		List<Location> suggestions = null;
		try {
			suggestions = POSITION_SUGGESTIONS_SERVICE.getSuggestions(city);
		} catch (Exception e) {
			System.err.println("Error during reading suggestions: " + e.getMessage());
			System.exit(1);
		}


		try {
			FileOutputStream outputStream = new FileOutputStream(new File(city + ".csv"));
			WRITER_SERVICE.writeAsCsv(suggestions, outputStream);
		} catch (FileNotFoundException e) {
			System.err.println("Error during writing suggestions: " + e.getMessage());
			System.exit(1);
		}
	}
}
