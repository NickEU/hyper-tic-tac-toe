package tictactoe.players;

import tictactoe.Coordinate;
import tictactoe.RuleAnalyzer;

import static tictactoe.GameBoard.EMPTY_CELL;
import static tictactoe.GameBoard.O_CHAR;
import static tictactoe.GameBoard.X_CHAR;

public class MediumAI extends BaseAI {
    public MediumAI(char xOrO) {
        super(xOrO);
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
        Coordinate potentialMove = winIfYouCan(piece, cells);
        if (potentialMove != null) {
            return potentialMove;
        }
        // If the opponent can win on the next move,
        // it blocks the opponent from winning.
        char opponent = piece == X_CHAR ? O_CHAR : X_CHAR;
        potentialMove = winIfYouCan(opponent, cells);
        if (potentialMove != null) {
            return potentialMove;
        }
        // otherwise makes a random move
        return super.plotNextMove(cells);
    }

    private Coordinate winIfYouCan(char piece, char[][] cells) {
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
