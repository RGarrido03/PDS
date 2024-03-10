package lab3.ex1;


public class Jogo implements JGaloInterface {


    boolean isPlayerX = true;
    private char[][] tabuleiro;
    private int jogadas;


    /**
     * Instantiates a new Game creating board.
     */
    public Jogo() {
        tabuleiro = new char[3][3];

        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
        jogadas = 0;
    }

    /**
     * Switching through players in each turn
     *
     * @return char
     */
    public char getActualPlayer() {
        isPlayerX = !isPlayerX;
        return isPlayerX ? 'O' : 'X';
    }

    /**
     * Play on a valid spot in board
     *
     * @param lin
     * @param col
     * @return boolean
     */
    public boolean setJogada(int lin, int col) {
        if (tabuleiro[lin - 1][col - 1] == ' ') {
            tabuleiro[lin - 1][col - 1] = isPlayerX ? 'O' : 'X';
            jogadas++;

            return true;
        }
        return false;
    }

    /**
     * Checking winner by iterate through the board and check for three same symbols.
     *
     * @return char
     */
    public char vencedor() {

        // Check horizontal or vertical lines
        for (int i = 0; i <= 2; i++) {

            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2] ||
                    tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i]) {
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

    /**
     * Check if game is finished
     *
     * @return boolean
     */
    public boolean isFinished() {
        return vencedor() != ' ' || jogadas == 9;
    }

    /**
     * Who is the winner
     *
     * @return char
     */
    public char checkResult() {

        if (vencedor() == 'X') {
            return 'X';
        } else if (vencedor() == 'O') {
            return 'O';
        }
        return ' ';
    }
}
