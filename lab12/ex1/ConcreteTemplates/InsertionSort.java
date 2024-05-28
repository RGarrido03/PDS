package ex1.ConcreteTemplates;

import ex1.Algoritmo;
import ex1.SortingFilter;
import ex1.SortingOrder;
import ex1.Telemovel;

import java.util.Comparator;
import java.util.List;

public class InsertionSort extends Algoritmo {
    public InsertionSort(SortingFilter sortingFilter, SortingOrder sortingOrder, List<Telemovel> smartphones) {
        super(sortingFilter, sortingOrder, smartphones);
    }

    public static <T> void insertionSort(List<T> list, Comparator<? super T> comparator)
    {
        int n = list.size();
        for (int i = 1; i < n; ++i) {
            int key = list[i];
            int j = i - 1;

            while (j >= 0 && list[j] > key) {
                list[j + 1] = list[j];
                j = j - 1;
            }
            list[j + 1] = key;
        }
    }

    public void sort() {

        switch (super.getSortingFilter()) {
            case SortingFilter.RAM -> insertionSort(getSmartphones(), Comparator.comparingInt(Telemovel::getMemory));
            case SortingFilter.PRICE -> insertionSort(getSmartphones(), Comparator.comparingDouble(Telemovel::getPrice));
            case SortingFilter.DESCRIPTION -> insertionSort(getSmartphones(), Comparator.comparing(Telemovel::getDescription));
        }
    }
}
