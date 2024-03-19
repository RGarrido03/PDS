package ex2.container;

import ex2.Portion;
import ex2.State;
import ex2.Temperature;

public class PlasticBottle extends Container{

    private final Portion portion;

    public PlasticBottle(Portion portion){
        super(State.Liquid, Temperature.COLD);
        this.portion = portion;
    }

    @Override
    public String toString() {
        return super.toString() + portion.toString();
    }
}
