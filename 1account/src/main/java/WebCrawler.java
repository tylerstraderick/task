import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class WebCrawler {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BingResultHandler resultHandler = new BingResultHandler();

        try {
            //set url and open connection as input to BingSearch class
            URL url = new URL("https://api.cognitive.microsoft.com/bing/v7.0/search");
            BingSearch bing = new BingSearch((HttpURLConnection) url.openConnection());

            //read from stdin
            String searchTerm = reader.readLine();

            //get urls from bing search
            Set<String> urls = bing.search(searchTerm);
            //remove bing and microsoft related urls
            urls = resultHandler.removeURLs(urls);
            //calls result pages and returns library urls
            urls = resultHandler.retrieveLibraryUrls(urls);

            Map<String, Integer> resultMap = new HashMap<String, Integer>();

            //iterates through library urls, grabs last section of the url and delimits to get the single library name
            Iterator<String> iterator = urls.iterator();
            while (iterator.hasNext()) {
                String[] strArr = iterator.next().split("/");
                String libraryFull = strArr[strArr.length - 1];
                String libraryName = libraryFull.split("\\.|-|_")[0];
                resultMap.put(libraryName, resultMap.getOrDefault(libraryName, 0) + 1);
            }

            //not ordered, but iterates over map and outputs library name to stdout
            for(Map.Entry<String, Integer> entry : resultMap.entrySet()) {
                System.out.println(entry.getKey());
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
