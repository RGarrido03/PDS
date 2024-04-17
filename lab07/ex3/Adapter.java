package ex3;

import java.util.ArrayList;
import java.util.List;

public class Adapter implements AdvancedPrinterInterface {
    private final BasicPrinter basicPrinter;

    // Spool (i.e., the service wrapping the spool)
    private final PrinterService printerService = new PrinterService();

    public Adapter(BasicPrinter basicPrinter) {
        this.basicPrinter = basicPrinter;
    }

    @Override
    public int print(Document doc) {
        String[] content = {doc.getText()};
        if (basicPrinter.print(content)) {
            return printerService.newPrintJob(doc);
        }
        return -1;
    }

    @Override
    public List<Integer> print(List<Document> docs) {
        List<Integer> jobIds = new ArrayList<>();
        for (Document doc : docs) {
            jobIds.add(print(doc));
        }
        return jobIds;
    }

    @Override
    public void showQueuedJobs() {
        if (printerService.getPrintQueue().isEmpty()) {
            System.out.println("No spooled jobs.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Spooled jobs:\n");
        printerService.getPrintQueue().stream().map(printJob -> "  * " + printJob.toString() + "\n").forEach(sb::append);
        System.out.println(sb);

    }

    @Override
    public boolean cancelJob(int jobId) {
        return printerService.cancelJob(jobId);
    }

    @Override
    public void cancelAll() {
        printerService.shutdownAndAwaitTermination();
        printerService.getPrintQueue().clear();
    }
}
