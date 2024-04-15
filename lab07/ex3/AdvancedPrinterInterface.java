package ex3;

import java.util.List;

public interface AdvancedPrinterInterface {
    int print(Document doc);

    List<Integer> print(List<Document> docs);

    void showQueuedJobs();

    boolean cancelJob(int jobId);

    void cancelAll();
}
