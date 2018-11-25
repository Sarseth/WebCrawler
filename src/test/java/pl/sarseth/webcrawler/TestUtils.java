package pl.sarseth.webcrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class TestUtils {

    public static final String WIKIPEDIA_PAGE = "wikipedia.html";

    public static Document getPage(String page) throws IOException {
        var wikipediaUrl = Thread.currentThread().getContextClassLoader().getResource(WIKIPEDIA_PAGE);
        var file = new File(wikipediaUrl.getPath());
        return Jsoup.parse(file, "UTF-8", "http://www.wikipedia.com");
    }
}
