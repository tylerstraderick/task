import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultPageService {

    //leaving this here so you can see what I was trying to do
    public static ArrayList<String> getLibrariesFromPage(String resultUrl) {

        HttpURLConnection connection;
        ArrayList<String> urls = new ArrayList<String>();
        BufferedReader reader;
        String line;
        Pattern urlRegex = Pattern.compile("https:?\\/\\/[-a-zA-z\\.]+.([a-zA-Z]+\\.[a-zA-Z]+)\\/([\\.A-Za-z0-9_~!*''();:@&=+$,/?#\\[%-\\]+]+.js)+");
        Pattern scriptTagRegex = Pattern.compile("<script");

        try {
            URL url = new URL(resultUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:77.0) Gecko/20100101 Firefox/77.0");

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((line = reader.readLine()) != null) {

                String unescapedLine = line.replace("\\", "");
                Matcher tagMatch = scriptTagRegex.matcher(unescapedLine);
                Matcher urlMatch = urlRegex.matcher(unescapedLine);

                if(tagMatch.find() && urlMatch.find()) {
                    int urlStart = urlMatch.start();
                    int urlEnd = urlMatch.end();
                    urls.add(unescapedLine.substring(urlStart, urlEnd));
                    System.out.println(unescapedLine.substring(urlStart, urlEnd));
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return urls;
    }
}
