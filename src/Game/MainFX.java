package Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
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
    private ImageView showHint;
    private BufferedReader readerObrazek;
    private BufferedReader readerObtiznost;
    private BufferedImage photo;
    private GridPane grid1;
    private GridPane grid2;
    private GridPane FinalGrid;
    private TextField stopky;
    private String obrazek;
    private String celeJmenoFotografie;
    private HBox rozdelit;
    private TableView tableView;
    private Image mesto1, tygr, liska, mesto2, another;
    private ImageView Fotky;
    private ArrayList<Integer> plocha;
    private ArrayList<PuzzlePiece> fotky;
    private Pane node;
    private String id;
    int width;
    int height;
    int sloupec;
    int radek;
    int PocetFotek;
    File obrazky;
    File obtiznosti;
    int pocet = 0;
    int halfGrid = 0;
    int Piece;
    int Gap;
    int Spacing;
    Button Photo;
    String nazevObrazku;
    String LevelFX;
    Cas cas;
    int showHintUsed = 0;

    public MainFX() throws IOException {

        naV = new HBox();

        pane = new Pane();

        showHint = new ImageView();

        plocha = new ArrayList<Integer>();

        rozdelit = new HBox();

        readerObrazek = new BufferedReader(new FileReader("obrazky.txt"));

        readerObtiznost = new BufferedReader(new FileReader("obtiznosti.txt"));

        obrazky = new File("obrazky.txt");

        obtiznosti = new File("obtiznosti.txt");

        tableView = new TableView<>();

        ZvolenaObtiznost();
        ZvolenyObrazek();
        getPuzzlePieces();
        setNaV(naV);

        /** Navigation Bar **/

        // Label Time
        stopky = new TextField();
        stopky.setEditable(false);
        stopky.setMouseTransparent(true);
        stopky.setFocusTraversable(false);
        cas = new Cas("00:00:00");
        stopky.setText("00:00:00");
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event1 -> {

            cas.Calendar();
            stopky.setText(String.valueOf(cas));
        }
        ));
        timeline.setCycleCount(Timeline.INDEFINITE);

        // Image Icon
        Image IkonaObrazku = new Image(getClass().getResourceAsStream("/images/IkonaObrazku.jpg"));
        ImageView i = new ImageView(IkonaObrazku);
        i.setFitHeight(50);
        i.setFitWidth(50);

        Photo = new Button("", i);
        Photo.setText("");
        Photo.setStyle("-fx-background-color: white;");
        Photo.setPrefHeight(75);
        Photo.setPrefWidth(75);

        Photo.setOnAction((ActionEvent event) -> {

            showHintUsed++;
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

                LevelFX = "Easy";
                radek = 3;
                sloupec = 3;
                PocetFotek = 9;
                Piece = 225;
                Gap = 40;
                halfGrid = 4;
                Spacing = 800;

            } else if (obtiznost.equals("Medium")) {

                LevelFX = "Medium";
                radek = 5;
                sloupec = 5;
                PocetFotek = 25;
                Piece = 140;
                Gap = 15;
                halfGrid = 12;
                Spacing = 900;

            } else if (obtiznost.equals("Hard")) {

                LevelFX = "Hard";
                radek = 7;
                sloupec = 7;
                PocetFotek = 49;
                Piece = 95;
                Gap = 12;
                halfGrid = 24;
                Spacing = 960;

            } else if (obtiznost.equals("Expert")) {

                LevelFX = "Expert";
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
        naV.setPadding(new Insets(10, 10, 0, 10));
        naV.setSpacing(770);
    }

    public void ZvolenyObrazek() throws IOException {

        while ((obrazek = readerObrazek.readLine()) != null) {

            if (obrazek.equals("Mesto1")) {

                nazevObrazku = "Město č.1";
                mesto1 = new Image(getClass().getResourceAsStream("/images/puzzle1.jpg"));
                celeJmenoFotografie = "/images/puzzle1.jpg";
                showHint.setImage(mesto1);

            } else if (obrazek.equals("Tygr")) {

                nazevObrazku = "Tygr";
                tygr = new Image(getClass().getResourceAsStream("/images/puzzle2.jpg"));
                celeJmenoFotografie = "/images/puzzle2.jpg";
                showHint.setImage(tygr);

            } else if (obrazek.equals("Liska")) {

                nazevObrazku = "Liška";
                liska = new Image(getClass().getResourceAsStream("/images/puzzle3.jpg"));
                celeJmenoFotografie = "/images/puzzle3.jpg";
                showHint.setImage(liska);

            } else if (obrazek.equals("Mesto2")) {

                nazevObrazku = "Město č.2";
                mesto2 = new Image(getClass().getResourceAsStream("/images/puzzle4.jpg"));
                celeJmenoFotografie = "/images/puzzle4.jpg";
                showHint.setImage(mesto2);

            } else {

                BufferedReader Another = new BufferedReader(new FileReader("writer-another.txt"));

                obrazek = Another.readLine();

                another = new Image(getClass().getResourceAsStream("/another/Photos/" + obrazek));
                celeJmenoFotografie = "/another/Photos/" + obrazek;
                showHint.setImage(another);

                if (obrazek.length() <= 9) {

                    nazevObrazku = "Vlastní (" + obrazek + ")";
                } else {
                    nazevObrazku = "Vlastní";
                }
            }
        }
    }

    public void getPuzzlePieces() throws IOException {

        photo = ImageIO.read(getClass().getResourceAsStream(celeJmenoFotografie));

        PocetFotek = radek * sloupec;

        BufferedImage images[] = new BufferedImage[PocetFotek];

        width = photo.getWidth() / sloupec;
        height = photo.getHeight() / radek;

        fotky = new ArrayList<>();

        grid1 = new GridPane();
        grid1.setHgap(Gap);
        grid1.setVgap(Gap);

        grid2 = new GridPane();
        grid2.setHgap(Gap);
        grid2.setVgap(Gap);

        FinalGrid = new GridPane();
        FinalGrid.setGridLinesVisible(true);
        FinalGrid.getStyleClass().add("grid");
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

            PuzzlePiece piece = new PuzzlePiece(images[i], i);
            fotky.add(piece);
        }
        Collections.shuffle(fotky, new Random());

        for (int m = 0; m < PocetFotek; m++) {

            ImageView[] imageView = new ImageView[PocetFotek];
            imageView[m] = new ImageView();
            Fotky = imageView[m];
            Fotky.setImage(fotky.get(m).image);
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

            // Drop place
            node = new Pane();
            node.setPrefSize(Piece, Piece);

            setOnDragOver(node);
            setOnDragEntered(node);
            setOnDragExited(node);
            setOnDragDropped(node);

            FinalGrid.add(node, y, x);
        }
        pane.getChildren().addAll(rozdelit, FinalGrid);
        pane.setCursor(Cursor.cursor("DEFAULT"));
    }

    public TableView Vysledky() {

        TableColumn<Vysledek, String> jmenoObrazku = new TableColumn<>("Obrázek \uD83D\uDDBC");
        TableColumn<Vysledek, String> level = new TableColumn<>("Level \uD83D\uDCC8");
        TableColumn<Vysledek, String> cas = new TableColumn<>("Čas ⏰");
        TableColumn<Vysledek, Integer> napovedaUsed = new TableColumn<>("Použití nápovědy");
        TableColumn<Vysledek, Integer> skore = new TableColumn<>("Skóre \uD83C\uDFC6");

        jmenoObrazku.setCellValueFactory(
                new PropertyValueFactory<>("JmenoObrazku"));

        level.setCellValueFactory(
                new PropertyValueFactory<>("level"));

        cas.setCellValueFactory(
                new PropertyValueFactory<>("cas"));

        napovedaUsed.setCellValueFactory(
                new PropertyValueFactory<>("napovedaUsed"));

        skore.setCellValueFactory(
                new PropertyValueFactory<>("skore"));

        TableColumn[] arr = {jmenoObrazku, level, cas, napovedaUsed, skore};

        level.setStyle("-fx-text-fill: linear-gradient(to right, #33CC33 0%, #FFFF00 33%, #FFA500 66%, #F84B00 100%);");
        cas.setStyle("-fx-text-fill: linear-gradient(to right, #66b3ff 0%, #cce6ff 100%);");

        if (tableView.getItems().isEmpty() == true) {

            tableView.getItems().add(new Vysledek(nazevObrazku, LevelFX, getCas().toString(), showHintUsed, 4));
        }
        tableView.setEditable(false);
        tableView.setSelectionModel(null);
        tableView.getColumns().addAll(arr);
        jmenoObrazku.setResizable(false);
        level.setResizable(false);
        cas.setResizable(false);
        napovedaUsed.setResizable(false);
        skore.setResizable(false);

        for (TableColumn x : arr) {

            mezera(x, tableView, 0.2);
            getStyleClass(x);
        }

        tableView.setPrefWidth(1250);
        tableView.setLayoutX(250);
        tableView.setLayoutY(100);
        tableView.getStyleClass().add("table-view");
        tableView.setFixedCellSize(60);

        pane.getChildren().add(tableView);

        return tableView;
    }

    public void Data() throws IOException {

        File soubor = new File("data.dat");

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                try {
                    PrintWriter writer = new PrintWriter(soubor);
                    writer.print("");
                    writer.close();
                } catch (FileNotFoundException e) {
                    System.out.println("Chyba");
                }
            }
        }, "Shutdown-thread"));

        try {

            if (soubor.createNewFile()) {
                System.out.println("Soubor je vytvoren: " + soubor.getName());
            } else {
                System.out.println("Soubor jiz existuje");
            }

            FileInputStream input = new FileInputStream(soubor);

            ObjectInputStream i = new ObjectInputStream(input);

            List<Vysledek> dataTabulky;

            Object tabulka = i.readObject();

            dataTabulky = (ArrayList<Vysledek>) tabulka;

            dataTabulky.add(new Vysledek(nazevObrazku, LevelFX, getCas().toString(), showHintUsed, 4));

            for (Vysledek data : dataTabulky) {

                System.out.println(data);
                tableView.getItems().add(data);
            }

            i.close();

            FileOutputStream output = new FileOutputStream(soubor);
            ObjectOutputStream o = new ObjectOutputStream(output);

            o.writeObject(dataTabulky);
            o.writeObject("\n");

            o.close();

        } catch (EOFException | ClassNotFoundException e) {

            List<Vysledek> dataTabulky = new ArrayList<>();
            dataTabulky.add(new Vysledek(nazevObrazku, LevelFX, getCas().toString(), showHintUsed, 4));

            for (Vysledek data : dataTabulky) {

                System.out.println(data);
            }

            FileOutputStream output = new FileOutputStream(soubor);
            ObjectOutputStream o = new ObjectOutputStream(output);
            o.writeObject(dataTabulky);

            o.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mezera(TableColumn tableColumn, TableView tableView, double delka) {

        tableColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(delka));
    }

    public void getStyleClass(TableColumn tableColumn) {

        tableColumn.getStyleClass().add("table-column");
    }

    public Cas getCas() {

        return cas;
    }

    // Tah je detekovan
    public void setOnDragDetected(ImageView vybranyObrazek) {

        vybranyObrazek.setOnDragDetected((MouseEvent event) -> {

            Dragboard db = vybranyObrazek.startDragAndDrop(TransferMode.ANY);

            int x = (int) (event.getSceneX());
            int y = (int) (event.getSceneY());
            int cisloX = x / 280;
            int cisloY = y / 432;

            if (y > 695) {

                cisloY = 2;
            }

            if (cisloX != 0) {

                cisloX += radek - 1;
            }

            if (x >= 1370) {

                cisloX -= radek;

            }
            if (x < 1610 && x >= 1370) {

                cisloX = radek;
            }
            if (cisloX == halfGrid) {

                cisloY += radek - 1;
            }
            if (x > 1675) {

                cisloX = halfGrid;
                cisloY += radek - 1;
            }

            //System.out.println("Osa X: " + x + " OsaY " + y);

            int pozice = cisloX + cisloY;
            //System.out.println("CisloX " + cisloX + " CisloY " + cisloY);

            String slovo = String.valueOf(fotky.get(pozice).cislo);
            vybranyObrazek.setId(slovo);
            id = vybranyObrazek.getId();
            //System.out.println("Pozice " + cisloY + " ID: " + id);

            timeline.play();

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
    public void setOnDragOver(Pane dragboard) {

        dragboard.setOnDragOver((DragEvent event) -> {

            if (event.getGestureSource() != dragboard
                    && event.getDragboard().hasImage()) {

                dragboard.setStyle("-fx-background-color: white;");
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }

            event.consume();
        });
    }

    // Tah se nachazi v cilovem objektu
    public void setOnDragEntered(Pane dragboard) {

        dragboard.setOnDragEntered((DragEvent event) -> {

            if (event.getGestureSource() != dragboard
                    && event.getDragboard().hasImage()) {
                dragboard.setStyle("-fx-background-color: #00ff00;");
            }

            event.consume();
        });
    }

    // Tah opustil cilovy objekt
    public void setOnDragExited(Pane dragboard) {

        dragboard.setOnDragExited((DragEvent event) -> {

            dragboard.setStyle("-fx-background-color: transparent;");

            event.consume();
        });
    }

    // Tah polozil castici obrazku (puzzle) do daneho objektu

    public void setOnDragDropped(Pane dragboard) {

        dragboard.setOnDragDropped((DragEvent event) -> {

            Dragboard db = event.getDragboard();
            boolean puzzleJePolozena = false;
            int x = (int) (event.getSceneX() - FinalGrid.getLayoutX());
            int y = (int) (event.getSceneY() - FinalGrid.getLayoutY());
            int odchylka = 232;
            int cisloX = x / odchylka;
            int cisloY = y / odchylka;

            if (cisloY > 0) {

                if (cisloY == 1) {

                    cisloX += radek - 1;

                } else {

                    cisloX += radek + 1;
                }
            }

            int lokace = cisloX + cisloY;

            if (db.hasImage()) {

                ImageView vybranyObrazek = new ImageView(db.getImage());
                vybranyObrazek.setFitWidth(Piece);
                vybranyObrazek.setFitHeight(Piece);
                System.out.println(" ID: " + id);
                vybranyObrazek.setCursor(Cursor.cursor("OPEN_HAND"));
                setOnDragDetected(vybranyObrazek);
                setOnDragDone(vybranyObrazek);
                dragboard.getChildren().add(vybranyObrazek);

                boolean vysledek = false;

                if (!plocha.contains(lokace)) {

                    plocha.add(lokace);

                    pocet++;

                    System.out.println(id + " = " + lokace);

                    if (Integer.parseInt(id) == lokace) {

                        vysledek = true;
                    }
                }
                if (PocetFotek == pocet) {

                Label kontrola = new Label();
                kontrola.setPrefSize(308, 104);
                kontrola.setAlignment(Pos.CENTER);
                kontrola.setLayoutX(750);
                kontrola.setLayoutY(100);
                pane.getChildren().add(kontrola);

                if (vysledek == true) {

                    FinalGrid.setStyle("-fx-border-color:" + ColorWIN() + "-fx-border-width: 10");

                    kontrola.setText("You WON!");
                    kontrola.setStyle("-fx-text-fill: " + ColorWIN() + Vzhled());

                    Button showResults = new Button("Show Results");
                    showResults.setPrefSize(208, 52);
                    showResults.setAlignment(Pos.CENTER);
                    showResults.setLayoutX(875);
                    showResults.setLayoutY(1000);

                    pane.getChildren().add(showResults);
                    showResults.setOnAction((ActionEvent e) -> {

                        try {
                            pane.getChildren().removeAll(rozdelit, FinalGrid);
                            Data();
                            Vysledky();
                            showResults.setDisable(true);

                        } catch (IOException exception) {
                            throw new RuntimeException(exception);
                        }
                    });
                } else {

                    FinalGrid.setStyle("-fx-border-color: " + ColorLOSE() + "-fx-border-width: 10");

                    kontrola.setText("You LOSE!");
                    kontrola.setStyle("-fx-text-fill: " + ColorLOSE() + Vzhled());
                }
                FinalGrid.setDisable(true);
                timeline.stop();
                Photo.setDisable(true);
                }

                event.consume();

                puzzleJePolozena = true;
            }
            event.setDropCompleted(puzzleJePolozena);

            event.consume();
        });
    }
    public String Vzhled(){

       return "-fx-font-size: 60;-fx-background-color: black;-fx-font-weight: bold;" +
                "-fx-font-style: italic;";
    }
    public String ColorWIN(){

        return "linear-gradient(to right, rgba(34,193,195,1) 0%, rgba(253,187,45,1) 100%);";
    }
    public String ColorLOSE(){

        return "linear-gradient(to right, rgba(255,0,0,1) 0%, rgba(255,231,0,1) 100%);";
    }

    public Parent getRoot() {

        return pane;
    }
}
