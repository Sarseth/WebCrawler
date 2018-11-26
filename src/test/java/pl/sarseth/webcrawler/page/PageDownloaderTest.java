package pl.sarseth.webcrawler.page;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("Confirmation that pages are downloaded properly")
class PageDownloaderTest {

    private PageDownloader pageDownloader = new PageDownloader();

    @Test
    @DisplayName("Simple check if it's possible to download webpage")
    void checkIfPageIsDownloadedCorrectly() {
        // GIVEN
        var url = "https://google.pl";

        // WHEN
        var document = pageDownloader.downloadPage(url);

        // THEN
        assertThat(document.isEmpty(), is(false));
    }

    @Test
    @DisplayName("Non existing pages should not throw java.net.UnknownHostException")
    void nonExistingHostTest() {
        // GIVEN
        var url = "http://www.this.page.does.not.exists.for.sure.or.does.it.gov/";

        // WHEN
        var document = pageDownloader.downloadPage(url);

        // THEN
        assertThat(document.isEmpty(), is(true));
    }

}
