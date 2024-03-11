package lab03;

public class Jogo implements JGaloInterface {

    private char[][] board;
    private char player1;
    private char player2;
    private char actualPlayer;
    private int jogadas;
    private boolean draw;

    public Jogo(char actualplayer) {
        this.actualPlayer = actualplayer;
        this.player1 = 'X';
        this.player2 = 'O';
        this.board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = ' ';
            }
        }
    }

    public char getActualPlayer() {
        char playeratual = this.actualPlayer;
        if (this.actualPlayer == this.player1) {
            this.actualPlayer = this.player2;
        } else {
            this.actualPlayer = this.player1;
        }
        return playeratual;
    }

    public boolean setJogada(int lin, int col) {
        if (this.board[lin - 1][col - 1] != ' ') {
            return false;
        } else {
            this.board[lin - 1][col - 1] = this.actualPlayer;
            this.jogadas++;
            return true;
        }
    }

    public boolean isFinished() {
        if (this.board[0][0] == this.board[0][1] && this.board[0][1] == this.board[0][2] && this.board[0][0] != ' ') {
            draw = false;
            return true;
        }
        if (this.board[1][0] == this.board[1][1] && this.board[1][1] == this.board[1][2] && this.board[1][0] != ' ') {
            draw = false;
            return true;
        }
        if (this.board[2][0] == this.board[2][1] && this.board[2][1] == this.board[2][2] && this.board[2][0] != ' ') {
            draw = false;
            return true;
        }
        if (this.board[0][0] == this.board[1][0] && this.board[1][0] == this.board[2][0] && this.board[0][0] != ' ') {
            draw = false;
            return true;
        }
        if (this.board[0][1] == this.board[1][1] && this.board[1][1] == this.board[2][1] && this.board[0][1] != ' ') {
            draw = false;
            return true;
        }
        if (this.board[0][2] == this.board[1][2] && this.board[1][2] == this.board[2][2] && this.board[0][2] != ' ') {
            draw = false;
            return true;
        }
        if (this.board[0][0] == this.board[1][1] && this.board[1][1] == this.board[2][2] && this.board[0][0] != ' ') {
            draw = false;
            return true;
        }
        if (this.board[0][2] == this.board[1][1] && this.board[1][1] == this.board[2][0] && this.board[0][2] != ' ') {
            draw = false;
            return true;
        }
        if (this.jogadas == 9) {
            draw = true;
            return true;
        }
        return false;
    }

    public char checkResult() {
        if (!draw) {
            if (this.actualPlayer == this.player1) {
                return this.player2;

            } else {
                return this.player1;
            }
        }
        return ' ';
    }

}