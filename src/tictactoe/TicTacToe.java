package tictactoe;

import tictactoe.enums.GameState;
import tictactoe.players.BaseAI;
import tictactoe.players.EasyAI;
import tictactoe.players.Human;
import tictactoe.players.Player;

public class TicTacToe {
    private final GameBoard board;
    private GameState gameState;
    private final RuleAnalyzer rules;
    private final Player playerX = new Human();
    private final Player playerY;


    TicTacToe(String initialBoardState, BaseAI difficulty) {
        playerY = difficulty;
        this.board = new GameBoard(initialBoardState);
        rules = new RuleAnalyzer(board);
        updateGameState();
    }

    TicTacToe(String initialBoardState) {
        this(initialBoardState, new EasyAI());
    }

    TicTacToe() {
        this("         ");
    }

    String getStateMsg() {
        return gameState.getMsg();
    }

    boolean isInProgress() {
        return gameState == GameState.IN_PROGRESS;
    }

    private void updateGameState() {
        if (rules.illegalXODifference() || rules.xWins() && rules.oWins()) {
            gameState = GameState.IMPOSSIBLE;
        } else if (rules.xWins()) {
            gameState = GameState.X_WINS;
        } else if (rules.oWins()) {
            gameState = GameState.O_WINS;
        } else {
            gameState = rules.noEmptyCellsLeft() ? GameState.DRAW : GameState.IN_PROGRESS;
        }
    }

    public boolean tryMakeMove(int row, int col) {
        if (gameState != GameState.IN_PROGRESS) {
            return false;
        }

        boolean moveWasMade = board.tryChangeCell(row, col);
        if (moveWasMade) {
            updateGameState();
        }
        return moveWasMade;
    }

    public boolean tryMakeMove(Coordinate coord) {
        return tryMakeMove(coord.getRow(), coord.getCol());
    }

    public char[][] getCells() {
        return board.getBoardCells();
    }

//    char getCurrentMovePiece() {
//        return rules.whoGoesNext();
//    }

    public boolean nextTurnIsHuman() {
        if (board.isNextMoveX()) {
            return checkPlayer(playerX);
        } else {
            return checkPlayer(playerY);
        }
    }

    private boolean checkPlayer(Player player) {
        if (player instanceof Human) {
            return true;
        } else {
            player.makeMove(this);
            return false;
        }
    }
}
