package ex2.container;

import ex2.Portion;
import ex2.State;
import ex2.Temperature;

public class TermicBottle extends Container{

    private final Portion portion;

    public TermicBottle(Portion portion){
        super(State.Liquid, Temperature.WARM);
        this.portion = portion;
    }
    @Override
    public String toString() {
        return super.toString() + portion.toString();
    }

}
