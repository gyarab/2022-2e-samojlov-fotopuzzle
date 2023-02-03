package Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;

public class MainFX {
    private Pane pane;
    private Parent root;
    private Button backToMenu;
    private Stage stage;
    private Timeline timeline;
    private HBox naV;
    private HBox main;
    private ImageView showHint;
    private BufferedReader readerObrazek;
    private BufferedReader readerObtiznost;
    private BufferedImage photo;
    private GridPane grid1;
    private GridPane grid2;
    private GridPane FinalGrid;
    private Label stopky;
    private String obrazek;
    private String celeJmenoFotografie;
    private HBox rozdelit;
    double souradniceX;
    double souradniceY;
    int width;
    int height;
    private Image mesto1, tygr, liska, mesto2, another;
    private ImageView Fotky;
    int sloupec;
    int radek;
    int PocetFotek;
    File obrazky;
    File obtiznosti;
    int x = 0;
    int halfGrid = 0;
    double posunutiX;
    double posunutiY;
    int Piece;
    int Gap;
    int Spacing;

    public MainFX() throws IOException {

        naV = new HBox();

        pane = new Pane();

        showHint = new ImageView();

        rozdelit = new HBox();

        readerObrazek = new BufferedReader(new FileReader("obrazky.txt"));

        readerObtiznost = new BufferedReader(new FileReader("obtiznosti.txt"));

        obrazky = new File("obrazky.txt");

        obtiznosti = new File("obtiznosti.txt");

        ZvolenaObtiznost();
        ZvolenyObrazek();
        getPuzzlePieces();

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
        //showHint.setVisible(false);

        /*Photo.setOnAction((ActionEvent event) -> {
            x++;
            if (x % 2 != 0) {
                showHint.setVisible(true);

            } else
                showHint.setVisible(false);
        });*/

        // Button Home
        Image domecek = new Image(getClass().getResourceAsStream("/images/domecek.png"));
        ImageView imageView = new ImageView(domecek);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        main = new HBox();
        main.relocate(main.getLayoutX() + 75, main.getLayoutY() + 100);
        main.getChildren().addAll(showHint);

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
                PocetFotek = 9;
                Piece = 225;
                Gap = 40;
                halfGrid = 4;
                Spacing = 800;

            } else if (obtiznost.equals("Medium")) {

                radek = 5;
                sloupec = 5;
                PocetFotek = 25;
                Piece = 140;
                Gap = 15;
                halfGrid = 12;
                Spacing = 900;

            } else if (obtiznost.equals("Hard")) {

                radek = 7;
                sloupec = 7;
                PocetFotek = 49;
                Piece = 95;
                Gap = 12;
                halfGrid = 24;
                Spacing = 960;

            } else if (obtiznost.equals("Expert")) {

                radek = 10;
                sloupec = 10;
                PocetFotek = 100;
                Piece = 70;
                Gap = 10;
                halfGrid = 50;
                Spacing = 920;
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

        //showHint.setFitHeight(500);
        //showHint.setFitWidth(700);

        while ((obrazek = readerObrazek.readLine()) != null) {

            if (obrazek.equals("Mesto1")) {

                mesto1 = new Image(getClass().getResourceAsStream("/images/puzzle1.jpg"));
                celeJmenoFotografie = "/images/puzzle1.jpg";
                //showHint.setImage(mesto1);

            } else if (obrazek.equals("Tygr")) {

                tygr = new Image(getClass().getResourceAsStream("/images/puzzle2.jpg"));
                celeJmenoFotografie = "/images/puzzle2.jpg";
                //showHint.setImage(tygr);

            } else if (obrazek.equals("Liska")) {

                liska = new Image(getClass().getResourceAsStream("/images/puzzle3.jpg"));
                celeJmenoFotografie = "/images/puzzle3.jpg";
                //showHint.setImage(liska);

            } else if (obrazek.equals("Mesto2")) {

                mesto2 = new Image(getClass().getResourceAsStream("/images/puzzle4.jpg"));
                celeJmenoFotografie = "/images/puzzle4.jpg";
                //showHint.setImage(mesto2);

            } else {

                BufferedReader Another = new BufferedReader(new FileReader("writer-another.txt"));

                obrazek = Another.readLine();

                another = new Image(getClass().getResourceAsStream("/another/Photos/" + obrazek));
                celeJmenoFotografie = "/another/Photos/" + obrazek;
                //showHint.setImage(another);
            }
        }
    }

    public void getPuzzlePieces() throws IOException {

        photo = ImageIO.read(getClass().getResourceAsStream(celeJmenoFotografie));

        PocetFotek = radek * sloupec;

        BufferedImage images[] = new BufferedImage[PocetFotek];

        width = photo.getWidth() / sloupec;
        height = photo.getHeight() / radek;

        ArrayList<Image> fotky = new ArrayList<>();

        grid1 = new GridPane();
        grid1.setHgap(Gap);
        grid1.setVgap(Gap);

        grid2 = new GridPane();
        grid2.setHgap(Gap);
        grid2.setVgap(Gap);

        FinalGrid = new GridPane();
        FinalGrid.setGridLinesVisible(true);
        FinalGrid.getStyleClass().add("grid");
        FinalGrid.setVgap(10);
        FinalGrid.setHgap(10);
        FinalGrid.setStyle("-fx-background-color: black;");
        FinalGrid.setLayoutX(615);
        FinalGrid.setLayoutY(225);

        rozdelit = new HBox(grid1, grid2);
        rozdelit.setSpacing(Spacing);
        rozdelit.setPadding(new Insets(200, 0, 0, 50));

        int VybranyObrazek = 0;

        for (int radky = 0; radky < radek; radky++) {

            for (int sloupce = 0; sloupce < sloupec; sloupce++) {

                images[VybranyObrazek] = (new BufferedImage(width, height, photo.getType()));

                Graphics2D grafika2D = images[VybranyObrazek++].createGraphics();

                int vybranaFotkaX = width * sloupce;
                int vybranaFotkaY = height * radky;

                int PuzzlePieceX = width * sloupce + width;
                int PuzzlePieceY = height * radky + height;

                grafika2D.drawImage(photo, 0, 0, width, height, vybranaFotkaX, vybranaFotkaY,
                        PuzzlePieceX, PuzzlePieceY, null);
                grafika2D.dispose();

            }
        }

        for (int i = 0; i < PocetFotek; i++) {

            Image image = SwingFXUtils.toFXImage(images[i], null);
            fotky.add(image);
            ImageView[] imageView = new ImageView[PocetFotek];
            imageView[i] = new ImageView();
            Fotky = imageView[i];
            Fotky.setImage(fotky.get(i));
            Fotky.setFitWidth(Piece);
            Fotky.setFitHeight(Piece);
            Fotky.setCursor(Cursor.cursor("OPEN_HAND"));

            setOnDragDetected(Fotky);
            setOnDragDone(Fotky);

            // Puzzle s GridPane
            int x = i / sloupec;
            int y = i % radek;

            if (i <= halfGrid) {

                grid1.add(Fotky, x, y);

            } else {

                grid2.add(Fotky, x, y);
            }

            GridPane gridPane = new GridPane();
            gridPane.setPrefSize(Piece, Piece);

            setOnDragOver(gridPane);
            setOnDragEntered(gridPane);
            setOnDragExited(gridPane);
            setOnDragDropped(gridPane);

            FinalGrid.add(gridPane, x, y);
        }
        Collections.shuffle(fotky);
        pane.getChildren().addAll(rozdelit,FinalGrid);
        pane.setCursor(Cursor.cursor("DEFAULT"));
        showHint.setCursor(Cursor.cursor("OPEN_HAND"));
        showHint.setOnMousePressed(IMGViewOnMousePressed);
        showHint.setOnMouseDragged(IMGViewOnMouseDragged);
    }

    // Tah je detekovan
    public void setOnDragDetected(ImageView vybranyObrazek) {

        vybranyObrazek.setOnDragDetected((MouseEvent event) -> {

            System.out.println("onDragDetected");

            Dragboard db = vybranyObrazek.startDragAndDrop(TransferMode.ANY);

            vybranyObrazek.setFitWidth(Piece);
            vybranyObrazek.setFitHeight(Piece);

            ClipboardContent obsahObrazku = new ClipboardContent();
            obsahObrazku.putImage(vybranyObrazek.getImage());
            db.setContent(obsahObrazku);

            event.consume();
        });
    }

    // Tah je ukoncen
    public void setOnDragDone(ImageView vybranyObrazek) {

        vybranyObrazek.setOnDragDone((DragEvent event) -> {

            System.out.println("onDragDone");

            if (event.getTransferMode() == TransferMode.MOVE) {
                vybranyObrazek.setVisible(false);
            }

            event.consume();
        });
    }

    // Tah se nachazi nad cilem objektu
    public void setOnDragOver(GridPane gridPane) {

        gridPane.setOnDragOver((DragEvent event) -> {

            System.out.println("onDragOver");

            if (event.getGestureSource() != gridPane
                    && event.getDragboard().hasImage()) {

                gridPane.setStyle("-fx-background-color: white;");
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }

            event.consume();
        });
    }

    // Tah se nachazi v cilovem objektu
    public void setOnDragEntered(GridPane gridPane) {

        gridPane.setOnDragEntered((DragEvent event) -> {

            System.out.println("onDragEntered");

            if (event.getGestureSource() != gridPane
                    && event.getDragboard().hasImage()) {
                gridPane.setStyle("-fx-background-color: #00ff00;");
            }

            event.consume();
        });
    }

    // Tah opustil cilovy objekt
    public void setOnDragExited(GridPane gridPane) {

        gridPane.setOnDragExited((DragEvent event) -> {

            gridPane.setStyle("-fx-background-color: transparent;");

            event.consume();
        });
    }

    // Tah polozil castici obrazku (puzzle) do daneho objektu
    public void setOnDragDropped(GridPane gridPane) {

        gridPane.setOnDragDropped((DragEvent event) -> {

            System.out.println("onDragDropped");

            Dragboard db = event.getDragboard();
            boolean puzzleJePolozena = false;

            if (db.hasImage()) {

                ImageView vybranyObrazek = new ImageView(db.getImage());
                gridPane.getChildren().clear();
                gridPane.getChildren().add(vybranyObrazek);
                vybranyObrazek.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {

                        vybranyObrazek.setCursor(Cursor.cursor("OPEN_HAND"));
                        setOnDragDetected(vybranyObrazek);
                        setOnDragDone(vybranyObrazek);

                        event.consume();
                    }
                });

                vybranyObrazek.setFitWidth(Piece);
                vybranyObrazek.setFitHeight(Piece);
                puzzleJePolozena = true;
            }
            event.setDropCompleted(puzzleJePolozena);

            event.consume();
        });
    }

    EventHandler<MouseEvent> IMGViewOnMousePressed =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {

                    souradniceX = t.getSceneX();
                    souradniceY = t.getSceneY();
                    posunutiX = ((ImageView) (t.getSource())).getTranslateX();
                    posunutiY = ((ImageView) (t.getSource())).getTranslateY();

                }
            };
    EventHandler<MouseEvent> IMGViewOnMouseDragged =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {

                    double NewPosunutiX = posunutiX + (t.getSceneX() - souradniceX);
                    double NewPosunutiY = posunutiY + (t.getSceneY() - souradniceY);

                    ((ImageView) (t.getSource())).setTranslateX(NewPosunutiX);
                    ((ImageView) (t.getSource())).setTranslateY(NewPosunutiY);

                }
            };

    public Parent getRoot() {

        return pane;
    }
}
