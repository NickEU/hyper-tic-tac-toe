package tictactoe;

import tictactoe.enums.PlayerType;
import tictactoe.enums.UserMoveResult;

import java.util.Scanner;

public class UserInterface {
    private final Scanner sc = new Scanner(System.in);
    private TicTacToe game;
    private PlayerType playerX;
    private PlayerType playerY;

    public void startPhase3() {
        while (true) {
            boolean rdyToLeave = mainMenuLoopUntilExit();
            if (rdyToLeave) {
                break;
            }
            startNewGame();
        }
    }

    private boolean mainMenuLoopUntilExit() {
        String mainLoopErrorMsg = "Bad parameters";
        while (true) {
            System.out.print("Input command: ");
            String[] userInput = sc.nextLine().split(" ");
            String cmd = userInput[0];
            if ("exit".equals(cmd)) {
                return true;
            }

            if (userInput.length != 3) {
                System.out.println(mainLoopErrorMsg);
                continue;
            }

            if ("start".equals(cmd)) {
                String argX = userInput[1];
                String argY = userInput[2];

                if (userArgParseFailed(argX) || userArgParseFailed(argY)) {
                    System.out.println(mainLoopErrorMsg);
                } else {
                    game = TicTacToe.startNewGame(playerX, playerY);
                    resetUserInputArgs();
                    return false;
                }
            } else {
                System.out.println(mainLoopErrorMsg);
            }
        }
    }

    private boolean userArgParseFailed(String player) {
        boolean isFirstArg = playerX == null;
        PlayerType userChosen;
        switch (player) {
            case "user":
                userChosen = PlayerType.HUMAN;
                break;
            case "trivial":
                userChosen = PlayerType.TRIVIAL;
                break;
            case "easy":
                userChosen = PlayerType.EASY;
                break;
            case "medium":
                userChosen = PlayerType.MEDIUM;
                break;
            case "hard":
                userChosen = PlayerType.HARD;
                break;
            default:
                resetUserInputArgs();
                return true;
        }

        if (isFirstArg) {
            playerX = userChosen;
        } else {
            playerY = userChosen;
        }

        return false;
    }

    public void startNewGame() {
        renderGameBoard();
        while (game.isInProgress()) {
            if (game.nextTurnIsHuman()) {
                getMoveCoordinatesFromUser();
            } else {
                System.out.printf("Making move level \"%s\"\n",
                        game.getLastMoveDiffLevel());
                renderGameBoard();
            }
        }
        // print final result at the end
        System.out.println(game.getStateMsg());
    }

    private void getMoveCoordinatesFromUser() {
        while (true) {
            System.out.println("Enter the coordinates: ");
            String userInput = sc.nextLine();
            UserMoveResult parseResult = parseUserMoveInput(userInput);
            if (parseResult == UserMoveResult.SUCCESS) {
                renderGameBoard();
                break;
            }
            System.out.println(parseResult.getMsg());
        }
    }

    UserMoveResult parseUserMoveInput(String userInput) {
        try {
            String[] coords = userInput.split(" ");
            int userRow = Integer.parseInt(coords[0]);
            int userCol = Integer.parseInt(coords[1]);
            if (coordinateOutOfBounds(userRow) || coordinateOutOfBounds(userCol)) {
                return UserMoveResult.FAIL_OUT_OF_BOUNDS;
            }
            int row = Math.abs(userCol - 3);
            int col = userRow - 1;
            return game.tryMakeMove(row, col)
                    ? UserMoveResult.SUCCESS
                    : UserMoveResult.FAIL_CELL_OCCUPIED;
        } catch(Exception e) {
            return UserMoveResult.FAIL_NAN;
        }
    }

    private boolean coordinateOutOfBounds(int coord) {
        return coord < 1 || coord > 3;
    }

    private void renderGameBoard() {
        String DECOR_BORDER = "---------";
        System.out.println(DECOR_BORDER);
        for (char[] row : game.getCells()) {
            System.out.printf("| %c %c %c |\n", row[0], row[1], row[2]);
        }
        System.out.println(DECOR_BORDER);
    }

    private void resetUserInputArgs() {
        playerX = null;
        playerY = null;
    }
}
