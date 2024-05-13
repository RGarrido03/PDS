package ex1;

import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        VectorGeneric<String> vg = new VectorGeneric<>();
        vg.addElem("a");
        vg.addElem("b");
        vg.addElem("c");
        vg.addElem("d");
        vg.addElem("e");

        ListIterator<String> iterator = vg.getListIterator();
        System.out.println("Has next: " + iterator.hasNext());
        System.out.println("Next: " + iterator.next());
        System.out.println("Next: " + iterator.next());
        System.out.println("Next index: " + iterator.nextIndex());
        System.out.println("Previous index: " + iterator.previousIndex());
        System.out.println("Has previous: " + iterator.hasPrevious());
        System.out.println("Previous: " + iterator.previous());
    }
}
