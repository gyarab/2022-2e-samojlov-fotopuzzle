package Game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainFX {

    private VBox vbox;
    private Parent root;
    private Button backToMenu;
    private Stage stage;
    ActionEvent event;
    File obrazky;
    File obtiznosti;

    public MainFX() throws FileNotFoundException {
        
        FileInputStream input = new FileInputStream("C:\\Users\\VS\\IdeaProjects\\PuzzleGameFX\\src\\images\\domecek.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(70);
        imageView.setFitWidth(70);
        
        obrazky = new File("obrazky.txt");
        obtiznosti = new File("obtiznosti.txt");

        vbox = new VBox();
        vbox.setStyle("-fx-background-color: black;");
        backToMenu = new Button("",imageView);
        backToMenu.setStyle("-fx-background-color: black;");

        vbox.getChildren().add(backToMenu);
        vbox.setAlignment(Pos.TOP_LEFT);
        
        backToMenu.setOnAction((ActionEvent event) -> {

            try {
                root = FXMLLoader.load(getClass().getResource("/fxml/PuzzleMenu.fxml"));
                Scene scene = new Scene(root);

                stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(MainFX.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    public void ZvolenaObtiznost() throws IOException {

        List< String> skenujRadky = Files.readAllLines(obtiznosti.toPath());

        for (String radek : skenujRadky) {
            
            if(radek.equals("Easy")){
                
                Scene scene = new Scene(root, 3300, 520);

                stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

                stage.setScene(scene);
                stage.show();
                
            }

        }

    }
    public void ZvolenyObrazek(){


    }
    

    public Parent getRoot() {
        
        return vbox;
    }
    public Scene getSceneX() {
        
        return new Scene(root, 900, 900);
    }

}
