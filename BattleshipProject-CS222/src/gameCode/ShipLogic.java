package gameCode;

public abstract class ShipLogic {
	protected int startRow;
    protected int startCol;

    public ShipLogic(int startRow, int startCol) {
        this.startRow = startRow;
        this.startCol = startCol;
    }

    public abstract boolean occupies(int row, int col);
}

