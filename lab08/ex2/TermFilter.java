package ex2;

public class TermFilter implements Filter {
    private final Filter filter;

    public TermFilter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public boolean hasNext() {
        return filter.hasNext();
    }

    @Override
    public String next() {
        return filter.next().split(" ")[0];
    }
}
