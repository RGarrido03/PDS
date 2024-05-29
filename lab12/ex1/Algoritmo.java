package ex1;

import java.util.List;

public abstract class Algoritmo {

    private final SortingFilter sortingFilter;
    private final SortingOrder sortingOrder;
    private final List<Telemovel> telemoveis;

    public Algoritmo(SortingFilter sortingFilter, SortingOrder sortingOrder, List<Telemovel> telemoveis) {
        this.sortingFilter = sortingFilter;
        this.sortingOrder = sortingOrder;
        this.telemoveis = telemoveis;

    }

    // Template method
    protected abstract void printSort();

    abstract void AscDesc();

    protected List<Telemovel> getTelemoveis() {
        return telemoveis;
    }

    protected SortingFilter getSortingFilter() {
        return sortingFilter;
    }

    public abstract void sort();
}

