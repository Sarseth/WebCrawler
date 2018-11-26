package pl.sarseth.webcrawler;

import pl.sarseth.webcrawler.page.PageCrawler;
import pl.sarseth.webcrawler.report.ReportAnalyzer;

public class App {
    public static void main(String[] args) {
        var pageCrawler = PageCrawler.createPageCrawler(args[0]);
        var reportAnalyzer = new ReportAnalyzer();

        pageCrawler.runPageCrawler(reportAnalyzer);
        reportAnalyzer.printReport();
    }
}
