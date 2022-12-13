package Game;

import java.awt.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainFX {
    private Pane pane;
    private Parent root;
    private Button backToMenu;
    private Stage stage;

    private ImageView Main;
    private BufferedReader readerObrazek;

    private BufferedReader readerObtiznost;

    private int VELIKOST;

    double souraniceX;
    double souraniceY;
    double posunutiX;
    double posunutiY;


    ActionEvent event;
    File obrazky;
    File obtiznosti;
    boolean Dragged;

    public MainFX() throws IOException {

        pane = new Pane();

        Main = new ImageView();

        readerObrazek = new BufferedReader(new FileReader("obrazky.txt"));

        readerObtiznost = new BufferedReader(new FileReader("obtiznosti.txt"));

        obrazky = new File("obrazky.txt");

        obtiznosti = new File("obtiznosti.txt");

        ZvolenaObtiznost();
        ZvolenyObrazek();

        Image domecek = new Image(getClass().getResourceAsStream("/images/domecek.png"));
        ImageView imageView = new ImageView(domecek);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        HBox h = new HBox();
        h.relocate(h.getLayoutX() + 75, h.getLayoutY() + 75);
        h.getChildren().add(Main);

        backToMenu = new Button("", imageView);
        backToMenu.setStyle("-fx-background-color: black;");

        pane.setStyle("-fx-background-color: black;");
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

        //ArrayList <String> obtiznosti = new ArrayList();
        //Collections.addAll(obtiznosti, "Easy", "Medium", "Hard", "Expert");

        String obtiznost;

        while ((obtiznost = readerObtiznost.readLine()) != null) {

            if (obtiznost.equals("Easy")) {


            } else if (obtiznost.equals("Medium")) {


            } else if (obtiznost.equals("Hard")) {


            } else if (obtiznost.equals("Expert")) {

            }
        }

    }

    public void ZvolenyObrazek() throws IOException {

        Main.setFitHeight(925);
        Main.setFitWidth(1750);

        String obrazek;
        Image mesto1, tygr, liska, mesto2, another;

        while ((obrazek = readerObrazek.readLine()) != null) {

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
            } else {

                BufferedReader Another = new BufferedReader(new FileReader("writer-another.txt"));

                obrazek = Another.readLine();

                another = new Image(getClass().getResourceAsStream("/another/Photos/" + obrazek));
                Main.setImage(another);
            }
        }
        pane.setCursor(Cursor.cursor("DEFAULT"));
        Main.setCursor(Cursor.cursor("OPEN_HAND"));
        Main.setOnMousePressed(IMGViewOnMousePressed);
        Main.setOnMouseDragged(IMGViewOnMouseDragged);
    }

    EventHandler<MouseEvent> IMGViewOnMousePressed =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {

                    souraniceX = t.getSceneX();
                    souraniceY = t.getSceneY();
                    posunutiX = ((ImageView) (t.getSource())).getTranslateX();
                    posunutiY = ((ImageView) (t.getSource())).getTranslateY();

                    Main.setCursor(Cursor.cursor("OPEN_HAND"));
                }
            };
    EventHandler<MouseEvent> IMGViewOnMouseDragged =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {

                    double NewPosunutiX = posunutiX + (t.getSceneX() - souraniceX);
                    double NewPosunutiY = posunutiY + (t.getSceneY() - souraniceY);

                    ((ImageView) (t.getSource())).setTranslateX(NewPosunutiX);
                    ((ImageView) (t.getSource())).setTranslateY(NewPosunutiY);

                    Main.setCursor(Cursor.cursor("CLOSED_HAND"));
                }
            };

    public Parent getRoot() {

        return pane;
    }
}



