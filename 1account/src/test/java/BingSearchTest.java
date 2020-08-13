import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class BingSearchTest {

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    static BingSearch bingSearch;

    @Mock
    static HttpURLConnection connection;

    @Test
    public void testValidConnection() {
        String response = "{\"url\":\"https:\\/\\/en.wikipedia.org\\/wiki\\/Dog\"}";

        bingSearch = new BingSearch(connection);

        Set<String> expected = new HashSet<>();
        expected.add("https://en.wikipedia.org/wiki/Dog");

        try{
            URL url = new URL("https://api.cognitive.microsoft.com/bing/v7.0/search");

            Mockito.when(connection.getResponseCode()).thenReturn(200);
            Mockito.when(connection.getInputStream()).thenReturn(new ByteArrayInputStream(response.getBytes()));
            Set<String> urls = bingSearch.search("dogs");

            assertEquals(expected, urls);

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Test
    public void testInvalidConnection() {
        try{
            Mockito.when(connection.getResponseCode()).thenReturn(404);
            Set<String> urls = bingSearch.search("dogs");

            assertEquals(null, urls);

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
