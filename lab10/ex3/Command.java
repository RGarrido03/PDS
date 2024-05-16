package ex3;

public interface Command<T> {
    void execute();
    void undo();
}
