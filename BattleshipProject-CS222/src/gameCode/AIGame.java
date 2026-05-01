package gameCode;

import java.util.Random;
import java.util.Scanner;

public class AIGame {

    public static String toCoordinate(int row, int col) {
        return (char) ('A' + row) + Integer.toString(col + 1);
    }

    private static void pause(int ms) {			//change. creates pause for specified milliseconds
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Board playerBoard = new Board(10);
        Board aiBoard = new Board(10);

        // player options
        int choice;

        while (true) {
            try {
                System.out.print("Choose your preset (1-3): ");
                choice = Integer.parseInt(scanner.nextLine());

                if (choice >= 1 && choice <= 3) break;

                System.out.println("Please enter 1, 2, or 3.");
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
            }
        }

        PresetBoards.load(playerBoard, choice);

       
        Random rand = new Random();			//change
        int aiChoice = rand.nextInt(3) + 1;	// change
        PresetBoards.load(aiBoard, aiChoice);
        

        System.out.println("\n Battleship Game Started!\n");

       
        while (true) {

           
            System.out.println("\n=== YOUR BOARD ===");
            playerBoard.printBoard(true);
            
           //change. pause method
            pause(1000);	 

            System.out.println("\n=== ENEMY BOARD ===");
            aiBoard.printBoard(false);
            
            // change. sleep method
            pause(1000);

            System.out.println("\nYour turn:");

            HumanPlayer human = new HumanPlayer(playerBoard, scanner);
            int[] move = human.chooseAttack();

            String result = aiBoard.attack(move[0], move[1]);

            System.out.println("You attacked " + toCoordinate(move[0], move[1]) + " → " + result);
            
            //change. sleep method
            pause(1000);

            
            if (aiBoard.allShipsSunk()) {			//changed this to show final enemy board then print that you win
                System.out.println("\n=== ENEMY BOARD ===");
                aiBoard.printBoard(true);
                System.out.println("\n You win! All enemy ships destroyed.");
                break;
            }

            
            System.out.println("\nAI turn:");

            AIPlayer ai = new AIPlayer(playerBoard, 10);
            int[] aiMove = ai.chooseAttack();

            String aiResult = playerBoard.attack(aiMove[0], aiMove[1]);

            System.out.println("AI attacked " + toCoordinate(aiMove[0], aiMove[1]) + " → " + aiResult);
            
            //change. sleep method
            pause(1000);

            //checks win
            if (playerBoard.allShipsSunk()) {
                System.out.println("\n AI wins! All your ships are destroyed.");
                playerBoard.printBoard(true);
                break;
            }

        }

        scanner.close();
    }
}
