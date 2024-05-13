package ex3;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        Stack<Integer> initStack = new Stack<>();
        Invoker<Integer> invoker = new Invoker<>();

        Command<Integer> addCommand1 = new AddElementCommand<>(initStack, 1);
        Command<Integer> addCommand2 = new AddElementCommand<>(initStack, 2);
        Command<Integer> addCommand3 = new AddElementCommand<>(initStack, 3);

        invoker.push(addCommand1);
        invoker.push(addCommand2);
        invoker.push(addCommand3);


        invoker.undo();
        System.out.println("Push to stac");

    }
}
