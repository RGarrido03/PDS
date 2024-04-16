package ex3;

import java.util.ArrayList;
import java.util.List;

public class PrinterTest {

    private static void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testPrinter(AdvancedPrinterInterface printer) {
        List<Document> docs = new ArrayList<>();
        docs.add(new Document("ex3/text1.txt"));
        docs.add(new Document("ex3/text2.txt"));
        docs.add(new Document("ex3/text3.txt"));

        printer.print(docs.getFirst());     // print first document only
        pause(2000);            // wait for a while

        printer.print(docs);
        printer.showQueuedJobs();
        pause(4000);            // wait for a while

        printer.print(docs);
        printer.cancelJob(6);
        printer.showQueuedJobs();
        pause(4000);            // wait for a while

        printer.print(docs);
        printer.cancelAll();
        printer.showQueuedJobs();

        pause(2000);            // wait for a while
    }

    public static void main(String[] args) {
        AdvancedPrinterInterface advancedPrinter = new AdvancedPrinter();
        AdvancedPrinterInterface basicPrinter = new Adapter(new BasicPrinter());

        testPrinter(advancedPrinter);
        testPrinter(basicPrinter);
    }
}
