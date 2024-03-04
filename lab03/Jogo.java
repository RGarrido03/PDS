package lab3;

import lab3.JGaloInterface;

public class Jogo implements JGaloInterface {

    private char[][] tabuleiro;
    boolean isPlayerX = true;
    private int jogadas;

    public Jogo(){
        tabuleiro = new char[3][3];

        for (int i = 0; i < tabuleiro.length; i++){
            for (int j = 0; j < tabuleiro.length; j++){
                tabuleiro[i][j] = ' ';
            }
        }
        jogadas = 0;

    }

    public char getActualPlayer(){

       isPlayerX = !isPlayerX;
       return isPlayerX ? 'O' : 'X';

    }
    public boolean setJogada(int lin, int col){
        if (tabuleiro[lin-1][col-1] == ' '){
            tabuleiro[lin-1][col-1] = isPlayerX ? 'O' : 'X';
            jogadas++;

            return true;
        }
        return false;
    }

    public char vencedor(){

        // Check horizontal or vertical lines
        for (int i = 0; i <= 2; i++){

            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2] ||
                    tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i]){
                return tabuleiro[i][i];
            }
        }

        // Check diagonal lines
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2] ||
                tabuleiro[2][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[0][2]) {
            return tabuleiro[1][1];
        }

        return ' ';

    }
    public boolean isFinished() {
        return vencedor() != ' ' || jogadas == 9;
    }

    public char checkResult() {

        if (vencedor() == 'X'){
            return 'X';
        }
        else if (vencedor() == 'O'){
            return 'O';
        }

        return ' ';
    }
}
