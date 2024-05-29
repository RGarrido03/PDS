package ex1;

import ex1.ConcreteTemplates.BogoSort;
import ex1.ConcreteTemplates.BubbleSort;
import ex1.ConcreteTemplates.SelectionSort;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Telemovel> telemovels = new ArrayList<>();

        telemovels.add(new Telemovel(46, 46, "Pixel"));
        telemovels.add(new Telemovel(5000, 50, "Android"));
        telemovels.add(new Telemovel(100000, 70, "Iphone"));

        System.out.println("\nSelection Sort");
        Algoritmo selecionSort = new SelectionSort(SortingFilter.PRICE, SortingOrder.ASCENDING, telemovels);
        selecionSort.performSorting();

        System.out.println("\nBubble Sort");
        Algoritmo bubbleSort = new BubbleSort(SortingFilter.DESCRIPTION, SortingOrder.DESCENDING, telemovels);
        bubbleSort.performSorting();

        System.out.println("\nBogo Sort");
        Algoritmo bogoSort = new BogoSort(SortingFilter.RAM, SortingOrder.DESCENDING, telemovels);
        bogoSort.performSorting();
    }
}
