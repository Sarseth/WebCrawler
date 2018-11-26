package pl.sarseth.webcrawler.page.link;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LinkData {

    private String page;

    private List<String> internalLinks;

    private List<String> externalLinks;

    private List<String> staticLinks;

    public static LinkDataBuilder builder() {
        return new LinkDataBuilder();
    }

    public String getPage() {
        return page;
    }

    public List<String> getInternalLinks() {
        return internalLinks;
    }

    public List<String> getExternalLinks() {
        return externalLinks;
    }

    public List<String> getStaticLinks() {
        return staticLinks;
    }

    private LinkData(LinkDataBuilder linkDataBuilder) {
        page = linkDataBuilder.page;
        internalLinks = linkDataBuilder.internalLinks;
        externalLinks = linkDataBuilder.externalLinks;
        staticLinks = linkDataBuilder.staticLinks;
    }

    public static class LinkDataBuilder {

        private String page;

        private List<String> internalLinks = Collections.emptyList();

        private List<String> externalLinks = Collections.emptyList();

        private List<String> staticLinks = Collections.emptyList();

        public LinkData build() {
            Objects.requireNonNull(StringUtils.trimToNull(page), "Page url is required");
            return new LinkData(this);
        }

        public LinkDataBuilder withPage(String page) {
            Objects.requireNonNull(StringUtils.trimToNull(page));
            this.page = page;
            return this;
        }

        public LinkDataBuilder withInternalLinks(List<String> internalLinks) {
            Objects.requireNonNull(internalLinks);
            this.internalLinks = internalLinks;
            return this;
        }

        public LinkDataBuilder withExternalLinks(List<String> externalLinks) {
            Objects.requireNonNull(externalLinks);
            this.externalLinks = externalLinks;
            return this;
        }

        public LinkDataBuilder withStaticLinks(List<String> staticLinks) {
            Objects.requireNonNull(staticLinks);
            this.staticLinks = staticLinks;
            return this;
        }

        private LinkDataBuilder() {
        }
    }

}
