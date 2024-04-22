package ex2;

import java.text.Normalizer;

public class NormalizationFilter implements Filter {
    private final Filter filter;

    public NormalizationFilter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public boolean hasNext() {
        return filter.hasNext();
    }

    @Override
    public String next() {
        return Normalizer.normalize(filter.next(), Normalizer.Form.NFD)
                         .replaceAll("[^\\p{ASCII}]", "")
                         .replaceAll("[^A-Za-z]", "");
    }
}
