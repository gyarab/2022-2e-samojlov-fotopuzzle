package Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.imageio.ImageIO;

public class MainFX {
    private Pane pane;
    private Parent root;
    private Button backToMenu;
    private Stage stage;
    private Timeline timeline;
    private HBox naV;
    private HBox main;
    private ImageView Main;
    private ImageView showHint;
    private BufferedReader readerObrazek;
    private BufferedReader readerObtiznost;
    private  BufferedImage photo;
    private Label stopky;
    private String obrazek;
    private String celeJmenoFotografie;
    double souraniceX;
    double souraniceY;
    double posunutiX;
    double posunutiY;
    int width;
    int height;
    private Image mesto1, tygr, liska, mesto2, another;
    int sloupec;
    int radek;
    int PocetFotek;
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

        /** Navigation Bar **/

        // Label Time

        stopky = new Label();
        Cas cas = new Cas("00:00:00");
        stopky.setText("00:00:00");
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event1 -> {

            cas.Calendar();
            stopky.setText(String.valueOf(cas));
        }
        ));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

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

        Main.setFitHeight(925);
        Main.setFitWidth(1750);

        String obtiznost;

        while ((obtiznost = readerObtiznost.readLine()) != null) {

            if (obtiznost.equals("Easy")) {

                radek = 3;
                sloupec = 3;
                PocetFotek = 9;

            } else if (obtiznost.equals("Medium")) {

                radek = 5;
                sloupec = 5;
                PocetFotek = 25;

            } else if (obtiznost.equals("Hard")) {

                radek = 7;
                sloupec = 7;
                PocetFotek = 49;

            } else if (obtiznost.equals("Expert")) {

                radek = 10;
                sloupec = 10;
                PocetFotek = 100;
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

    public void ZvolenyObrazek() throws IOException {

        showHint.setFitHeight(500);
        showHint.setFitWidth(700);

        while ((obrazek = readerObrazek.readLine()) != null) {

            if (obrazek.equals("Mesto1")) {

                mesto1 = new Image(getClass().getResourceAsStream("/images/puzzle1.jpg"));
                celeJmenoFotografie = "/images/puzzle1.jpg";
                showHint.setImage(mesto1);

            } else if (obrazek.equals("Tygr")) {

                tygr = new Image(getClass().getResourceAsStream("/images/puzzle2.jpg"));
                celeJmenoFotografie = "/images/puzzle2.jpg";
                showHint.setImage(tygr);

            } else if (obrazek.equals("Liska")) {

                liska = new Image(getClass().getResourceAsStream("/images/puzzle3.jpg"));
                celeJmenoFotografie = "/images/puzzle3.jpg";
                showHint.setImage(liska);

            } else if (obrazek.equals("Mesto2")) {

                mesto2 = new Image(getClass().getResourceAsStream("/images/puzzle4.jpg"));
                celeJmenoFotografie = "/images/puzzle4.jpg";
                showHint.setImage(mesto2);

            } else {

                BufferedReader Another = new BufferedReader(new FileReader("writer-another.txt"));

                obrazek = Another.readLine();

                another = new Image(getClass().getResourceAsStream("/another/Photos/" + obrazek));
                celeJmenoFotografie = "/another/Photos/" + obrazek;
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
    public void getPuzzlePieces() throws IOException {

        photo = ImageIO.read(getClass().getResourceAsStream(celeJmenoFotografie));

        PocetFotek = radek * sloupec;

        BufferedImage images [] = new BufferedImage[PocetFotek];

        width  = photo.getWidth() / sloupec;
        height = photo.getHeight() / radek;

        int VybranyObrazek = 0;

        for (int radky = 0; radky < radek; radky++) {

            for (int sloupce = 0; sloupce < sloupec; sloupce++) {

                images[VybranyObrazek] = (new BufferedImage(width, height, photo.getType()));

                Graphics2D grafika2D = images[VybranyObrazek++].createGraphics();
                grafika2D.drawImage(photo, 0, 0, width, height, width * sloupce, height * radky,
                        width * sloupce + width, height * radky + height, null);
                grafika2D.dispose();

            }
        }
        for (int i = 0; i < PocetFotek; i++){

            File puzzle = new File("C:\\Users\\VS\\IdeaProjects\\PuzzleGameFX\\src\\pieces\\" + "puzzle"+ (i + 1) + ".jpg");
            ImageIO.write(images[i], "jpg", puzzle);

        }
    }

    public Parent getRoot() {

        return pane;
    }
}
