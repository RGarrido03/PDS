package ex3;

import java.util.ArrayList;
import java.util.List;

public class Adapter implements AdvancedPrinterInterface {
    private final BasicPrinter basicPrinter;

    // Spool (i.e., the service wrapping the spool)
    private final AdvancedPrinter.PrinterService printerService = new AdvancedPrinter.PrinterService();

    public Adapter(BasicPrinter basicPrinter) {
        this.basicPrinter = basicPrinter;
    }

    @Override
    public int print(Document doc) {
        // TODO: Refactor to accomodate the spool
        String[] content = {doc.getText()};
        if (basicPrinter.print(content)) {
            return 0;
        }
        return -1;
    }

    @Override
    public List<Integer> print(List<Document> docs) {
        // TODO: Refactor to accomodate the spool
        List<Integer> jobIds = new ArrayList<>();
        for (Document doc : docs) {
            jobIds.add(print(doc));
        }
        return jobIds;
    }

    @Override
    public void showQueuedJobs() {
        // TODO: Implement this method
    }

    @Override
    public boolean cancelJob(int jobId) {
        // TODO: Implement this method
        return false;
    }

    @Override
    public void cancelAll() {
        // TODO: Implement this method
    }
}
