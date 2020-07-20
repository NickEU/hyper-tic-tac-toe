package tictactoe.players;

import static tictactoe.GameBoard.O_CHAR;
import static tictactoe.GameBoard.X_CHAR;

public class Player {
    protected char myPiece;
    protected char opponentsPiece;

    public Player(char xOrO) {
        this.myPiece = xOrO;
        opponentsPiece = xOrO == X_CHAR ? O_CHAR : X_CHAR;
    }

    protected char getMyPiece() {
        return myPiece;
    }
}
