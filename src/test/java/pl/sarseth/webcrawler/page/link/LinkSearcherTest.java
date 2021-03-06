package pl.sarseth.webcrawler.page.link;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.sarseth.webcrawler.TestUtils;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@DisplayName("Verification that link searcher can find links")
class LinkSearcherTest {

    private LinkSearcher linkSearcher = new LinkSearcher();

    @Test
    @DisplayName("Check if links in wikipage can be found")
    void findAllLinksInHtmlPage() throws IOException {
        // GIVEN
        var wikipedia = TestUtils.getPage(TestUtils.WIKIPEDIA_PAGE);
        var expectedNumberOfInternalLinks = 290; //under wikipedia.org and matching regex .+(/|html|htm)+$ and are inner
        var expectedNumberOfExternalLinks = 14; //under wikipedia.org and matching regex .+(/|html|htm)+$ and are inner
        var expectedNumberOfStaticLinks = 3; //under wikipedia.org

        // WHEN
        LinkData allLinksInDocument = linkSearcher.findAllLinksInDocument(wikipedia, "wikipedia.org");

        // THEN
        assertThat(allLinksInDocument.getInternalLinks(), hasSize(expectedNumberOfInternalLinks));
        assertThat(allLinksInDocument.getExternalLinks(), hasSize(expectedNumberOfExternalLinks));
        assertThat(allLinksInDocument.getStaticLinks(), hasSize(expectedNumberOfStaticLinks));
    }

    @Test
    @DisplayName("Expect null pointer when host address not defined")
    void nullProofTestForHostAddress() throws IOException {
        // GIVEN
        var wikipedia = TestUtils.getPage(TestUtils.WIKIPEDIA_PAGE);

        // WHEN
        Assertions.assertThrows(NullPointerException.class, () -> {
            linkSearcher.findAllLinksInDocument(wikipedia, null);
        });
    }

    @Test
    @DisplayName("Expect null pointer when document not found")
    void nullProofTest() {
        // WHEN
        Assertions.assertThrows(NullPointerException.class, () -> {
            linkSearcher.findAllLinksInDocument(null, null);
        });
    }

}
