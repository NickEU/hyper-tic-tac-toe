package tictactoe.players;

import tictactoe.Coordinate;
import tictactoe.RuleAnalyzer;

import static tictactoe.GameBoard.EMPTY_CELL;

public class HardAI extends BaseAI {
    public HardAI(char xOrO) {
        super(xOrO);
        this.name = "hard";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    protected Coordinate plotNextMove(char[][] cells) {
        return findMinimaxCoordinate(cells);
    }

    private Coordinate findMinimaxCoordinate(char[][] cells) {
        Coordinate backup = null;

        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[0].length; col++) {
                rules = new RuleAnalyzer(cells);
                if (rules.cellIsEmpty(row, col)) {
                    // block from winning
                    cells[row][col] = myPiece;
                    Coordinate potentialMove = winIfYouCan(opponentsPiece, cells);
                    if (potentialMove != null) {
                        return potentialMove;
                    }
                    // need more research on minimax,
                    // does not work.
                    int recursiveResult = findMinimax(cells, false);
                    if (recursiveResult > 0) {
                        return new Coordinate(row, col);
                    } else if (recursiveResult == 0 && backup == null) {
                        backup = new Coordinate(row, col);
                    }
                    cells[row][col] = EMPTY_CELL;
                }
            }
        }
        return backup;
    }

    private int findMinimax(char[][] cells, boolean isMaximizing) {
        rules = new RuleAnalyzer(cells);
        if (rules.charHasWon(myPiece)) {
            return 1;
        } else if (rules.charHasWon(opponentsPiece)) {
            return -1;
        } else if (rules.noEmptyCellsLeft()) {
            return 0;
        }

        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[0].length; col++) {
                rules = new RuleAnalyzer(cells);
                if (rules.cellIsEmpty(row, col)) {
                    cells[row][col] = isMaximizing ? myPiece : opponentsPiece;
                    int recursiveResult = findMinimax(cells, !isMaximizing);
                    cells[row][col] = EMPTY_CELL;
                    return recursiveResult;
                }
            }
        }

        throw new IllegalStateException();
    }
}
