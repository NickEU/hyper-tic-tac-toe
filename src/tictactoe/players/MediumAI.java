package tictactoe.players;

import tictactoe.Coordinate;

public class MediumAI extends BaseAI {
    public MediumAI() {
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

        // do stuff

        // If the opponent can win in one move,
        // it plays the third itself to block the opponent to win.

        // do stuff

        return super.plotNextMove(cells);
    }
}
