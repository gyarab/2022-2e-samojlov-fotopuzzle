package puzzlegame;

import Game.MainFX;
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
import javafx.stage.Stage;

public class PuzzleGameKontroler extends SettingsMenuKontroler implements Initializable {

    @FXML
    private ImageView IMGview;

    private Scene scene;

    private Stage stage;

    @FXML
    public void Play(ActionEvent event) throws Exception {

        MainFX newScene = new MainFX();
        Parent root = newScene.getRoot();
        Scanner vyberUroven = new Scanner(obtiznosti);

         if(obrazky.equals("Tygr"))

            scene = new Scene(root, 900, 900);
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
