package ex2;

import java.util.ArrayList;
import java.util.List;

public class TermFilter implements Filter {
    private final Filter filter;
    private final List<String> queue = new ArrayList<>();

    public TermFilter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty() || filter.hasNext();
    }

    @Override
    public String next() {
        if (queue.isEmpty()) {
            queue.addAll(List.of(filter.next().split(" ")));
        }
        return queue.removeFirst();
    }
}
