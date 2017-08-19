import ecs100.*;
import java.awt.Color;

/**    
The rack is represented by an array of tiles.
The rack should be displayed with a rectangular border and each tile displayed
by drawing the image of the tile at that place. Empty spaces in the rack 
should be represented by nulls and displayed as empty.
The user can select a position on the rack using the mouse. The selected tile
(or empty space) should be highlighted with a border around it.

Suggested methods:
- constructor
- boolean on(double x, double y) : is the point x,y on the rack
- int index(double x, double y) : returns the index of the cell at the point x,y on the rack
- Tile pickup(int pos) : pick up the tile at the index pos (null if no tile)
- boolean place(Tile tile, int pos) : place tile at the index pos on the rack
pushing tiles to the side if necessary. (return true if successful, and false if rack full)
- void fill(Bag bag) :  fill all the space on the rack from the bag
- void draw() :  draw the rack
- void reset() : reset the rack to initial empty state.  
 */

public class Rack{
    // Fields
    private static final int RackSize = 7;
    private Tile [] tiles = new Tile [RackSize];

    private static final int CellSize = Tile.Size;
    private static final int Left = 200;
    public static final double Top = 650;
    public static final int Right = Left + CellSize * RackSize;         

    public Rack () {
    }

    public boolean on(double x, double y) {
        return (y>=Top && y < Top+CellSize  && x>=Left && x < Left+RackSize* CellSize);
    }

    public int index (double x, double y) {
        return (int) ((x-Left)/CellSize);
    }

    public Tile pickup(int pos){
        if ((pos<0 || pos>RackSize) || (tiles[pos] ==null)){
            return null;
        }
        Tile t = tiles[pos];
        tiles[pos]=null;
        return t;
    }

    public boolean place(Tile tile, int pos){
        if (pos<0 || pos>RackSize) {return false;}
        if (tiles[pos] ==null) {
            tiles[pos] = tile;
            return true;
        }
        for (int p=pos-1; p>=0; pos--) {
            if (tiles[p]==null) {
                for (int i = p; i<pos;i++) {
                    tiles [i] = tiles[i+1];
                }
                tiles[pos] = tile;
                return true;
            }
        }
        for (int p=pos+1; p<RackSize; pos++) {
            if (tiles[p]==null) {
                for (int i = p; i>pos;i--) {
                    tiles [i] = tiles[i-1];
                }
                tiles[pos] = tile;
                return true;
            }
        }
        return false;
    }

    public void fill(Bag bag) {
        for (int pos = 0; pos<RackSize; pos++) {
            if (tiles[pos]==null) {
                tiles[pos]=bag.pick();
            }
        }
    }

    public void draw() {
        UI.setColor(Color.black);
        int left = Left;
        for(int pos = 0; pos<RackSize; pos++){
            if (tiles[pos] !=null) {
                tiles[pos].draw(left,Top);
            }
            UI.drawRect(left, Top, CellSize, CellSize);
            left += CellSize;
        }
    }

    public void reset() {
        for (int i=0; i<RackSize; i++) {
            tiles [i] = null;
        }
    }
}
