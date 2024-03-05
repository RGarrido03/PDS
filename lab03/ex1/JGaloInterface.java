package lab3.ex1;

public interface JGaloInterface {
    char getActualPlayer();

    boolean setJogada(int lin, int col);

    boolean isFinished();

    char checkResult();
}