package gameCode;

import java.util.Random;
import java.util.Scanner;

public class AIGameDriver {

	
    private static void pause(int ms) {	
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
        int choice;
        
        System.out.println("Battleship Game Started!\n");
        
        pause(1000);

        while (true) {
            try {
                System.out.print("Choose your preset board (1-5): ");
                choice = Integer.parseInt(scanner.nextLine());

                if (choice >= 1 && choice <= 5) break;

                System.out.println("Please enter 1, 2, 3, 4, or 5.");
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
            }
        }

        PresetBoards.load(playerBoard, choice);

       
        Random rand = new Random();			
        int aiChoice = rand.nextInt(5) + 1;
        PresetBoards.load(aiBoard, aiChoice);
        
        Player human = new HumanPlayer(playerBoard, scanner);
        Player ai = new AIPlayer(aiBoard, 10);

       
        while (true) {

           
            System.out.println("\n=== YOUR BOARD ===");
            playerBoard.printBoard(true);
            
            pause(1000);	 

            System.out.println("\n=== ENEMY BOARD ===");
            aiBoard.printBoard(false);
            
            pause(1000);

            
           while(true) { 
            System.out.println("\nYour turn:");
	
            int[] move = human.chooseAttack();

            String result = aiBoard.attack(move[0], move[1]);
            
            if(result.equals("NUKE")) {		
            	System.out.println("BOOM! You hit the computer's Nuke!");
            	break;
            }
            else if(result.equals("already attacked")) {
            	System.out.println("Already attacked! Pick a different coordinate!");
            } else {
            	System.out.println("You attacked " + Board.toCoordinate(move[0], move[1]) + " → " + result);
            	break;
            }
           }
    
            pause(1000);

            
            if (aiBoard.allShipsSunk()) {
                System.out.println("\n=== ENEMY BOARD ===");
                aiBoard.printBoard(true);
                System.out.println("\nYou win! All enemy ships destroyed.");
                break;
            }

            
            System.out.println("\nAI turn:");

            int[] aiMove = ai.chooseAttack();

            String aiResult = playerBoard.attack(aiMove[0], aiMove[1]);
            
            if(aiResult.equals("NUKE")) {
            	System.out.println("BOOM! The computer hit your Nuke!");
            }
           
            System.out.println("AI attacked " + Board.toCoordinate(aiMove[0], aiMove[1]) + " → " + aiResult);
            
            pause(1000);

            if (playerBoard.allShipsSunk()) {
            	System.out.println("\n=== YOUR BOARD ===");
            	playerBoard.printBoard(true);
                System.out.println("\nAI wins! All your ships are destroyed.");
                break;
            }

        }

        scanner.close();
    }
}
