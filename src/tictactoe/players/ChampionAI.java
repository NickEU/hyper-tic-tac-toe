package tictactoe.players;

import tictactoe.Coordinate;

public class ChampionAI extends BaseAI {
    public ChampionAI(char xOrO) {
        super(xOrO);
        this.name = "champion";
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
