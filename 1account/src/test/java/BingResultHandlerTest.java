import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;

public class BingResultHandlerTest {

    @Before
    public void init() {
    }

    static BingResultHandler resultHandler;

    @Test
    public void testURLRemoval() {
        Set<String> input = new HashSet<>();
        input.add("https://google.com");
        input.add("https://bing.com");
        input.add("www.microsoft.com");

        Set<String> expected = new HashSet<>();
        expected.add("https://google.com");

        resultHandler = new BingResultHandler();
        Set<String> urls = resultHandler.removeURLs(input);

        assertEquals(expected, urls);
    }

    @Test
    public void testInvalidConnection() {

    }
}
