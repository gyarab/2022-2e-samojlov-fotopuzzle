package puzzlegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author "Vladimír Samojlov"
 * @class "2.E"
 */

/**
 * Spuštění celé aplikace JavaFX
 */
public class PuzzleGame extends Application {

    /**
     * Hlavní metoda pro spuštění celé aplikace
     */
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/PuzzleMenu.fxml"));
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("/images/PuzzleLogo.png"));
        stage.setTitle("Puzzle");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
