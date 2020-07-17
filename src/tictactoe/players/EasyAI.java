package tictactoe.players;

public class EasyAI extends BaseAI {
    public EasyAI(char xOrO) {
        super(xOrO);
        this.name = "easy";
    }

    @Override
    public String getName() {
        return name;
    }
}
