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

    public static void main(String[] args) {
        AdvancedPrinterInterface p = new AdvancedPrinter();

        List<Document> docs = new ArrayList<>();
        docs.add(new Document("ex3/text1.txt"));
        docs.add(new Document("ex3/text2.txt"));
        docs.add(new Document("ex3/text3.txt"));

        p.print(docs.getFirst());     // print first document only
        pause(2000);            // wait for a while

        p.print(docs);
        p.showQueuedJobs();
        pause(4000);            // wait for a while

        p.print(docs);
        p.cancelJob(6);
        p.showQueuedJobs();
        pause(4000);            // wait for a while

        p.print(docs);
        p.cancelAll();
        p.showQueuedJobs();

        pause(2000);            // wait for a while
    }
}
