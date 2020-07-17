package tictactoe.players;

import tictactoe.Coordinate;

import java.util.Random;

public class EasyAI extends BaseAI {
    protected Random rnd = new Random();

    public EasyAI() {
        this.name = "easy";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    protected Coordinate plotNextMove(char[][] cells) {
        final int bound = 3;
        return new Coordinate(rnd.nextInt(bound), rnd.nextInt(bound));
    }
}
