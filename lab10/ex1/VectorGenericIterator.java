package ex1;

import java.util.ListIterator;

public class VectorGenericIterator<T> implements ListIterator<T> {
    private final VectorGeneric<T> vector;
    private int index;

    public VectorGenericIterator(VectorGeneric<T> vector, int index) {
        this.vector = vector;
        this.index = index;
    }

    @Override
    public boolean hasNext() {
        return index < vector.totalElem();
    }

    @Override
    public T next() {
        return vector.getElem(index++);
    }

    @Override
    public boolean hasPrevious() {
        return index > 0;
    }

    @Override
    public T previous() {
        return vector.getElem(--index);
    }

    @Override
    public int nextIndex() {
        return index + 1;
    }

    @Override
    public int previousIndex() {
        return index - 1;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove is not supported.");
    }

    @Override
    public void set(T t) {
        throw new UnsupportedOperationException("Set is not supported.");
    }

    @Override
    public void add(T t) {
        throw new UnsupportedOperationException("Add is not supported.");
    }
}
