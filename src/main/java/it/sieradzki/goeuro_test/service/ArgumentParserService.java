package it.sieradzki.goeuro_test.service;

public class ArgumentParserService {

	public String parseCity(String[] args) throws IllegalArgumentException {

		if (args.length != 1) {
			throw new IllegalArgumentException(
					"Too few or too many arguments - excepted 1, but found " + args.length);
		}

		String cityName = args[0].trim();
		if (cityName.isEmpty()) {
			throw new IllegalArgumentException("Given city name cannot be empty");
		}

		return cityName;
	}
}
