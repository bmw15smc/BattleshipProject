package gameCode;

import java.util.Scanner;

public class HumanPlayer extends Player {		//change. extends player

//    private Board board;		//change. from super class now
    private Scanner scanner;

    public HumanPlayer(Board board, Scanner scanner) {
        super(board);		//change. using constructor from superclass Player
        this.scanner = scanner;
    }

    @Override		//change to override
    public int[] chooseAttack() {

        while (true) {
            System.out.print("Enter attack (e.g. B1): ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.length() < 2) {
                System.out.println("Invalid format.");
                continue;
            }

            char letter = input.charAt(0);
            String numberPart = input.substring(1);

            if (letter < 'A' || letter > 'J') {
                System.out.println("Column must be A-J.");
                continue;
            }

            int col = letter - 'A';

            int row;
            try {
                row = Integer.parseInt(numberPart) - 1;
            } catch (Exception e) {
                System.out.println("Invalid number.");
                continue;
            }

            if (!board.isValidPosition(row, col)) {
                System.out.println("Out of bounds.");
                continue;
            }

            return new int[]{col, row};
        }
    }
}
