package ex1.ConcreteTemplates;

import ex1.Algoritmo;
import ex1.SortingFilter;
import ex1.SortingOrder;
import ex1.Telemovel;

import java.util.Comparator;
import java.util.List;


public class BubbleSort extends Algoritmo {
    public BubbleSort(SortingFilter sortingFilter, SortingOrder sortingOrder, List<Telemovel> smartphones) {
        super(sortingFilter, sortingOrder, smartphones);
    }

    public static <T> void bubbleSort(List<T> list, Comparator<? super T> comparator)
    {
        int n = list.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {

                    T temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j+1, temp);
                    swapped = true;
                }
            }

            if (!swapped)
                break;
        }
    }

    public void sort() {

        switch (super.getSortingFilter()) {
            case RAM -> bubbleSort(getTelemoveis(), Comparator.comparingInt(Telemovel::getMemory));
            case PRICE -> bubbleSort(getTelemoveis(), Comparator.comparingDouble(Telemovel::getPrice));
            case DESCRIPTION -> bubbleSort(getTelemoveis(), Comparator.comparing(Telemovel::getDescription));
        }
    }
}
