import java.util.*;

/**
The bag is represented by a list of tiles.
The bag should be initialised to have the standard distribution of tiles:

2 blank tiles                                      0 points
Ex12, Ax9, Ix9, Ox8, Nx6, Rx6, Tx6, Lx4, Sx4, Ux4  1 point 
Dx4, Gx3                                            2 points
Bx2, Cx2, Mx2, Px2                                    3 points
Fx2, Hx2, Vx2, Wx2, Yx2                            4 points
Kx1                                                    5 points
Jx1, Xx1                                            8 points
Qx1, Zx1                                            10 points
 */

public class Bag{
    private ArrayList <Tile> tiles = new ArrayList <Tile>();
    private Random random = new Random ();

    public Bag () {
        reset ();
    }

    public void reset () {
        tiles = new ArrayList <Tile>();
        addTiles (2, "blank", 0);
        addTiles (12, "E", 1);
        addTiles (9, "A", 1);
        addTiles (9, "I", 1);
        addTiles (8, "O", 1);
        addTiles (6, "N", 1);
        addTiles (6, "R", 1);
        addTiles (6, "T", 1);
        addTiles (4, "L", 1);
        addTiles (4, "S", 1);
        addTiles (4, "U", 1);
        addTiles (4, "D", 2);
        addTiles (3, "G", 2);
        addTiles (2, "B", 3);
        addTiles (2, "C", 3);
        addTiles (2, "M", 3);
        addTiles (2, "P", 3);
        addTiles (2, "F", 4);
        addTiles (2, "H", 4);
        addTiles (2, "V", 4);
        addTiles (2, "W", 4);
        addTiles (2, "Y", 4);
        addTiles (1, "K", 5);
        addTiles (1, "J", 8);
        addTiles (1, "X", 8);
        addTiles (1, "Q", 10);
        addTiles (1, "Z", 10);        
    }

    private void addTiles (int count, String name, int score) {
        for (int i=0; i<count; i++) {
            tiles.add(new Tile(name,score));
        }
    }

    public boolean isEmpty () {
        return tiles.isEmpty();
    }

    public Tile pick () {
        if (tiles.isEmpty()) {
            return null;
        }
        return tiles.remove(random.nextInt(tiles.size()));
    }
}
