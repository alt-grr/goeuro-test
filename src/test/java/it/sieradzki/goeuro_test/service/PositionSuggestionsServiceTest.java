package it.sieradzki.goeuro_test.service;

import it.sieradzki.goeuro_test.model.Location;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class PositionSuggestionsServiceTest {

	private static final String BASE_URL = "http://localhost";

	@Mock
	private ReaderService readerService;

	private PositionSuggestionsService objectUnderTest;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);

		objectUnderTest = new PositionSuggestionsService(BASE_URL, readerService);
	}

	@Test
	public void getSuggestionsCallsRightMethodsOnReaderService() throws Exception {

		// given:
		Location location = mock(Location.class);

		ByteArrayInputStream inputStream = new ByteArrayInputStream("test".getBytes());
		when(readerService.readFrom(any(String.class))).thenReturn(inputStream);
		when(readerService.parse(any(InputStream.class))).thenReturn(Collections.singletonList(location));


		// when:
		List<Location> result = objectUnderTest.getSuggestions("Warsaw");


		// then:
		Assert.assertThat("Lists are equal", result, CoreMatchers.hasItems(location));
		verify(readerService, times(1)).readFrom(any(String.class));
		verify(readerService, times(1)).parse(any(InputStream.class));
	}
}
