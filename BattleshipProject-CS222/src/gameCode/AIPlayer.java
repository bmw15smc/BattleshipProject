package gameCode;


import java.util.Random;

public class AIPlayer extends Player {

    private boolean[][] attacked;
    private int size;
    private Random random = new Random();

    public AIPlayer(Board board, int size) {
    	super(board);
        this.size = size;
        this.attacked = new boolean[size][size];
    }

    @Override
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
