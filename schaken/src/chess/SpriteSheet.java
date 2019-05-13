package chess;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SpriteSheet {

    private static BufferedImage chessSpriteSheet;

    public static BufferedImage grabImage(int col, int row) {
        return chessSpriteSheet.getSubimage(col * 32, row * 32, 32, 32);
    }


    public static void loadSprites() {
        try {
            chessSpriteSheet = ImageIO.read(new FileInputStream("res/spritesPieces.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
