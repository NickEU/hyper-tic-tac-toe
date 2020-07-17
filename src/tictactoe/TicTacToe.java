package tictactoe;

import tictactoe.enums.GameState;
import tictactoe.enums.PlayerType;
import tictactoe.players.BaseAI;
import tictactoe.players.ChampionAI;
import tictactoe.players.EasyAI;
import tictactoe.players.Human;
import tictactoe.players.MediumAI;
import tictactoe.players.Player;
import tictactoe.players.TrivialAI;


public class TicTacToe {
    private static TicTacToe gameInstance;
    private final GameBoard board;
    private GameState gameState;
    private final RuleAnalyzer rules;
    private final Player playerX;
    private final Player playerY;
    private String lastMoveDiffLevel;

    private TicTacToe(String initialBoardState, Player x, Player o) {
        playerX = x;
        playerY = o;
        this.board = new GameBoard(initialBoardState);
        rules = new RuleAnalyzer(board);
        updateGameState();
    }

    public static TicTacToe startNewGame(PlayerType x, PlayerType o) {
        if (gameInstance != null && gameInstance.isInProgress()) {
            System.out.println("ERROR! Cannot create a new game when a game is already in progress");
            return null;
        }
        final String EMPTY_BOARD = "         ";
        Player xPlayer = createPlayerObj(x);
        Player yPlayer = createPlayerObj(o);
        gameInstance = new TicTacToe(EMPTY_BOARD, xPlayer, yPlayer);
        return gameInstance;
    }

    private static Player createPlayerObj(PlayerType x) {
        switch(x) {
            case TRIVIAL:
                return new TrivialAI();
            case EASY:
                return new EasyAI();
            case MEDIUM:
                return new MediumAI();
            case CHAMPION:
                return new ChampionAI();
            case HUMAN:
                return new Human();
            default:
                return null;
        }
    }

    String getStateMsg() {
        return gameState.getMsg();
    }

    public String getLastMoveDiffLevel() {
        return lastMoveDiffLevel;
    }

    public void setLastMoveDiffLevel(String lastMoveDiffLevel) {
        this.lastMoveDiffLevel = lastMoveDiffLevel;
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
            if (player instanceof BaseAI) {
                BaseAI ai = (BaseAI) player;
                ai.makeMove(this);
                setLastMoveDiffLevel(ai.getName());
                return false;
            }
        }
        throw new IllegalStateException();
    }
}
