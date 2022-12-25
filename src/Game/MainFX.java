
package Game;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainFX {
    private Pane pane;
    private Parent root;
    private Button backToMenu;
    private Stage stage;

    private HBox naV;
    private HBox main;
    private ImageView Main;
    private ImageView showHint;
    private BufferedReader readerObrazek;
    private BufferedReader readerObtiznost;
    double souraniceX;
    double souraniceY;
    double posunutiX;
    double posunutiY;
    int width;
    int height;

    int sloupec;
    int radek;

    ActionEvent event;
    File obrazky;
    File obtiznosti;
    int x = 0;

    public MainFX() throws IOException {

        naV = new HBox();

        pane = new Pane();

        showHint = new ImageView();

        Main = new ImageView();

        readerObrazek = new BufferedReader(new FileReader("obrazky.txt"));

        readerObtiznost = new BufferedReader(new FileReader("obtiznosti.txt"));

        obrazky = new File("obrazky.txt");

        obtiznosti = new File("obtiznosti.txt");

        ZvolenaObtiznost();
        ZvolenyObrazek();
        setNaV(naV);
        //Calendar(0,0,0);

        /** Navigation Bar **/

        // Label Time

        Label stopky = new Label();
        java.util.Date date = new java.util.Date();
        stopky.setText(String.valueOf(date));

        // Image Icon
        Image IkonaObrazku = new Image(getClass().getResourceAsStream("/images/IkonaObrazku.jpg"));
        ImageView i = new ImageView(IkonaObrazku);
        i.setFitHeight(50);
        i.setFitWidth(50);

        Button Photo = new Button("", i);
        Photo.setText("");
        Photo.setStyle("-fx-background-color: white;");
        Photo.setPrefHeight(50);
        Photo.setPrefWidth(50);
        showHint.setVisible(false);

        Photo.setOnAction((ActionEvent event) -> {
            x++;
            if (x % 2 != 0) {
                showHint.setVisible(true);

            } else
                showHint.setVisible(false);
        });

        // Button Home
        Image domecek = new Image(getClass().getResourceAsStream("/images/domecek.png"));
        ImageView imageView = new ImageView(domecek);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        main = new HBox();
        main.relocate(main.getLayoutX() + 75, main.getLayoutY() + 100);
        main.getChildren().addAll(Main, showHint);

        backToMenu = new Button("", imageView);
        backToMenu.setStyle("-fx-background-color: white;");
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

        naV.getChildren().addAll(backToMenu, Photo, stopky);

        /** Main Pane **/

        pane.setStyle("-fx-background-color: black;");
        pane.getChildren().addAll(main, naV);
    }

    public void ZvolenaObtiznost() throws IOException {

        String obtiznost;

        while ((obtiznost = readerObtiznost.readLine()) != null) {

            if (obtiznost.equals("Easy")) {

                radek = 3;
                sloupec = 3;

            } else if (obtiznost.equals("Medium")) {

                radek = 5;
                sloupec = 5;


            } else if (obtiznost.equals("Hard")) {

                radek = 7;
                sloupec = 7;

            } else if (obtiznost.equals("Expert")) {

                radek = 10;
                sloupec = 10;

            }
        }

    }

    public void setNaV(HBox naV) {
        this.naV = naV;

        naV.setStyle("-fx-background-color: white;");
        naV.setLayoutX(0);
        naV.setPadding(new Insets(10, 2000, 7, 10));
        naV.setSpacing(770);

    }
    public void Calendar(int sekunda, int minuta, int hodina){

        sekunda = 0;
        minuta = 0;
        hodina = 0;

        int UbehnutyCas = 0;


        sekunda++;

        minuta = hodina % 60;

        hodina /= 60;
        System.out.println(sekunda);
        System.out.println(minuta);
        System.out.println(hodina);

    }

    public void ZvolenyObrazek() throws IOException {

        Main.setFitHeight(925);
        Main.setFitWidth(1750);

        showHint.setFitHeight(500);
        showHint.setFitWidth(700);

        String obrazek;
        Image mesto1, tygr, liska, mesto2, another;

        while ((obrazek = readerObrazek.readLine()) != null) {

            if (obrazek.equals("Mesto1")) {

                mesto1 = new Image(getClass().getResourceAsStream("/images/puzzle1.jpg"));
                Main.setImage(mesto1);
                showHint.setImage(mesto1);

            } else if (obrazek.equals("Tygr")) {

                tygr = new Image(getClass().getResourceAsStream("/images/puzzle2.jpg"));
                Main.setImage(tygr);
                showHint.setImage(tygr);

            } else if (obrazek.equals("Liska")) {

                liska = new Image(getClass().getResourceAsStream("/images/puzzle3.jpg"));
                Main.setImage(liska);
                showHint.setImage(liska);

            } else if (obrazek.equals("Mesto2")) {

                mesto2 = new Image(getClass().getResourceAsStream("/images/puzzle4.jpg"));
                Main.setImage(mesto2);
                showHint.setImage(mesto2);

            } else {

                BufferedReader Another = new BufferedReader(new FileReader("writer-another.txt"));

                obrazek = Another.readLine();

                another = new Image(getClass().getResourceAsStream("/another/Photos/" + obrazek));
                Main.setImage(another);
                showHint.setImage(another);
            }
        }
        pane.setCursor(Cursor.cursor("DEFAULT"));
        Main.setCursor(Cursor.cursor("OPEN_HAND"));
        showHint.setCursor(Cursor.cursor("OPEN_HAND"));

        Main.setOnMousePressed(IMGViewOnMousePressed);
        Main.setOnMouseDragged(IMGViewOnMouseDragged);
        showHint.setOnMousePressed(IMGViewOnMousePressed);
        showHint.setOnMouseDragged(IMGViewOnMouseDragged);
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
                    showHint.setCursor(Cursor.cursor("OPEN_HAND"));
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
                    showHint.setCursor(Cursor.cursor("CLOSED_HAND"));
                }
            };



    public Parent getRoot() {

        return pane;
    }
}

