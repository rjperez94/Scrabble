import ecs100.*;

/**
Tile
Represents a single tile.
Needs at least the name of the image file for drawing itself
and the value of the tile.  It could store the letter on the tile also,
though this is not used in this version of the game.

Needs a
- constructor
- draw method, to draw the tile at a position x,y
- method to return the value of the tile.
 */

public class Tile {
    public static final int Size = 40;
    private int value;
    private String image;
    
    public Tile (String name, int val) {
        value = val;
        image = "tiles/"+name+".jpg";
    }

    public void draw (double x, double y) {
        UI.drawImage(image, x, y);
    }

    public int value () {
        return value;
    }
}