package ex2;

import java.util.Random;

public abstract class Chef {
    private static final Random random = new Random();
    private final FoodType supported;
    private Chef next;

    protected Chef(FoodType supported) {
        this.supported = supported;
    }

    public static Chef link(Chef first, Chef... chain) {
        Chef head = first;

        for (Chef next : chain) {
            head.next = next;
            head = next;
        }

        return first;
    }

    private static int getRandomInt() {
        return random.nextInt(15) + 5;
    }

    private boolean canHandle(Food food) {
        return food.getType() == supported;
    }

    public boolean handle(Food food) {
        System.out.printf("%-15s", this.getClass().getSimpleName() + ":");

        if (canHandle(food)) {
            System.out.println("Starting to cook " + food.getName() + ". Out in " + getRandomInt() + " minutes.");
            return true;
        }

        System.out.println("I can't cook that.");
        if (next != null) {
            return next.handle(food);
        }

        System.out.println("We're sorry: our service can't satisfy that request!");
        return false;
    }
}
