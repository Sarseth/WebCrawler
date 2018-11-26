package pl.sarseth.webcrawler.page;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Optional;

class PageDownloader {

    private static final String USER_AGENT = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:63.0) Gecko/20100101 Firefox/63.0";

    Optional<Document> downloadPage(String url) {
        try {
            var document = Jsoup.connect(url).userAgent(USER_AGENT).get();
            return Optional.of(document);
        } catch (UnknownHostException e) {
            System.out.println(url + " does not exist.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
