package ex1;

public class ChocolateCakeBuilder implements CakeBuilder {
    private Cake cake;

    @Override
    public void setCakeShape(Shape shape) {
        cake.setShape(shape);
    }

    @Override
    public void addCakeLayer() {
        cake.setNumCakeLayers(cake.getNumCakeLayers() + 1);
        cake.setCakeLayer("Soft chocolate");
    }

    @Override
    public void addCreamLayer() {
    }

    @Override
    public void addTopLayer() {
        cake.setTopLayerCream(new Cream("Whipped_Cream"));
    }

    @Override
    public void addTopping() {
        cake.setTopping(new Topping("Fruit"));
    }

    @Override
    public void addMessage(String m) {
        cake.setMessage(m);
    }

    @Override
    public void createCake() {
        cake = new Cake();
    }

    @Override
    public Cake getCake() {
        return cake;
    }
}
