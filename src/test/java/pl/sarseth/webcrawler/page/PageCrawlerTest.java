package pl.sarseth.webcrawler.page;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@DisplayName("Test on live small page dustyfeet.com")
class PageCrawlerTest {

    @Test
    @DisplayName("No asserts, just check if crawling is working")
    void simpleCrawlCall() throws IOException {
        PageCrawler pageCrawler = PageCrawler.createPageCrawler("http://www.dustyfeet.com/");
        pageCrawler.runPageCrawler();
    }

}
