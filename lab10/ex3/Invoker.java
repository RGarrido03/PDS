package ex3;

import javax.naming.OperationNotSupportedException;
import java.util.Stack;

public class Invoker<T> {
    private final Stack<Command<T>> commands;

    public Invoker() {
        commands = new Stack<>();
    }

    public boolean push(Command<T> command){
        command.execute();
        return commands.push(command) != null;
    }

    public boolean undo(){
        //Command command = commands.pop();
        return commands.pop().undo();
    }
}
