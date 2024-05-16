package ex3;

import java.util.Stack;

public class Invoker<T> {
    private final Stack<Command<T>> commands;

    public Invoker() {
        commands = new Stack<>();
    }

    public void push(Command<T> command){
        command.execute();
        commands.push(command);
    }

    public void undo(){
        commands.pop().undo();
    }
}
