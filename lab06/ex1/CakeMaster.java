package ex1;

public class CakeMaster {
    private CakeBuilder cakeBuilder;

    public void setCakeBuilder(CakeBuilder cakeBuilder) {
        this.cakeBuilder = cakeBuilder;
    }

    public Cake getCake() {
        return cakeBuilder.getCake();
    }

    public void createCake(String message) {
        cakeBuilder.createCake();

        cakeBuilder.addCakeLayer();
        cakeBuilder.addCreamLayer();

        cakeBuilder.addTopping();
        cakeBuilder.addTopLayer();
        cakeBuilder.addMessage(message);
    }

    public void createCake(int layers, String message) {
        cakeBuilder.createCake();

        for (int i = 0; i < layers; i++) {
            cakeBuilder.addCakeLayer();
            cakeBuilder.addCreamLayer();
        }

        cakeBuilder.addTopping();
        cakeBuilder.addTopLayer();
        cakeBuilder.addMessage(message);
    }

    public void createCake(Shape shape, int layers, String message) {
        cakeBuilder.createCake();
        cakeBuilder.setCakeShape(shape);

        for (int i = 0; i < layers; i++) {
            cakeBuilder.addCakeLayer();
            cakeBuilder.addCreamLayer();
        }

        cakeBuilder.addTopping();
        cakeBuilder.addTopLayer();
        cakeBuilder.addMessage(message);
    }
}
