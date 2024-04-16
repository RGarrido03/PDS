package ex3;

import java.util.concurrent.Callable;

public class PrintJob implements Callable<Document> {
    private static int nextJobId = 0;

    private final int jobId;
    private final Document document;

    public PrintJob(Document doc) {
        this.jobId = nextJobId++;
        this.document = doc;
    }

    @Override
    public Document call() {
        return document;
    }

    public int getJobId() {
        return jobId;
    }

    @Override
    public String toString() {
        return document.toString();
    }
}
