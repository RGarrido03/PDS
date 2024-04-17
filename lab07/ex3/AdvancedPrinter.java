package ex3;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

public class AdvancedPrinter implements AdvancedPrinterInterface {

    private final PrinterService spool;

    public AdvancedPrinter() {
        this.spool = new PrinterService();
        new Thread(this.spool).start();
    }

    @Override
    public int print(Document doc) {
        System.out.println("Spooling 1 document.");
        return spool.newPrintJob(doc);
    }

    @Override
    public List<Integer> print(List<Document> docs) {
        System.out.println("Spooling " + docs.size() + " documents.");
        return docs.stream().map(spool::newPrintJob).toList();
    }

    @Override
    public void showQueuedJobs() {
        if (spool.getPrintQueue().isEmpty()) {
            System.out.println("No spooled jobs.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Spooled jobs:\n");
        spool.getPrintQueue().stream().map(printJob -> "  * " + printJob.toString() + "\n").forEach(sb::append);
        System.out.println(sb);
    }

    @Override
    public boolean cancelJob(int jobId) {
        return spool.cancelJob(jobId);
    }

    @Override
    public void cancelAll() {
        spool.shutdownAndAwaitTermination();
        spool.getPrintQueue().clear();
    }

    // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/ExecutorService.html

}
