import ecs100.*;
import java.awt.Color;

/**
Scrabble Board
methods:
- constructor
- boolean on(double x, double y) : is the point x,y on the board
- int[] rowCol(double x, double y) : returns the row/col at the point x,y on the board
- Tile pickup(int row, int col) : pick up the tile in the cell at row/col (null if no tile)
- boolean place(Tile tile, int row, int col) : place tile at the cell at row/col on the board
(return true if successful, and false if unsuccessful (no space at row/col)
- boolean validPlay() :  the working tiles constitute a valid play.
- void commit() :  commit all the working tiles, if it is a valid play.
- void draw() :  draw the board
- void reset() : reset the board to initial empty state.

NOTE: you MUST use these methods for this class so that we can run an automated test
procedure to check your code (copy of it is given at the end of the class).
 */

public class Board{
    // The dimensions of the board
    private static final int BoardSize = 15;
    private static final double CellSize = Tile.Size;

    public static final double Left = 70;
    public static final double Top = 10;
    public static final double Right = Left + BoardSize * CellSize;
    public static final double Bot = Top + BoardSize * CellSize;

    private Color [][] color = new Color [BoardSize][BoardSize];

    private Tile [][] tiles = new Tile [BoardSize][BoardSize];
    private Tile [][] tilePlay = new Tile [BoardSize][BoardSize];

    int win_r1, win_c1, win_r2, win_c2;
    /** Construct a new Board object */
    public Board(){
        /**
         * Layout of Colors on board.
         */
        String [] [] specials = {
                {"TW", "NO", "NO", "DL", "NO", "NO", "NO", "TW", "NO", "NO", "NO", "DL", "NO", "NO", "TW"},
                {"NO", "DW", "NO", "NO", "NO", "TL", "NO", "NO", "NO", "TL", "NO", "NO", "NO", "DW", "NO"},
                {"NO", "NO", "DW", "NO", "NO", "NO", "DL", "NO", "DL", "NO", "NO", "NO", "DW", "NO", "NO"},
                {"DL", "NO", "NO", "DW", "NO", "NO", "NO", "DL", "NO", "NO", "NO", "DW", "NO", "NO", "DL"},
                {"NO", "NO", "NO", "NO", "DW", "NO", "NO", "NO", "NO", "NO", "DW", "NO", "NO", "NO", "NO"},
                {"NO", "TL", "NO", "NO", "NO", "TL", "NO", "NO", "NO", "TL", "NO", "NO", "NO", "TL", "NO"},
                {"NO", "NO", "DL", "NO", "NO", "NO", "DL", "NO", "DL", "NO", "NO", "NO", "DL", "NO", "NO"},
                {"TW", "NO", "NO", "DL", "NO", "NO", "NO", "DW", "NO", "NO", "NO", "DL", "NO", "NO", "TW"},
                {"NO", "NO", "DL", "NO", "NO", "NO", "DL", "NO", "DL", "NO", "NO", "NO", "DL", "NO", "NO"},
                {"NO", "TL", "NO", "NO", "NO", "TL", "NO", "NO", "NO", "TL", "NO", "NO", "NO", "TL", "NO"},
                {"NO", "NO", "NO", "NO", "DW", "NO", "NO", "NO", "NO", "NO", "DW", "NO", "NO", "NO", "NO"},
                {"DL", "NO", "NO", "DW", "NO", "NO", "NO", "DL", "NO", "NO", "NO", "DW", "NO", "NO", "DL"},
                {"NO", "NO", "DW", "NO", "NO", "NO", "DL", "NO", "DL", "NO", "NO", "NO", "DW", "NO", "NO"},
                {"NO", "DW", "NO", "NO", "NO", "TL", "NO", "NO", "NO", "TL", "NO", "NO", "NO", "DW", "NO"},
                {"TW", "NO", "NO", "DL", "NO", "NO", "NO", "TW", "NO", "NO", "NO", "DL", "NO", "NO", "TW"}                     
            };
        for (int row = 0; row<BoardSize; row++) {
            for (int col = 0; col<BoardSize; col++) { 
                if (specials[row] [col]== "NO") {
                    color [row] [col] = new Color(255, 222, 173);
                    //UI.setColor(new Color(255, 222, 173));
                } else if (specials[row] [col]== "TW") {
                    color [row] [col] = Color.red;
                    //UI.setColor(Color.red);
                } else if (specials[row] [col]== "DL") {
                    color [row] [col] = new Color(135, 206, 250);
                    //UI.setColor(new Color(135, 206, 250));
                } else if (specials[row] [col]== "DW") {
                    color [row] [col] = Color.pink;
                    //UI.setColor(Color.pink);
                } else if (specials[row] [col]== "TL") {
                    color [row] [col] = Color.blue;
                    //UI.setColor(Color.blue);
                }
            }
        }
    }

    /** Is the position (x,y) on the board */
    public boolean on(double x, double y){
        /*# YOUR CODE HERE */
        return (y>=Top && y < Bot  && x>=Left && x < Right);
    }

    /**
     * Return the row/col corresponding to the point x,y.
     */
    public int[] rowCol(double x, double y){
        /*# YOUR CODE HERE */
        int row = (int) ((y-Top)/CellSize);
        int col = (int) ((x-Left)/CellSize);
        return new int[]{row, col};
    }

    /**
     * Pickup tile from the board, if the board position contains
     * a working tile and return it.
     */
    public Tile pickup(int row, int col){
        /*# YOUR CODE HERE */
        if (row<0 || row >= BoardSize || col<0 || col >= BoardSize){
            return null;
        }
        if (tilePlay[row][col] !=null) {
            Tile t = tilePlay[row][col];
            tilePlay[row][col] = null;
            return t;
        }
        return null;
    }

    /**
     * Place the tile on the board, if the board position is empty
     */
    public boolean place(Tile tile, int row, int col){
        /*# YOUR CODE HERE */
        if (row<0 || row >=BoardSize || col<0 || col >=BoardSize) { return false; }
        if (tiles[row][col]== null && tilePlay[row][col]==null) { 
            tilePlay[row][col] = tile;
            return true;
        }
        return false;
    }

    /**
     * Commit all the workingTiles to the board
     */
    public void commit(){
        /*# YOUR CODE HERE */
        for (int r = 0; r<BoardSize; r++) {
            for (int c = 0; c<BoardSize; c++) {
                if (tilePlay[r][c]!=null) { 
                    tiles [r][c] = tilePlay[r][c];
                    tilePlay[r][c] =null;
                }
            }
        }
    }

    /**
     * Returns true if the working tiles consitute a valid play:
     * The tiles must all be on a single line (row or column) and with no gaps.
     *  "No gaps" means that there must not be any empty cell between any pair
     *  of moveable tiles, though the movable tiles are not necessarily adjacent
     *  if there are any fixed tiles between the movable tiles.
     * At least one of the moveable tiles must be adjacent to a fixed tile,
     * unless it is the very first turn, in which case there are no fixed tiles.
     */
    public boolean validPlay(){
        int row = -1;
        int col = -1;
        int countWorking =0;
        int countFixed=0;
        for (int r=0; r<BoardSize; r++) {
            for (int c=0; c<BoardSize; c++) {
                if (tilePlay [r][c] != null) {
                    countWorking++;
                    if (row==-1) {row=r;}
                    else if (row!=r) {row=-100;}
                    if (col==-1) {col=c;}
                    else if (col!=c) {col=-100;}
                }
                if (tiles [r][c]!=null) {
                    countFixed++;
                }
            }
        }

        if (countWorking ==0) { return false; }
        if ((row==-100) && (col==-100)) { return false;}
        boolean adjacent = false;
        if (row>=0) {
            boolean started=false;
            boolean ended=false;
            for (int c=0; c<BoardSize; c++) {
                if (!started && tilePlay [row][c]!=null) {
                    started = true;
                } else if (started && !ended && tiles [row][c]!=null) {
                    adjacent = true;
                } else if (started && !ended && tilePlay [row][c]==null && tiles [row][c]==null) {
                    ended = true;
                } else if (ended && tilePlay [row][c]!=null) {
                    return false;
                }
            }
        } else if (col>=0) {
            boolean started=false;
            boolean ended=false;
            for (int r=0; r<BoardSize; r++) {
                if (!started && tilePlay [r][col]!=null) {
                    started = true;
                } else if (started && !ended && tiles [r][col]!=null) {
                    adjacent = true;
                } else if (started && !ended && tilePlay [r][col]==null && tiles [r][col]==null) {
                    ended = true;
                } else if (ended && tilePlay [r][col]!=null) {
                    return false;
                }
            }
        }

        if (countFixed ==0){
            return true;
        }
        if (adjacent) {
            return true;
        }

        for (int r=0; r<BoardSize; r++) {
            for (int c=0; c<BoardSize; c++) {
                if (tilePlay [r][c]!=null) {
                    if ((r-1>=0 && tiles [r-1][c] !=null) ||
                    (r+1<BoardSize && tiles [r+1][c] !=null) ||
                    (c-1>=0 && tiles [r][c-1] !=null) ||
                    (c+1<BoardSize && tiles [r][c+1] !=null)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Draw the board.
     * Assumes that the graphics pane has been cleared
     */
    public void draw(){
        /*# YOUR CODE HERE */

        double y = Top;
        for (int row=0; row<BoardSize; row++){
            double x = Left;
            for (int col=0; col<BoardSize; col++){

                UI.setColor(color [row] [col]);
                UI.fillRect(x, y, CellSize, CellSize);
                UI.setColor(Color.black);
                UI.drawRect(x, y, CellSize, CellSize);

                if (tiles [row][col] !=null) {
                    tiles [row][col].draw (x,y);
                } else if (tilePlay [row][col] !=null) {
                    tilePlay [row][col].draw (x,y);
                }
                x += CellSize;
            }
            y += CellSize;
        }
    }

    public void reset(){
        for (int row=0; row<BoardSize; row++){
            for (int col=0; col<BoardSize; col++){
                tiles [row][col]= null;
                tilePlay [row][col]= null;
            }
        }

    }

    //====================================================================
    /**
     * Tests the reset, place, pick, commit, and validPlay methods
     * by putting tiles on the board.
     * Doesn't draw anything.
     */

    public static void testValid(int row, int col){
        Board b = new Board();
        Tile t = new Tile("A", 1);
        System.out.println("Testing tiles in a row");
        //place tiles in a row 
        b.place(t, 2, 2);
        b.place(t, 2, 3);
        b.place(t, 2, 4);
        if (!b.validPlay()) {System.out.println("2/2, 2/3, 2/4 should be valid");}
        b.place(t, 2, 6);
        if (b.validPlay()) {System.out.println("2/2, 2/3, 2/4, 2/6 should NOT be valid");}
        b.place(t, 2, 5);
        if (!b.validPlay()) {System.out.println("2/2, .. 2/6 should be valid");}
        b.commit();

        System.out.println("Testing tiles in a disconnected row");
        b.place(t, 5, 4);
        b.place(t, 5, 5);
        b.place(t, 5, 6);
        if (b.validPlay()) {System.out.println("disconnected 5/4, 5/5/, 5/6 should NOT be valid");}
        System.out.println("Testing tiles in an L shape");
        b.place(t, 4, 6);
        b.place(t, 3, 6);
        if (b.validPlay()) {System.out.println("5/4, 5/5, 5/6, 4/6, 3/6 should NOT be valid");}
        b.pickup(5, 4);
        b.pickup(5, 5);
        if (!b.validPlay()) {System.out.println("5/6, 4/6, 3/6 should be valid");}

        System.out.println("Testing tiles in a column");
        b.reset();
        b.place(t, 2, 2);
        b.place(t, 3, 2);
        b.place(t, 4, 2);
        if (!b.validPlay()) {System.out.println("2/2, 3/2, 4/2 should be valid");}
        b.place(t, 6, 2);
        if (b.validPlay()) {System.out.println("2/2, 3/2, 4/2, 6/2 should NOT be valid");}
        b.place(t, 5, 2);
        if (!b.validPlay()) {System.out.println("2/2, .. 6/2 should be valid");}
        b.commit();

        System.out.println("Testing tiles in a disconnected column");
        b.place(t, 4, 5);
        b.place(t, 5, 5);
        b.place(t, 6, 5);
        if (b.validPlay()) {System.out.println("disconnected 4/5, 5/5/, 6/5 should NOT be valid");}
        b.place(t, 6, 4);
        b.place(t, 6, 3);
        if (b.validPlay()) {System.out.println(" 4/5, 5/5, 6/5, 6/4, 6/3 should NOT be valid");}
        b.pickup(4, 5);
        b.pickup(5, 5);
        if (!b.validPlay()) {System.out.println("6/5, 6/4, 6/3 should be valid");}

        System.out.println("Testing column connected at ends and side");
        b.reset();
        b.place(t, 10, 5);
        b.commit();
        b.place(t, 7, 5);
        b.place(t, 8, 5);
        b.place(t, 9, 5);
        if (!b.validPlay()) {System.out.println("7,8,9/5 should be valid, given 10/5");}
        b.reset();
        b.place(t, 10, 5);
        b.commit();
        b.place(t, 11, 5);
        b.place(t, 12, 5);
        b.place(t, 13, 5);
        if (!b.validPlay()) {System.out.println("11,12,13/5 should be valid, given 10/5");}
        b.reset();
        b.place(t, 10, 5);
        b.commit();
        b.place(t, 9, 6);
        b.place(t, 10, 6);
        b.place(t, 11, 6);
        if (!b.validPlay()) {System.out.println("9,10,11/6 should be valid, given 10/5");}

        System.out.println("Testing row connected at ends and side");
        b.reset();
        b.place(t, 5, 10);
        b.commit();
        b.place(t, 5, 7);
        b.place(t, 5, 8);
        b.place(t, 5, 9);
        if (!b.validPlay()) {System.out.println("5/7,8,9 should be valid, given 5/10");}
        b.reset();
        b.place(t, 5, 10);
        b.commit();
        b.place(t, 5, 11);
        b.place(t, 5, 12);
        b.place(t, 5, 13);
        if (!b.validPlay()) {System.out.println("5/11,12,13 should be valid, given 5/10");}
        b.reset();
        b.place(t, 5, 10);
        b.commit();
        b.place(t, 6, 9);
        b.place(t, 6, 10);
        b.place(t, 6, 11);
        if (!b.validPlay()) {System.out.println("6/9,10,11 should be valid, given 5/10");}

        System.out.println("Testing column spanning fixed tiles");
        b.reset();
        b.place(t, 6, 5);
        b.place(t, 9, 5);
        b.commit();
        if (b.validPlay()) {System.out.println("no working tiles should NOT be valid");}

        b.place(t, 4, 5);
        b.place(t, 5, 5);
        b.place(t, 7, 5);
        if (!b.validPlay()) {System.out.println("4,5,7/5 should be valid, given 6/5");}
        b.place(t, 10, 5);
        if (b.validPlay()) {System.out.println("4,5,7,10/5, should NOT be valid, given 6,9/5");}
        b.place(t, 8, 5);
        if (!b.validPlay()) {System.out.println("4,5,7,8,10/5, should be valid, given 6,9/5");}
        b.reset();
        b.place(t, 6, 5);
        b.commit();
        b.place(t, 3, 5);
        b.place(t, 4, 5);
        b.place(t, 7, 5);
        b.place(t, 8, 5);
        if (b.validPlay()) {System.out.println("3,4,7,8/5, should NOT be valid, given 6/5");}

        System.out.println("Testing row spanning fixed tiles");
        b.reset();
        b.place(t, 5, 6);
        b.place(t, 5, 9);
        b.commit();
        if (b.validPlay()) {System.out.println("no working tiles should NOT be valid");}

        b.place(t, 5, 4);
        b.place(t, 5, 5);
        b.place(t, 5, 7);
        if (!b.validPlay()) {System.out.println("5/4,5,7 should be valid, given 5/6");}
        b.place(t, 5, 10);
        if (b.validPlay()) {System.out.println("5/4,5,7,10, should NOT be valid, given 5/6,9");}
        b.place(t, 5, 8);
        if (!b.validPlay()) {System.out.println("5/4,5,7,8,10, should be valid, given 5/6,9");}
        b.reset();
        b.place(t, 5, 6);
        b.commit();
        b.place(t, 5, 3);
        b.place(t, 5, 4);
        b.place(t, 5, 7);
        b.place(t, 5, 8);
        if (b.validPlay()) {System.out.println("5/3,4,7,8, should NOT be valid, given 5/6");}

        System.out.println("Tests all done");
    }

    public static Tile newTile(){
        return new Tile("A", 1);
    }
}
