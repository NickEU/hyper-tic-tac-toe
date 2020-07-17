package tictactoe.players;

public class Player {
    protected char piece;

    public Player(char xOrO) {
        this.piece = xOrO;
    }

    protected char getPiece() {
        return piece;
    }
}
