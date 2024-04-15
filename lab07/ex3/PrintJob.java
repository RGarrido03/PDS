package ex3;

import java.util.concurrent.Callable;

public class PrintJob implements Callable<Document> {
    private final Document document;

    public PrintJob(Document doc) {
        this.document = doc;
    }

    @Override
    public Document call() {
        return document;
    }

    @Override
    public String toString() {
        return document.toString();
    }
}
