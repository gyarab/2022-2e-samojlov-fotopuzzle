package Game;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

/**
 * @author "Vladimír Samojlov"
 * @class "2.E"
 */

/**
 * Třída sloužící k přeměně dílčího obrázku z API AWT do JavaFX
 */
public class PuzzlePiece {

    int cislo;
    Image image;

    /**
     * Konstruktor třídy PuzzlePiece
     */
    public PuzzlePiece(BufferedImage images, int x) {

        this.image = SwingFXUtils.toFXImage(images, null);
        cislo = x;
    }
}
