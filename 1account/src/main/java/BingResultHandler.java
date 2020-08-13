import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BingResultHandler {

    static public ResultPageService getPageService = new ResultPageService();

    //checks to see if it contains bing or microsoft and removes the url from the collection if it does
    public static Set<String> removeURLs(Set<String> urls) {
        Iterator<String> iterator = urls.iterator();
        while(iterator.hasNext()) {
            String currString = iterator.next();
            if(currString.contains("bing") || currString.contains("microsoft")) {
               iterator.remove();
            }
        }
        return urls;
    }

    //skipped this step so fudged data
    public static Set<String> retrieveLibraryUrls(Set<String> urls) {
        /*Iterator<String> iterator = urls.iterator();
        while(iterator.hasNext()) {
            ResultPageService.getLibrariesFromPage(iterator.next());
        }*/
        Set<String> libarayURLs = new HashSet<>();
        libarayURLs.add("https://cdnjs.cloudflare.com/ajax/libs/vue/2.6.11/vue.min.js");
        libarayURLs.add("https://cdnjs.cloudflare.com/ajax/libs/vue/2.6.11/vue.common.dev.js");
        libarayURLs.add("https://cdnjs.cloudflare.com/ajax/libs/vue/2.6.11/vue.common.js");
        libarayURLs.add("https://cdnjs.cloudflare.com/ajax/libs/react/16.13.1/umd/react.production.min.js");
        libarayURLs.add("https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js");
        libarayURLs.add("https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js");
        libarayURLs.add("https://cdnjs.cloudflare.com/ajax/libs/ember.js/2.18.2/ember.min.js");
        libarayURLs.add("https://cdnjs.cloudflare.com/ajax/libs/ember.js/2.18.2/ember-runtime.js");
        libarayURLs.add("https://cdnjs.cloudflare.com/ajax/libs/ember.js/2.18.2/ember-template-compiler.js");
        libarayURLs.add("https://cdnjs.cloudflare.com/ajax/libs/ember.js/2.18.2/ember-tests.js");
        libarayURLs.add("https://cdnjs.cloudflare.com/ajax/libs/socket.io/2.3.0/socket.io.js");
        return libarayURLs;

    }
}
