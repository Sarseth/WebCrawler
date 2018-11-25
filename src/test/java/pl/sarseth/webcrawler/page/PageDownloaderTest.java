package pl.sarseth.webcrawler.page;


import org.jsoup.nodes.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("Confirmation that pages are downloaded properly")
class PageDownloaderTest {

    private PageDownloader pageDownloader = new PageDownloader();

    @Test
    @DisplayName("Simple check if it's possible to download webpage")
    void checkIfPageIsDownloadedCorrectly() {
        // GIVEN
        String url = "https://google.pl";

        // WHEN
        Optional<Document> document = pageDownloader.downloadPage(url);

        // THEN
        assertThat(document.isEmpty(), is(false));
    }

    @Test
    @DisplayName("Non existing pages should not throw java.net.UnknownHostException")
    void nonExistingHostTest() {
        // GIVEN
        String url = "http://www.this.page.does.not.exists.for.sure.or.is.it.gov/";

        // WHEN
        Optional<Document> document = pageDownloader.downloadPage(url);

        // THEN
        assertThat(document.isEmpty(), is(true));
    }

}
