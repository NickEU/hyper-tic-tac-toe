package tictactoe.players;

import tictactoe.Coordinate;

public class EasyAI extends BaseAI {
    @Override
    protected Coordinate plotNextMove(char[][] cells) {
        System.out.println("easy");
        return new Coordinate(2, 2);
    }
}
