package tictactoe.players;

import tictactoe.Coordinate;

public class ChampionAI extends BaseAI {
    public ChampionAI() {
        this.name = "champion";
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
