package tictactoe.players;

import tictactoe.Coordinate;

public class MediumAI extends BaseAI {
    public MediumAI(char xOrO) {
        super(xOrO);
        this.name = "medium";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    protected Coordinate plotNextMove(char[][] cells) {
        // If it can win in one move (if it has two in a row),
        // it places a third to get three in a row and win.
        Coordinate potentialMove = winIfYouCan(myPiece, cells);
        if (potentialMove != null) {
            return potentialMove;
        }
        // If the opponent can win on the next move,
        // it blocks the opponent from winning.
        potentialMove = winIfYouCan(opponentsPiece, cells);
        if (potentialMove != null) {
            return potentialMove;
        }
        // otherwise makes a random move
        return super.plotNextMove(cells);
    }
}
