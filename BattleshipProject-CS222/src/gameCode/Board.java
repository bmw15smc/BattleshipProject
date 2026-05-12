package gameCode;
import java.util.ArrayList;

public class Board {
    private char[][] grid;
    private ArrayList<Ship> ships;
    private int size;
    private boolean hitNuke = false;

    public Board(int size) {
        this.size = size;
        grid = new char[size][size];
        ships = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = '~';
            }
        }
    }

    public static String toCoordinate(int row, int col) {
        return (char) ('A' + row) + Integer.toString(col + 1);
    }
    
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    public void addShip(Ship ship) {
        
        for (int i = 0; i < ship.getSize(); i++) {
            int r = ship.isHorizontal() ? ship.startRow : ship.startRow + i;
            int c = ship.isHorizontal() ? ship.startCol + i : ship.startCol;

            if (!isValidPosition(r, c)) {
                throw new IllegalArgumentException("Ship out of bounds! Issue: " + ship.getType());
            }

            if (grid[r][c] == '\u25a0') {
                throw new IllegalArgumentException("Ships overlap! Issue: " + ship.getType());
            }
        }

        ships.add(ship);

        for (int i = 0; i < ship.getSize(); i++) {
            int r = ship.isHorizontal() ? ship.startRow : ship.startRow + i;
            int c = ship.isHorizontal() ? ship.startCol + i : ship.startCol;

            if(ship.getType().equals("Nuke")) {
            	grid[r][c] = '8';		// nuke symbol
            }
            else{
            	grid[r][c] = '\u25a0';		// looked up unicode symbol
            }
        }
    }

    public String attack(int col, int row) {

        if (!isValidPosition(row, col)) {
            return "Out of bounds!";
        }

        if (grid[row][col] == 'X' || grid[row][col] == 'O') {
            return "already attacked";
        }

        for (Ship ship : ships) {
            if (ship.occupies(row, col)) {
                ship.hit();
                grid[row][col] = 'X';
                
                if(ship.getType().equals("Nuke")) {	
                	hitNuke();
                	return("NUKE");
                }
                
              if(ship.isSunk()){
            	  return "Hit! " + ship.getType() + " is sunk!";
              }
              return "Hit!";
            }
        }

        grid[row][col] = 'O';
        return "Miss!";
    }

    public void hitNuke() {
    	this.hitNuke = true;
    }
    
    public boolean allShipsSunk() {
    	if(hitNuke) {
    		return true;
    	}
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

  
    public void printBoard(boolean showShips) {

        System.out.print("   ");
        for (int col = 0; col < size; col++) {
            System.out.print((char) ('A' + col) + " ");
        }
        System.out.println();

        for (int row = 0; row < size; row++) {

            System.out.printf("%2d ", row + 1);

            for (int col = 0; col < size; col++) {

                char display = grid[row][col];

                if (!showShips && (display == '\u25a0' || display == '8')) {
                    display = '~';
                }

                System.out.print(display + " ");
            }

            System.out.println();
        }
    }
}
