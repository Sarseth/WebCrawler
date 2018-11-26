package pl.sarseth.webcrawler;

import pl.sarseth.webcrawler.page.PageCrawler;
import pl.sarseth.webcrawler.report.ReportAnalyzer;

import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) {
        var pageCrawler = PageCrawler.createPageCrawler(args[0]);
        var reportAnalyzer = new ReportAnalyzer();

        pageCrawler.runPageCrawler(reportAnalyzer);

        try {
            reportAnalyzer.saveToFileReport();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to create file. Let's print this solution on screen.");
            reportAnalyzer.printReport();
        }
    }
}
