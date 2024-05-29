package ex1.ConcreteTemplates;

import ex1.Algoritmo;
import ex1.SortingFilter;
import ex1.SortingOrder;
import ex1.Telemovel;

import java.util.Comparator;
import java.util.List;

public class BogoSort extends Algoritmo {

    public BogoSort(SortingFilter sortingFilter, SortingOrder sortingOrder, List<Telemovel> smartphones) {
        super(sortingFilter, sortingOrder, smartphones);
    }

    public static <T> void bogoSort(List<T> list, Comparator<? super T> comparator)
    {
        for (int i = 0; i < list.size(); i++) {

            int index1 = (int) (Math.random() * list.size()), index2 = (int) (Math.random() * list.size());
            int a = list.indexOf(index1);
            list.indexOf(index1) = list.indexOf(index2);
            list.indexOf(index2) = a;
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
