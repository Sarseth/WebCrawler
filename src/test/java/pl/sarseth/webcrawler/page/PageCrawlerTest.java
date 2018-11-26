package pl.sarseth.webcrawler.page;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.sarseth.webcrawler.report.ReportAnalyzer;

@DisplayName("Page crawler tests")
class PageCrawlerTest {

    @Test
    @DisplayName("Check if report is created properly. Visual verification ;)")
    void simpleCrawlCall() {
        var pageCrawler = PageCrawler.createPageCrawler("http://www.dustyfeet.com/");
        var reportAnalyzer = new ReportAnalyzer();
        pageCrawler.runPageCrawler(reportAnalyzer);
        reportAnalyzer.printReport();
    }

    @Test
    @DisplayName("Empty host name, fail fast test")
    void nullCallFailFastTest() {
        // WHEN
        Assertions.assertThrows(NullPointerException.class, () -> PageCrawler.createPageCrawler(""));
    }


}
