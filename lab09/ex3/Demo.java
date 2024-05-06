package ex3;

import ex3.startypes.Star;
import ex3.startypes.StarFactory;

import java.awt.*;
import java.util.Random;

public class Demo {
    private static final Random random = new Random();
    static int CANVAS_SIZE = 1200;
    static int STARS_TO_DRAW = 1000000;

    public static void main(String[] args) {
        Sky sky = new Sky();

        // https://astrobackyard.com/wp-content/uploads/2020/03/types-of-stars.jpg
        char[] starTypes = {'O', 'B', 'A', 'F', 'G', 'K', 'M'};
        char type;

        Runtime runtime = Runtime.getRuntime();
        long before = runtime.totalMemory() - runtime.freeMemory();

        for (int i = 0; i < STARS_TO_DRAW; i++) {
            type = starTypes[random(0, starTypes.length - 1)];
            sky.placeStar(createStar(type));
        }
        sky.setSize(CANVAS_SIZE, CANVAS_SIZE);
        sky.setBackground(Color.BLACK);
        sky.setVisible(true);

        long after = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory: " + (after - before) / 1024 / 1024 + " MB");
    }

    private static Star createStar(char type) {
        int x = random(0, CANVAS_SIZE);
        int y = random(0, CANVAS_SIZE);
        return new Star(StarFactory.getStar(type), x, y);
    }

    private static int random(int min, int max) {
        return random.nextInt(min, max + 1);
    }
}