package tictactoe.enums;

public enum UserMoveResult {
    SUCCESS(""),
    FAIL_CELL_OCCUPIED("This cell is occupied! Choose another one!"),
    FAIL_NAN("You should enter numbers!"),
    FAIL_OUT_OF_BOUNDS("Coordinates should be from 1 to 3!");

    private final String msg;

    UserMoveResult(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
