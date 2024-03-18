package ex2;

import ex2.container.*;

public class ContainerFactory {

    public static Container create(Portion portion) {

        return switch (portion.getState().toString() + portion.getTemperature().toString()) {
            case "BottleCOLD" -> new PlasticBottle();
            case "BottleWARM" -> new TermicBottle();
            case "MeatWARM" -> new Tupperware();
            case "MeatCOLD" -> new PlasticBag();
            default -> null;
        };

    }
}
