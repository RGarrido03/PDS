package ex2;

public class VowelFilter implements Filter {
    private final Filter filter;

    public VowelFilter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public boolean hasNext() {
        return filter.hasNext();
    }

    @Override
    public String next() {
        return filter.next().replaceAll("[aeiouà-åè-ïò-öù-üAEIOUÀ-ÅÈ-ÏÒ-ÖÙ-Ü]", "");
    }
}
