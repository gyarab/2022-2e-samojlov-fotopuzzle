package Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import puzzlegame.PuzzleGameKontroler;

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
    private Ellipse ceiling;
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

        Photo.setOnAction((ActionEvent event) -> {

            Stage newWindow = new Stage();
            newWindow.setTitle("Helper Image");
            newWindow.getIcons().add(new Image("/images/PuzzleLogo.png"));
            newWindow.setResizable(false);
            showHint.setFitHeight(450);
            showHint.setFitWidth(550);
            Button button = new Button("OK");
            button.setStyle("-fx-background-color: black;-fx-text-fill: white;");
            button.setOnAction(e -> {
                newWindow.close();
            });

            VBox container = new VBox(showHint, button);

            container.setSpacing(15);
            container.setPadding(new Insets(25));
            container.setAlignment(Pos.CENTER);
            container.setStyle("-fx-background-color: linear-gradient(to top, #2c216b, #0070f8, #f58d00, #ffc200, #ebe112);");

            newWindow.setScene(new Scene(container));
            newWindow.show();
        });

        // Button Home
        Image domecek = new Image(getClass().getResourceAsStream("/images/domecek.png"));
        ImageView imageView = new ImageView(domecek);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

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
        pane.getChildren().add(naV);
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
        }

        Collections.shuffle(fotky, new Random());

        for (int m = 0; m < PocetFotek; m++) {

            //Shape clip = createPiece();
            //clip.setFill(Color.WHITE);
            //clip.setStroke(null);

            ImageView[] imageView = new ImageView[PocetFotek];
            imageView[m] = new ImageView();
            Fotky = imageView[m];
            Fotky.setImage(fotky.get(m));
            //Fotky.setClip(clip);
            Fotky.setFitWidth(Piece);
            Fotky.setFitHeight(Piece);
            Fotky.setCursor(Cursor.cursor("OPEN_HAND"));

            setOnDragDetected(Fotky);
            setOnDragDone(Fotky);

            // Puzzle s GridPane
            int x = m / sloupec;
            int y = m % radek;

            if (m <= halfGrid) {

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
        pane.getChildren().addAll(rozdelit, FinalGrid);
        pane.setCursor(Cursor.cursor("DEFAULT"));
    }


    // Tah je detekovan
    public void setOnDragDetected(ImageView vybranyObrazek) {

        vybranyObrazek.setOnDragDetected((MouseEvent event) -> {

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

            if (event.getTransferMode() == TransferMode.MOVE) {
                vybranyObrazek.setVisible(false);
            }

            event.consume();
        });
    }

    // Tah se nachazi nad cilem objektu
    public void setOnDragOver(GridPane gridPane) {

        gridPane.setOnDragOver((DragEvent event) -> {

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

            Dragboard db = event.getDragboard();
            boolean puzzleJePolozena = false;

            if (db.hasImage()) {

                ImageView vybranyObrazek = new ImageView(db.getImage());
                vybranyObrazek.setId(String.valueOf(System.currentTimeMillis()));
                vybranyObrazek.setFitWidth(Piece);
                vybranyObrazek.setFitHeight(Piece);
                gridPane.getChildren().clear();
                gridPane.getChildren().add(vybranyObrazek);
                vybranyObrazek.setCursor(Cursor.cursor("OPEN_HAND"));
                setOnDragDetected(vybranyObrazek);
                setOnDragDone(vybranyObrazek);

                event.consume();

                puzzleJePolozena = true;
            }
            event.setDropCompleted(puzzleJePolozena);

            event.consume();
        });
    }

    public Parent getRoot() {

        return pane;
    }
}
