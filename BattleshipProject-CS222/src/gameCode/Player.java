package gameCode;

public abstract class Player {
    protected Board board;

    public Player(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public abstract int[] chooseAttack();
}
