package gameCode;

public class Ship extends ShipLogic {
	private String type;
    private int size;
    private int hits;
    private boolean horizontal;

    public Ship(int size, String type, int startRow, int startCol, boolean horizontal) {
        super(startRow, startCol);
        this.type = type;
        this.size = size;
        this.horizontal = horizontal;
        this.hits = 0;
    }

    @Override
    public boolean occupies(int row, int col) {
        for (int i = 0; i < size; i++) {
            int r = horizontal ? startRow : startRow + i;
            int c = horizontal ? startCol + i : startCol;

            if (r == row && c == col) return true;
        }
        return false;
    }

    public void hit() { 
    	hits++;
    }

    public boolean isSunk(){  return hits >= size; }
    
    public String getType() {return type; }
    public int getSize() { return size; }
    public boolean isHorizontal() { return horizontal; }
}


