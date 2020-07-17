package tictactoe.players;

import tictactoe.Coordinate;

public class TrivialAI extends BaseAI {
    public TrivialAI() {
        this.name = "trivial";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    protected Coordinate plotNextMove(char[][] cells) {
        // do trivial stuff
        return super.plotNextMove(cells);
    }
}
