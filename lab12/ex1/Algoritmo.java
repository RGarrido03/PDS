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
    public final void performSorting(){
        sort();
        printSort();
    }

    protected abstract void printSort();
    protected abstract void sort();

    protected List<Telemovel> getTelemoveis() {
        return telemoveis;
    }

    protected SortingFilter getSortingFilter() {
        return sortingFilter;
    }
    protected SortingOrder getSortingOrder() {
        return sortingOrder;
    }


}

