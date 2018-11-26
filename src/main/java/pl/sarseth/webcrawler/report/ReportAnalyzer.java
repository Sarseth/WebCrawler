package pl.sarseth.webcrawler.report;

import pl.sarseth.webcrawler.page.link.LinkData;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ReportAnalyzer {

    private static final String PREFIX_FOR_INSIDE_ENTRIES = "\n\r  - ";
    private static final String NEW_LINE = "\n\r";
    private static final String INTERNALS = "\n\r  Internal links: ";
    private static final String EXTERNAL = "\n\r  External links: ";
    private static final String STATIC = "\n\r  Static links: ";

    private List<LinkData> linkDataList = new ArrayList<>();

    public Consumer<LinkData> consumer() {
        return linkData -> linkDataList.add(linkData);
    }

    public void saveToFileReport() throws FileNotFoundException {
        var printWriter = new PrintWriter("report.txt");
        for (LinkData linkData : linkDataList) {
            var record = createRecordForUrl(linkData);
            printWriter.print(record);
        }
        printWriter.close();
    }

    public void printReport() {
        for (LinkData linkData : linkDataList) {
            var reportInStringForm = createRecordForUrl(linkData);
            System.out.println(reportInStringForm);
        }
    }

    private String createRecordForUrl(LinkData linkData) {
        return linkData.getPage()
                + INTERNALS
                + linkData.getInternalLinks().stream().collect(Collectors.joining(PREFIX_FOR_INSIDE_ENTRIES, PREFIX_FOR_INSIDE_ENTRIES, NEW_LINE))
                + EXTERNAL
                + linkData.getExternalLinks().stream().collect(Collectors.joining(PREFIX_FOR_INSIDE_ENTRIES, PREFIX_FOR_INSIDE_ENTRIES, NEW_LINE))
                + STATIC
                + linkData.getStaticLinks().stream().collect(Collectors.joining(PREFIX_FOR_INSIDE_ENTRIES, PREFIX_FOR_INSIDE_ENTRIES, NEW_LINE));
    }

}
