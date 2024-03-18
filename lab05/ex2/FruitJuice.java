package ex2;

public class FruitJuice extends Food {

    private String type;

    public FruitJuice(String type){
        super(State.Liquid, Temperature.COLD);
        this.type = type;
    }

    public String getType() {return type;}
}
