package gameCode;
import java.util.ArrayList;

public class Board {
    private char[][] grid;
    private ArrayList<Ship> ships;
    private int size;

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
                throw new IllegalArgumentException("Ship out of bounds! Issue: " + ship.getType()); //changed to show which ship is the issue
            }

            if (grid[r][c] == '\u25a0') {		//change ship symbol
                throw new IllegalArgumentException("Ships overlap! Issue: " + ship.getType());	//same change
            }
        }

        ships.add(ship);

        for (int i = 0; i < ship.getSize(); i++) {
            int r = ship.isHorizontal() ? ship.startRow : ship.startRow + i;
            int c = ship.isHorizontal() ? ship.startCol + i : ship.startCol;

            grid[r][c] = '\u25a0';		//changed S to a square to see better (unicode)
        }
    }

    public String attack(int col, int row) {

        if (!isValidPosition(row, col)) {
            return "Out of bounds!";
        }

        if (grid[row][col] == 'X' || grid[row][col] == 'O') {
            return "Already attacked!";
        }

        for (Ship ship : ships) {
            if (ship.occupies(row, col)) {
                ship.hit();
                grid[row][col] = 'X';
                
              if(ship.isSunk()){								//changed so it prints when a ship is sunk
            	  return "Hit! " + ship.getType() + " is sunk!";
              }
              return "Hit!";
            }
        }

        grid[row][col] = 'O';
        return "Miss!";
    }

    public boolean allShipsSunk() {
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

                if (!showShips && display == '\u25a0') {		//changed S to square
                    display = '~';
                }

                System.out.print(display + " ");
            }

            System.out.println();
        }
    }
}
