package pl.sarseth.webcrawler.page;

import pl.sarseth.webcrawler.page.link.LinkSearcher;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class PageCrawler {

    private static final String REGEX_FOR_HTTP_WWW_PREFIX = "^(http://www\\.|https://www\\.|http://|https://)";

    private Set<String> pagesVisited;

    private List<String> pagesToVisit;

    private PageDownloader pageDownloader;

    private LinkSearcher linkSearcher;

    private String hostAddress;

    public static PageCrawler createPageCrawler(String firstUrlToVisit) {
        PageCrawler pageCrawler = new PageCrawler();
        pageCrawler.pagesToVisit.add(firstUrlToVisit);
        pageCrawler.pageDownloader = new PageDownloader();
        pageCrawler.linkSearcher = new LinkSearcher();
        pageCrawler.hostAddress = firstUrlToVisit.replaceAll(REGEX_FOR_HTTP_WWW_PREFIX, "");
        return pageCrawler;
    }

    public void runPageCrawler() {
        Optional<String> nextUrl = nextUrl();
        while (nextUrl.isPresent()) {
            System.out.println(nextUrl.get());
            pageDownloader
                    .downloadPage(nextUrl.get())
                    .ifPresent(doc -> pagesToVisit.addAll(linkSearcher.findAllLinksInDocument(doc, hostAddress)));

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

    private PageCrawler() {
        pagesVisited = new HashSet<>();
        pagesToVisit = new LinkedList<>();
    }

}