package pl.sarseth.webcrawler.page.link;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class LinkSearcher {

    private static final String LINK_ATTR = "href";
    private static final String LINK_QUERY = "a[href]";
    private static final String STATIC_QUERY = "[src]";
    private static final String STATIC_ATTR = "src";
    private static final String HTML_END_REGEX = ".+(/|html|htm)+$";

    public LinkData findAllLinksInDocument(Document document, String hostAddress) {
        Objects.requireNonNull(document);
        Objects.requireNonNull(StringUtils.trimToNull(hostAddress));

        List<String> allLinks = new ArrayList<>();
        document.select(LINK_QUERY).forEach(link -> allLinks.add(link.absUrl(LINK_ATTR)));

        Map<Boolean, List<String>> mapOfLinks =
                allLinks
                        .stream()
                        .filter(s -> s.matches(HTML_END_REGEX))
                        .collect(Collectors.partitioningBy(s -> s.contains(hostAddress)));

        var linkDataBuilder = LinkData.builder().withPage(document.location());
        linkDataBuilder.withInternalLinks(mapOfLinks.get(Boolean.TRUE)); // True for previous partitioningBy by "contains hostAddress")
        linkDataBuilder.withExternalLinks(mapOfLinks.get(Boolean.FALSE)); // False for previous partitioningBy by "contains hostAddress")

        List<String> staticLinks = findStaticLinks(document);
        linkDataBuilder.withStaticLinks(staticLinks);

        return linkDataBuilder.build();
    }

    private List<String> findStaticLinks(Document document) {
        List<String> staticLinks = new ArrayList<>();
        document.select(STATIC_QUERY).forEach(link -> staticLinks.add(link.absUrl(STATIC_ATTR)));
        return staticLinks;
    }

}
