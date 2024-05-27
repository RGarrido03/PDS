package ex1.ConcreteStartegies;

import ex1.Algoritmo;
import ex1.Telemovel;

import java.util.List;

public class Samsung extends Algoritmo {
    public Samsung(SortingFilter sortingFilter, SortingOrder sortingOrder, List<Telemovel> smartphones) {
        super(sortingFilter, sortingOrder, smartphones);
    }
}
