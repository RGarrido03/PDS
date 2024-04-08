package ex1;

public class Topping {
    public Topping(String name) {
        this.name = name;
    }

    private String name;

    public String getCream() {
        return name;
    }

    public void setCream(String cream) {
        this.name = cream;
    }

    @Override
    public String toString() {
        return name;
    }
}
