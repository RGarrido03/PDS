package ex2;

public class Food {
    private final FoodType type;
    private final String name;

    public Food(FoodType type, String name) {
        this.type = type;
        this.name = name;
    }

    public FoodType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
