package gameCode;

import java.util.Scanner;

public class PvpGameDriver {

	   private static void pause(int ms) { //creates the pause method with specified times
	       try {
	           Thread.sleep(ms);
	       } catch (InterruptedException e) {
	           Thread.currentThread().interrupt();
	       }
	   }
	   private static void clearScreen() { //method for clearing the screen with a lot of spaces
	       for (int i = 0; i < 60; i++) {
	           System.out.println();
	       }
	   }
	   public static void main(String[] args) {
	       Scanner scanner = new Scanner(System.in);//new scanner
	       Board p1Board = new Board(10);
	       Board p2Board = new Board(10);//creates the boards
	       // Player 2 chooses board first
	       System.out.println("=== BATTLESHIP PvP MODE ===");
	       pause(2000);
	       System.out.println("Player 2, choose your preset first.");
	       System.out.print("Player 2 preset (1-5): ");
	       int p2Choice = Integer.parseInt(scanner.nextLine());
	       PresetBoards.load(p2Board, p2Choice); //player chooses and board is loaded
	       System.out.println("\nPlayer 2 setup complete.");
	       pause(2000);
	       clearScreen();
	       System.out.println("Player 1, choose your preset.");
	       System.out.print("Player 1 preset (1-5): ");
	       int p1Choice = Integer.parseInt(scanner.nextLine());
	       PresetBoards.load(p1Board, p1Choice); //player chooses and board is loaded
	       System.out.println("\nPlayer 1 setup complete.");
	       pause(2000);
	       clearScreen();
	       Player p1 = new HumanPlayer(p1Board, scanner);
	       Player p2 = new HumanPlayer(p2Board, scanner); //creates the players and their logic
	       System.out.println("Game starting...");
	       pause(2500);
	       clearScreen();
	       // game loop
	       while (true) {
	           // player 1 turn
	           System.out.println("=== PLAYER 1 VIEW (YOUR SHIPS) ===");
	           p1Board.printBoard(true); //shows the player their ships
	           pause(1500);
	           System.out.println("\n=== ENEMY BOARD (HITS / MISSES) ===");
	           p2Board.printBoard(false);//shows the players hits and misses on a board
	           int[] move1;//initiates move
	           while (true) {
	               System.out.println("\nPlayer 1 attack:");
	               move1 = p1.chooseAttack();
	               String result = p2Board.attack(move1[0], move1[1]);//player makes their move
	               if (result.equals("already attacked")) {
	                   System.out.println("Already attacked. Try again.");//in case of the space already being
	               } else {
	                   System.out.println("Result: " +
	                       Board.toCoordinate(move1[0], move1[1]) +
	                       " → " + result);//shows where attacked and the result of the attack
	                   break;
	               }
	           }
	           if (p2Board.allShipsSunk()) { //check win
	               System.out.println("\nPLAYER 1 WINS!");
	               break;
	           }
	           // switches turn for 4 seconds
	           System.out.println("\nTURN OVER");
	           System.out.println("Please look away.");
	           System.out.println("Switching boards...");
	           pause(4000);
	           clearScreen();
	           // player 2 turn
	           System.out.println("=== PLAYER 2 VIEW (YOUR SHIPS) ===");
	           p2Board.printBoard(true);
	           pause(1500);
	           System.out.println("\n=== ENEMY BOARD (HITS / MISSES) ===");
	           p1Board.printBoard(false);
	           int[] move2; //initates move
	           while (true) {
	               System.out.println("\nPlayer 2 attack:");
	               move2 = p2.chooseAttack();
	               String result = p1Board.attack(move2[0], move2[1]);
	               if (result.equals("already attacked")) {
	                   System.out.println("Already attacked. Try again.");
	               } else {
	                   System.out.println("Result: " +
	                       Board.toCoordinate(move2[0], move2[1]) +
	                       " → " + result);
	                   break;
	               }
	           }
	           if (p1Board.allShipsSunk()) { //check win
	               System.out.println("\nPLAYER 2 WINS!");
	               break;
	           }
	           // switching turn
	           System.out.println("\nTURN OVER");
	           System.out.println("Please look away.");
	           System.out.println("Switching boards...");
	           pause(4000);
	           clearScreen();
	       }
	       scanner.close();
	   }
	}

