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
        // to be implemented
        return new Coordinate(2, 2);
    }
}
