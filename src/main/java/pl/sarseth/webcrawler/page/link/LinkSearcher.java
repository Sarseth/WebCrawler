package pl.sarseth.webcrawler.page.link;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LinkSearcher {

    private static final String LINK_ATTR = "href";
    private static final String LINK_QUERY = "a[href]";
    private static final String HTML_END_REGEX = ".+(/|html|htm)+$";

    public List<String> findAllLinksInDocument(Document document, String hostAddress) {
        Objects.requireNonNull(document);
        Objects.requireNonNull(StringUtils.trimToNull(hostAddress));

        List<String> linkList = new LinkedList<>();
        document.select(LINK_QUERY).forEach(link -> linkList.add(link.absUrl(LINK_ATTR)));
        return linkList.stream()
                .filter(predicateForValidUrls(hostAddress))
                .collect(Collectors.toList());
    }

    private Predicate<String> predicateForValidUrls(String hostAddress) {
        return s -> s.contains(hostAddress) && s.matches(HTML_END_REGEX);
    }

}
