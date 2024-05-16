package ex3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

public class AddElementCommand<T> implements Command<T> {

    private final T t;
    private final Collection<T> target;


    public AddElementCommand(Collection<T> target, T t) {
        this.target = target;
        this.t = t;
    }

    @Override
    public void execute() {
        if (target instanceof ArrayList<T>){
            target.add(t);
        } else if (target instanceof  Stack<T>){
            target.add(t);
        }
    }


    @Override
    public void undo() {
        if (target instanceof ArrayList<T>){
            ((ArrayList<T>) target).remove(target.size() - 1);
        } else if (target instanceof Stack<T>)  {
            ((Stack<T>) target).pop();
        }
    }


    @Override
    public String toString() {
        return "Command " + t +
                " in collection: " + target;
    }

}
