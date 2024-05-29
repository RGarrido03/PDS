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
            case RAM -> insertionSort(getTelemoveis(), Comparator.comparingInt(Telemovel::getMemory));
            case PRICE -> insertionSort(getTelemoveis(), Comparator.comparingDouble(Telemovel::getPrice));
            case DESCRIPTION -> insertionSort(getTelemoveis(), Comparator.comparing(Telemovel::getDescription));
        }
    }
}
