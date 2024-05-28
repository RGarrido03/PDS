package ex1.ConcreteTemplates;

import ex1.Algoritmo;
import ex1.SortingFilter;
import ex1.SortingOrder;
import ex1.Telemovel;

import java.util.Comparator;
import java.util.List;

public class SelectionSort extends Algoritmo {

    public SelectionSort(SortingFilter sortingFilter, SortingOrder sortingOrder, List<Telemovel> telemoveis) {
        super(sortingFilter, sortingOrder, telemoveis);
    }

    // Custom sorting method using selection sort algorithm
    public static <T> void selectionSort(List<T> list, Comparator<? super T> comparator) {
        for (int i = 0; i < list.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (comparator.compare(list.get(j), list.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                T temp = list.get(i);
                list.set(i, list.get(minIndex));
                list.set(minIndex, temp);
            }
        }
    }

    @Override
    public void sort() {
        switch (super.getSortingFilter()){
            case SortingFilter.RAM -> selectionSort(getSmartphones(), Comparator.comparingInt(Telemovel::getMemory));
            case SortingFilter.PRICE -> selectionSort(getSmartphones(), Comparator.comparingDouble(Telemovel::getPrice));
            case SortingFilter.DESCRIPTION -> selectionSort(getSmartphones(), Comparator.comparing(Telemovel::getDescription));
        }
    }

}