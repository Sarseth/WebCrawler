package pl.sarseth.webcrawler.page.link;

import org.jsoup.nodes.Document;

import java.util.LinkedList;
import java.util.List;

public class LinkSearcher {

    private static final String LINK_ATTR = "href";
    private static final String LINK_QUERY = "a[href]";

    public List<String> findAllLinksInDocument(Document document) {
        List<String> linkList = new LinkedList<>();
        if (document != null) {
            document.select(LINK_QUERY).forEach(link -> linkList.add(link.absUrl(LINK_ATTR)));
        }
        return linkList;
    }

}
