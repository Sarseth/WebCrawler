package pl.sarseth.webcrawler.page.link;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@DisplayName("Verification that link searcher can find links")
class LinkSearcherTest {

    private static final String WIKIPEDIA_PAGE = "wikipedia.html";

    private LinkSearcher linkSearcher = new LinkSearcher();

    @Test
    @DisplayName("Check if links in wikipage can be found")
    void findAllLinksInHtmlPage() throws IOException {
        // GIVEN
        var wikipedia = getWikipediaPage();
        var expectedNumberOfLinks = 320;

        // WHEN
        var allLinksInDocument = linkSearcher.findAllLinksInDocument(wikipedia);

        // THEN
        assertThat(allLinksInDocument, hasSize(expectedNumberOfLinks));
    }

    @Test
    @DisplayName("Null proof test")
    void nullProofTest() {
        // GIVEN
        // WHEN
        var allLinksInDocument = linkSearcher.findAllLinksInDocument(null);

        // THEN
        assertThat(allLinksInDocument, hasSize(0));
    }

    private Document getWikipediaPage() throws IOException {
        var wikipediaUrl = Thread.currentThread().getContextClassLoader().getResource(WIKIPEDIA_PAGE);
        var file = new File(wikipediaUrl.getPath());
        return Jsoup.parse(file, "UTF-8", "http://www.wikipedia.com");
    }

}
