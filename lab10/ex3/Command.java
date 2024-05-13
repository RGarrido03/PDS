package ex3;

public interface Command<T> {
    boolean execute();

    boolean undo();
}
