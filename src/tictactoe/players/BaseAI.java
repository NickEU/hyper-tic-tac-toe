package tictactoe.players;

import tictactoe.Coordinate;
import tictactoe.TicTacToe;

public class BaseAI extends Player {
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
        System.out.println("OOPS!");
        return new Coordinate(1, 1);
    }
}
