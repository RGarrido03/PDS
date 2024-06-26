package ex3;

import java.util.Optional;
import java.util.concurrent.*;

public class PrinterService implements Runnable {
    private final LinkedBlockingQueue<PrintJob> printQueue;
    private final ExecutorService pool;

    // este serviço simula a fila de impressão e a impressão de um documento de cada vez
    public PrinterService() {
        printQueue = new LinkedBlockingQueue<>();
        pool = Executors.newFixedThreadPool(1);
    }

    public void run() { // run the service
        try {
            for (; ; ) {
                PrintJob j = printQueue.take();
                pool.submit(j).get();
            }
        } catch (java.util.concurrent.RejectedExecutionException ex) {
            System.out.println("Job rejected by spool: service shutting down?");
        } catch (ExecutionException e) {
            System.out.println("Error");
            e.printStackTrace();
        } catch (InterruptedException ex) {
            this.shutdownAndAwaitTermination();
        }
    }

    public int newPrintJob(Document doc) {
        PrintJob printJob = new PrintJob(doc);
        printQueue.add(printJob);
        return printJob.getJobId();
    }

    public boolean cancelJob(int job) {
        Optional<PrintJob> found = printQueue.stream().filter(printJob -> printJob.getJobId() == job).findFirst();
        if (found.isEmpty()) {
            return false;
        }

        printQueue.remove(found.get());
        System.out.println("Cancelled " + found.get());
        return true;
    }

    void shutdownAndAwaitTermination() {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Spool did not terminate.");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            printQueue.clear();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

    public LinkedBlockingQueue<PrintJob> getPrintQueue() {
        return printQueue;
    }
}