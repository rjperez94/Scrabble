import ecs100.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

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
    private String name;
    private int value;
    private String image;
    private RuntimeDetector.TargetEnv env;
    
    public Tile (String name, int val) {
        this.env = RuntimeDetector.getEnvironment();
        this.name = name;
        this.value = val;
        this.image = "tiles/"+name+".jpg";
    }

    public Tile (File path, String name, int val) {
        this.env = RuntimeDetector.getEnvironment();
        this.name = name;
        this.value = val;
        this.image = path.getAbsolutePath() + "/" + name + ".jpg";
    }

    public void draw (double x, double y) throws IOException {
        if (env != RuntimeDetector.TargetEnv.CHEERPJ_BROWSER) {
            UI.drawImage(image, x, y);
        } else {
            ImageIO.read(Objects.requireNonNull(Tile.class.getClassLoader().getResourceAsStream("tiles/" + name + ".jpg")));
            UI.drawImage(image, x, y);
        }
    }

    public int value () {
        return value;
    }
}