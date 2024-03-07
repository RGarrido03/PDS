package lab3.ex2;

public enum ClassType {
    EXECUTIVE("E"),
    TOURISTIC("T");

    private final String type;

    ClassType(String e) {
        this.type = e;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
