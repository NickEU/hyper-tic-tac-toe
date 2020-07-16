package tictactoe.players;

import tictactoe.Coordinate;

public class EasyAI extends BaseAI {
    public EasyAI() {
        this.name = "easy";
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
