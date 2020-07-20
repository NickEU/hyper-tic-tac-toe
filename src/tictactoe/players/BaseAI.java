package tictactoe.players;

import tictactoe.Coordinate;
import tictactoe.RuleAnalyzer;
import tictactoe.TicTacToe;

import java.util.Arrays;
import java.util.Random;

import static tictactoe.GameBoard.EMPTY_CELL;
import static tictactoe.GameBoard.O_CHAR;
import static tictactoe.GameBoard.X_CHAR;

public class BaseAI extends Player {
    protected Random rnd = new Random();
    protected String name = "base";
    protected RuleAnalyzer rules;

    public BaseAI(char xOrO) {
        super(xOrO);
    }

    public String getName() {
        return name;
    }

    public void makeMove(TicTacToe game) {
        char[][] original = game.getCells();
        char[][] cells = new char[original.length][original[0].length];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = Arrays.copyOf(original[i], original[i].length);
        }

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

    protected Coordinate winIfYouCan(char piece, char[][] cells) {
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[0].length; col++) {
                if (cells[row][col] != X_CHAR && cells[row][col] != O_CHAR) {
                    cells[row][col] = piece;
                    rules = new RuleAnalyzer(cells);
                    if (rules.charHasWon(piece)) {
                        cells[row][col] = EMPTY_CELL;
                        return new Coordinate(row, col);
                    } else {
                        cells[row][col] = EMPTY_CELL;
                    }
                }
            }
        }
        return null;
    }
}
