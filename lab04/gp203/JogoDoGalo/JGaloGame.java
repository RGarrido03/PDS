package lab03.JogoDoGalo;

public class JGaloGame implements JGaloInterface{
    private char[][] board;
    private char player1;
    private char player2;
    private int playCounter;

    public JGaloGame(String[] args){
        player1 = 'X';
        player2 = 'O';
        playCounter = 0;
        board = new char[3][3];
        board = fillBoard(board); 
    }

    public char[][] fillBoard(char[][] board){
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board.length; j++){
                board[i][j] = ' ';
            }
        }
        return board;
    }

    @Override
    public char getActualPlayer(){
        if(playCounter%2==0){
            return player1;
        }
        return player2;
    }

    @Override
    public boolean setJogada(int lin, int col){
         
        if(board[lin-1][col-1] == ' '){ 
            board[lin-1][col-1] = getActualPlayer();
            playCounter++;
            return true;
        }
        return false;
        
    }

    public char checkDiagonal(){
        if(board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != ' ' 
        || board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != ' ' ){
            return board[1][1];
        }
        else{
            return ' ';
        }
    }

    public char checkVertical(){
        for(int i = 0; i<board.length;i++){
            if(board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] != ' '){
                return board[1][i];
            }
        }
        return ' ';
    }

    public char checkHorizontal(){
        for(int i = 0; i<board.length;i++){
            if(board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] != ' '){
                return board[i][1];
            }
        }
        return ' ';
    }

    @Override
    public boolean isFinished(){ 
        if(playCounter == 9){ // max plays that can be done in a match
            return true;
        }
        else if(checkDiagonal() != ' '|| checkHorizontal() != ' ' || checkVertical() != ' ')
            return true;
        return false;
    }

    @Override
    public char checkResult(){
        char horizontal = checkHorizontal();
        char vertical = checkVertical();
        char diagonal = checkDiagonal();

        if(horizontal != ' '){
            return horizontal;
        }

        if(vertical != ' '){
            return vertical;
        }

        return diagonal;
    }
}
