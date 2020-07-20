package tictactoe.players;

import tictactoe.Coordinate;

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
        // do champion stuff
        return super.plotNextMove(cells);
    }


}
