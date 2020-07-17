package tictactoe;

public class GameBoard {
    public static final char X_CHAR = 'X';
    public static final char O_CHAR = 'O';
    public static final char EMPTY_CELL = ' ';
    private final char[][] boardCells;
    private final XOChar curMovePiece = new XOChar(X_CHAR);

    GameBoard(String initialBoardState) {
        int BOARD_SIZE = 3;
        boardCells = new char[BOARD_SIZE][BOARD_SIZE];
        for (int j = 0, i = 0; j < BOARD_SIZE; j++) {
            for (int k = 0; k < BOARD_SIZE; k++) {
                boardCells[j][k] = initialBoardState.charAt(i);
                i++;
            }
        }
    }

    char[][] getBoardCells() {
        return this.boardCells.clone();
    }

    boolean cellIsOccupied(int row, int col) {
        char targetCell = boardCells[row][col];
        return targetCell == X_CHAR || targetCell == O_CHAR;
    }

    boolean tryChangeCell(int row, int col) {
        if (cellIsOccupied(row, col)) {
            return false;
        }

        boardCells[row][col] = curMovePiece.getCurrent();
        curMovePiece.next();
        return true;
    }

    boolean isNextMoveX() {
        return curMovePiece.getCurrent() == X_CHAR;
    }

    private static class XOChar {
        private char current;

        public XOChar(char startingChar) {
            current = startingChar;
        }

        char getCurrent() {
            return current;
        }

        void next() {
            current = current == X_CHAR ? O_CHAR : X_CHAR;
        }
    }
}
