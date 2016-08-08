package it.sieradzki.goeuro_test.service;

import org.junit.Assert;
import org.junit.Test;

public class ArgumentParserServiceTest {

	private final ArgumentParserService objectUnderTest = new ArgumentParserService();

	@Test
	public void parseValidArguments() throws Exception {

		// given:
		String cityName = "Warsaw";
		String[] args = {cityName};

		// when:
		String parsedCityName = objectUnderTest.parseCity(args);

		// then:
		Assert.assertEquals(cityName, parsedCityName);
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwExceptionOnEmptyArgumentsList() throws Exception {

		// given:
		String[] args = {};

		// when:
		objectUnderTest.parseCity(args);

		// then:
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwExceptionOnTooLongArgumentsList() throws Exception {

		// given:
		String[] args = {"a", "b", "c"};

		// when:
		objectUnderTest.parseCity(args);

		// then:
	}
}
