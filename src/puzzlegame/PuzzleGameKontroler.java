package puzzlegame;

import Game.MainFX;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PuzzleGameKontroler implements Initializable {

    @FXML
    private ImageView IMGview;

    private Scene scene;

    private Stage stage;

    @FXML
    public void Play(ActionEvent event) throws Exception {

        BufferedReader r = new BufferedReader(new FileReader("obtiznosti.txt"));

        MainFX newScene = new MainFX();
        Parent root = newScene.getRoot();
        String radek;

        while ((radek = r.readLine()) != null) {

            if (radek.equals("Easy")) {

                scene = new Scene(root, 450, 450);
            }
            else if (radek.equals("Medium")) {

                scene = new Scene(root, 700, 700);
            }
            else if (radek.equals("Hard")) {

                scene = new Scene(root, 950, 950);
            }
            else if (radek.equals("Expert")) {

                scene = new Scene(root, 1200, 1200);
            }
        }
        stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
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
