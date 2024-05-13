package ex3;

import java.util.Stack;

public class Invoker<T> {
    private final Stack<Command<T>> commands = new Stack<>();

    void push(Command<T> command) {
        command.execute();
        commands.push(command);
    }

    void undo() {
        Command command = commands.pop();
        command.undo();
    }
}
