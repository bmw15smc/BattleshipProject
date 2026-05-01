package gameCode;

import java.util.Scanner;

//this class doesnt work yet
public class GameDriver {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Board board = new Board(10);

        int choice = 0;

        // preset selection
        while (true) {
            try {
                System.out.print("Choose preset (1-3): ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 3) break;
                System.out.println("Enter 1, 2, or 3.");
            } catch (Exception e) {
                System.out.println("Enter a valid number!");
            }
        }

        PresetBoards.load(board, choice);

        System.out.println("\nEnter moves in Battleship format (A1 - J10)\n");

        while (true) {

            board.printBoard(false);

            System.out.print("Enter coordinate (e.g. B5): ");
            String input = scanner.nextLine().trim().toUpperCase();

            // validate input length
            if (input.length() < 2) {
                System.out.println("Invalid input.");
                continue;
            }

            char letter = input.charAt(0);
            String numberPart = input.substring(1);

            if (letter < 'A' || letter > 'J') {
                System.out.println("Column must be A-J.");
                continue;
            }

            int col = letter - 'A'; // column first

            int row;
            try {
                row = Integer.parseInt(numberPart) - 1;
            } catch (Exception e) {
                System.out.println("Row must be a number 1-10.");
                continue;
            }

            if (!board.isValidPosition(row, col)) {
                System.out.println("Out of bounds.");
                continue;
            }

            String result = board.attack(row, col);

            System.out.println(result);

            if (board.allShipsSunk()) {
                System.out.println("You win!");
                board.printBoard(true);
                break;
            }
        }

        scanner.close();
    }
}
