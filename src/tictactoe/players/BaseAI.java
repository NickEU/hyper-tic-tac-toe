package tictactoe.players;

import tictactoe.Coordinate;
import tictactoe.TicTacToe;

import java.util.Random;

public class BaseAI extends Player {
    protected Random rnd = new Random();
    protected String name = "base";

    public String getName() {
        return name;
    }

    public void makeMove(TicTacToe game) {
        char[][] cells = game.getCells();
        boolean madeTheMove;
        do {
            Coordinate moveCoords = plotNextMove(cells);
            madeTheMove = game.tryMakeMove(moveCoords);
        } while (!madeTheMove);
    }

    protected Coordinate plotNextMove(char[][] cells) {
        final int bound = 3;
        return new Coordinate(rnd.nextInt(bound), rnd.nextInt(bound));
    }
}
