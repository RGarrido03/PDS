package ex1.ConcreteTemplates;

import ex1.Algoritmo;
import ex1.SortingFilter;
import ex1.SortingOrder;
import ex1.Telemovel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BogoSort extends Algoritmo {

    public BogoSort(SortingFilter sortingFilter, SortingOrder sortingOrder, List<Telemovel> smartphones) {
        super(sortingFilter, sortingOrder, smartphones);
    }

    public static <T> void bogoSort(List<T> list, Comparator<? super T> comparator)
    {
        while (true) {
            boolean sorted = true;
            for (int i = 1; i < list.size(); i++) {
                if (comparator.compare(list.get(i-1), list.get(i)) > 0) {
                    sorted = false;
                    break;
                }
            }

            if (sorted) break;

            Collections.shuffle(list);
        }

    }

    public void sort() {
        switch (super.getSortingFilter()) {
            case RAM -> bogoSort(getTelemoveis(), Comparator.comparingInt(Telemovel::getMemory));
            case PRICE -> bogoSort(getTelemoveis(), Comparator.comparingDouble(Telemovel::getPrice));
            case DESCRIPTION -> bogoSort(getTelemoveis(), Comparator.comparing(Telemovel::getDescription));
        }
    }


    @Override
    protected void printSort() {
        getTelemoveis().forEach(System.out::println);
    }
}
