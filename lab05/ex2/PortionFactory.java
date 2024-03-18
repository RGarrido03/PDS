package ex2;

public class PortionFactory {

    public static Portion create(String food, Temperature temperature) {

        return switch (food + temperature) {
            case "BeverageCOLD" -> new FruitJuice("Orange");
            case "BeverageWARM" -> new Milk();
            case "MeatCOLD" -> new Tuna();
            case "MeatWORM" -> new Pork();
            default -> null;
        };
    }
}
