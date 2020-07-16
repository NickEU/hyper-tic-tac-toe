package tictactoe;

import tictactoe.enums.PlayerType;
import tictactoe.enums.UserMoveResult;

import java.util.Scanner;

public class UserInterface {
    private final Scanner sc = new Scanner(System.in);
    private TicTacToe game;

    public void start() {
        game = TicTacToe.startNewGame(PlayerType.HUMAN, PlayerType.EASY);
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
}
