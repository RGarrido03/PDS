package ex2;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Chef sushiChef = new SushiChef();
        Chef pastaChef = new PastaChef();
        Chef burgerChef = new BurgerChef();
        Chef pizzaChef = new PizzaChef();
        Chef dessertChef = new DessertChef();
        Chef chef = Chef.link(sushiChef, pastaChef, burgerChef, pizzaChef, dessertChef);

        Food sushi = new Food(FoodType.SUSHI, "Sushi nigiri and sashimi");
        Food pasta = new Food(FoodType.PASTA, "Pasta carbonara");
        Food burger = new Food(FoodType.BURGER, "Veggie burger");
        Food pizza = new Food(FoodType.PIZZA, "Plain pizza, no toppings");
        Food dessert = new Food(FoodType.DESSERT, "Strawberry ice cream");
        Food salad = new Food(FoodType.SALAD, "Salad with tuna");
        List<Food> foods = List.of(burger, pasta, pizza, sushi, salad, dessert);

        for (Food food : foods) {
            System.out.println("\nCan I please get a " + food.getName() + "?");
            chef.handle(food);
        }
    }
}
