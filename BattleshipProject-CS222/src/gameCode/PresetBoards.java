package gameCode;

public class PresetBoards {

    public static void load(Board board, int choice) {

        if (choice == 1) {
            preset1(board);
        } 
        else if (choice == 2) {
            preset2(board);
        } 
        else if (choice == 3) {
            preset3(board);
        } 
        else {
            System.out.println("Invalid choice. Loading preset 1.");
            preset1(board);
        }
    }

    
    //change. dont need try catch in here bc it will be caught in board.
    private static void preset1(Board board) {

//        try {
            board.addShip(new Ship(5,"Carrier", 0, 0, true));
            board.addShip(new Ship(4,"Battleship", 2, 2, false));
            board.addShip(new Ship(3,"Cruiser", 5, 7, false));		//changed to column 4 bc was overlapping before (5,7)
            board.addShip(new Ship(3,"Submarine", 7, 5, false));
            board.addShip(new Ship(2,"Destroyer", 9, 7, true));
//        } catch (Exception e) {
//            System.out.println("Preset1 ship error: " + e);
//       }
    }
   
    private static void preset2(Board board) {

//        try {
            board.addShip(new Ship(5,"Carrier", 1, 1, false));
            board.addShip(new Ship(4,"Battleship",0, 6, true));
            board.addShip(new Ship(3,"Cruiser",4, 3, true));
            board.addShip(new Ship(3,"Submarine",6, 8, false));
            board.addShip(new Ship(2,"Destroyer", 9, 0, true));
//        } catch (Exception e) {
//            System.out.println("Preset2 error: " + e);
//        }
    }

    private static void preset3(Board board) {

//        try {
            board.addShip(new Ship(5,"Carrier", 5, 0, true));
            board.addShip(new Ship(4,"Battleship", 0, 0, false));
            board.addShip(new Ship(3,"Cruiser", 2, 6, true));
            board.addShip(new Ship(3,"Submarine", 7, 2, false));
            board.addShip(new Ship(2,"Destroyer", 9, 8, true));
//        } catch (Exception e) {
//            System.out.println("Preset3 error: " + e);
//        }
    }
   
    
}
