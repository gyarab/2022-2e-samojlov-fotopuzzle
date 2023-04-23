package puzzlegame;

import Game.MainFX;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private Parent root;

    @FXML
    public void Play(ActionEvent event) throws Exception {

        MainFX newScene = new MainFX();
        root = newScene.getRoot();

        scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("/css/MainScene.css").toExternalForm());

        stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
        stage.show();

        scene.setOnKeyPressed(e -> {

            if (e.getCode() == KeyCode.ESCAPE) {

                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/PuzzleMenu.fxml"));
                    stage.setScene(new Scene(root));
                    stage.show();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
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
