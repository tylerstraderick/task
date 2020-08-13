import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BingSearch {

    BingSearch(HttpURLConnection connection) {
        this.connection = connection;
    }

    static HttpURLConnection connection;
    static String apiKey = "7182dc4f9db84c839d2c04f93c28da5a";

    public static Set<String> search(String searchTerm) {
        Set<String> urls = new HashSet<String>();
        BufferedReader reader;
        String line;
        //regex for urls
        Pattern urlRegex = Pattern.compile("https:?\\/\\/[-a-zA-z\\.]{1,}.([a-zA-Z]{1,}\\.[a-zA-Z]{1,})\\/([\\.A-Za-z0-9_~!*''();:@&=+$,/?#\\[%-\\]+]{1,})*");

        try {
            connection.setRequestProperty("Ocp-Apim-Subscription-Key", apiKey);

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    String unescapedLine = line.replace("\\", "");
                    Matcher matcher = urlRegex.matcher(unescapedLine);
                    //using regex to find lines with urls, using start and end of match to get the url as a sunstring and adding it to a set
                    while (matcher.find()) {
                        int urlStart = matcher.start();
                        int urlEnd = matcher.end();
                        urls.add(unescapedLine.substring(urlStart, urlEnd));
                    }
                }
                reader.close();
            } else {
                System.out.println("Error connecting to Bing API");
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return urls;
    }
}
