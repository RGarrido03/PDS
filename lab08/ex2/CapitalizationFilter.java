package ex2;

public class CapitalizationFilter implements Filter {
    private final Filter filter;

    public CapitalizationFilter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public boolean hasNext() {
        return filter.hasNext();
    }

    @Override
    public String next() {
        String a = filter.next();

        if (a.length() == 1) {
            return a.toUpperCase();
        }

        return Character.toUpperCase(a.charAt(0)) +
                a.substring(1, a.length() - 1).toLowerCase() +
                Character.toUpperCase(a.charAt(a.length() - 1));
    }
}
