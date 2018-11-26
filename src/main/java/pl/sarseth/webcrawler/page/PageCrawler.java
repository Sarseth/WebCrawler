package pl.sarseth.webcrawler.page;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import pl.sarseth.webcrawler.page.link.LinkData;
import pl.sarseth.webcrawler.page.link.LinkSearcher;
import pl.sarseth.webcrawler.report.ReportAnalyzer;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class PageCrawler {

    private static final String REGEX_FOR_HTTP_WWW_PREFIX = "^(http://www\\.|https://www\\.|http://|https://)";

    private Set<String> pagesVisited = new HashSet<>();

    private List<String> pagesToVisit = new LinkedList<>();

    private PageDownloader pageDownloader = new PageDownloader();

    private LinkSearcher linkSearcher = new LinkSearcher();

    private String hostAddress;

    public static PageCrawler createPageCrawler(String firstUrlToVisit) {
        Objects.requireNonNull(StringUtils.trimToNull(firstUrlToVisit));

        var pageCrawler = new PageCrawler();
        pageCrawler.pagesToVisit.add(firstUrlToVisit);
        pageCrawler.hostAddress = firstUrlToVisit.replaceAll(REGEX_FOR_HTTP_WWW_PREFIX, "");
        return pageCrawler;
    }

    public void runPageCrawler(ReportAnalyzer reportAnalyzer) {
        var nextUrl = nextUrl();
        while (nextUrl.isPresent()) {

            var document = pageDownloader.downloadPage(nextUrl.get());

            if (document.isEmpty()) {
                putEmptyPageIntoReport(reportAnalyzer, nextUrl.get());
            } else {
                LinkData allLinksInDocument = linkSearcher.findAllLinksInDocument(document.get(), hostAddress);

                reportAnalyzer.consumer().accept(allLinksInDocument);
                pagesToVisit.addAll(allLinksInDocument.getInternalLinks());
            }
            nextUrl = nextUrl();
        }
    }

    private Optional<String> nextUrl() {
        String urlCandidate = null;
        while (!pagesToVisit.isEmpty() && urlNotSetOrUrlAlreadyVisited(urlCandidate)) {
            urlCandidate = pagesToVisit.remove(0);
        }

        if (!pagesVisited.add(urlCandidate)) { // If last element was in set, but list of urls ended. Job is done
            urlCandidate = null;
        }
        return Optional.ofNullable(urlCandidate);
    }

    private boolean urlNotSetOrUrlAlreadyVisited(String urlCandidate) {
        return urlCandidate == null || pagesVisited.contains(urlCandidate);
    }

    private void putEmptyPageIntoReport(ReportAnalyzer reportAnalyzer, String nextUrl) {
        reportAnalyzer.consumer().accept(LinkData.builder().withPage(nextUrl).build());
    }

}