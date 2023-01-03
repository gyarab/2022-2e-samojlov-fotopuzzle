package puzzlegame;

import Game.MainFX;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class PuzzleGameKontroler implements Initializable {

    @FXML
    private ImageView IMGview;

    private Scene scene;

    private Stage stage;

    @FXML
    public void Play(ActionEvent event) throws Exception {

        MainFX newScene = new MainFX();
        Parent root = newScene.getRoot();

        //Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        //System.out.println(screenBounds);

        scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("/css/MainScene.css").toExternalForm());

        scene.setOnKeyPressed(e -> {

            if (e.getCode() == KeyCode.ESCAPE) {

                stage.setFullScreen(true);

            }
        });

        stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
        stage.show();
    }
    @FXML
    public void settingsButtonClicked(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/SettingsMenu.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Image image = new Image(getClass().getResourceAsStream("/images/puzzleNight.jpg"));
        IMGview.setImage(image);

    }

}
