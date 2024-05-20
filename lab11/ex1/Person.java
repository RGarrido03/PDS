package ex1;

public abstract class Person {
    private final String name;

    protected Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
