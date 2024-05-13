package ex3;

import javax.naming.OperationNotSupportedException;
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
    public boolean execute() {
        if (target instanceof ArrayList<T>){
            return target.add(t);
        } else if (target instanceof  Stack<T>){
            return target.add(t);
        }
        return false;
    }

    @Override
    public boolean undo() {
        if (target instanceof ArrayList<T>){
            return ((ArrayList<T>) target).remove(target.size() -1) != null;
        } else if (target instanceof Stack<T>)  {
            return ((Stack<T>) target).pop() != null;
        }
        return false;
    }
}
