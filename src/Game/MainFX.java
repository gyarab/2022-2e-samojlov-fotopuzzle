package Game;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import static javafx.geometry.Pos.CENTER;

public class MainFX {
    private Pane pane;
    private Parent root;
    private Button backToMenu;
    private Stage stage;

    private ImageView Main;
    private BufferedReader reader;
    ActionEvent event;
    File obrazky;
    File obtiznosti;

    public MainFX() throws IOException {

        pane = new Pane();

        Main = new ImageView();

        ZvolenyObrazek();

        Image domecek = new Image(getClass().getResourceAsStream("/images/domecek.png"));
        ImageView imageView = new ImageView(domecek);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        HBox h = new HBox();
        h.getChildren().add(Main);

        obrazky = new File("obrazky.txt");
        obtiznosti = new File("obtiznosti.txt");

        pane.setStyle("-fx-background-color: black;");
        backToMenu = new Button("", imageView);
        backToMenu.setStyle("-fx-background-color: black;");

        h.relocate(h.getLayoutX() + 75, h.getLayoutY() + 75);

        pane.getChildren().addAll(backToMenu, h);

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

        List<String> skenujRadky = Files.readAllLines(obtiznosti.toPath());

        for (String radek : skenujRadky) {

            if (radek.equals("Easy")) {

                Scene scene = new Scene(root, 3300, 520);

                stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

                stage.setScene(scene);
                stage.show();

            }

        }

    }

    public void ZvolenyObrazek() throws IOException {

        reader = new BufferedReader(new FileReader("obrazky.txt"));

        Main.setFitHeight(925);
        Main.setFitWidth(1750);

        String obrazek;
        Image mesto1, tygr, liska, mesto2, another;

        while ((obrazek = reader.readLine()) != null) {

            if (obrazek.equals("Mesto1")) {

                mesto1 = new Image(getClass().getResourceAsStream("/images/puzzle1.jpg"));
                Main.setImage(mesto1);

            } else if (obrazek.equals("Tygr")) {

                tygr = new Image(getClass().getResourceAsStream("/images/puzzle2.jpg"));
                Main.setImage(tygr);
            } else if (obrazek.equals("Liska")) {

                liska = new Image(getClass().getResourceAsStream("/images/puzzle3.jpg"));
                Main.setImage(liska);

            } else if (obrazek.equals("Mesto2")) {

                mesto2 = new Image(getClass().getResourceAsStream("/images/puzzle4.jpg"));
                Main.setImage(mesto2);
            }
            else{

                BufferedReader Another = new BufferedReader(new FileReader("writer-another.txt"));

                obrazek = Another.readLine();

                another = new Image(getClass().getResourceAsStream("/another/Photos/" + obrazek));
                Main.setImage(another);
            }
        }
    }

    public Parent getRoot() {

        return pane;
    }

}
