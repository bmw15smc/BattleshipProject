package gameCode;


import java.util.Random;

public class AIPlayer {

    private boolean[][] attacked;
    private int size;
    private Random random = new Random();

    public AIPlayer(Board board, int size) {
        this.size = size;
        this.attacked = new boolean[size][size];
    }

    public int[] chooseAttack() {

        int row, col;

        do {
            row = random.nextInt(size);
            col = random.nextInt(size);
        } while (attacked[row][col]);

        attacked[row][col] = true;

        return new int[]{col, row};
    }
}
