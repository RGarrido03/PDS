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
    abstract void printSorted();

    abstract void AscDesc();

    protected SortingFilter getSortingFilter() {
        return sortingFilter;
    }

    public abstract void sort();
}

