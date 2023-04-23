package Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
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
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.imageio.ImageIO;

public class MainFX {
    private Button napoveda;
    private Pane pane;
    private Parent root;
    private Parent newRoot;
    private Button backToMenu;
    private Stage stage;
    private Stage newStage;
    private Timeline timeline;
    private HBox naV;
    private ImageView showHint;
    private BufferedReader readerObrazek;
    private BufferedReader readerObtiznost;
    private BufferedImage fotografie;
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
    private ArrayList<PuzzlePiece> fotky;
    private Pane node;
    private String id;
    int sirka;
    int vyska;
    int sloupec;
    int radek;
    int newId;
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
    private HashMap<Integer, Integer> poziceObrazku;
    int lokace;
    int number;
    int nasobek;
    int lokaceX;
    int lokaceY;
    int pozice;
    int skoreFX;
    int pocitadlo = 0;
    private int iD;

    public MainFX() throws IOException {

        naV = new HBox();

        pane = new Pane();

        showHint = new ImageView();

        poziceObrazku = new HashMap<>();

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
        cas = new Cas("00:01:00");
        stopky.setText("00:01:00");
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

            showHint.setFitHeight(450);
            showHint.setFitWidth(550);
            showHint.setLayoutX(675);
            showHint.setLayoutY(330);

            pocet++;

            if (pocet % 2 == 0) {

                pane.getChildren().remove(showHint);
                napoveda.setDisable(false);

            } else {
                pane.getChildren().add(showHint);
                napoveda.setDisable(true);
            }

        });

        // Button Home
        Image domecek = new Image(getClass().getResourceAsStream("/images/domecek.png"));
        ImageView imageView = new ImageView(domecek);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        // Odstin
        setShadowStyle(imageView);
        setShadowStyle(i);

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

        // Napoveda
        ImageView help = new ImageView(new Image(getClass().getResourceAsStream("/images/help-black.jpg")));
        help.setFitHeight(85);
        help.setFitWidth(85);

        napoveda = new Button("", help);
        napoveda.setStyle("-fx-background-color: black;");
        napoveda.setFocusTraversable(false);
        napoveda.setLayoutX(890);
        napoveda.setLayoutY(970);

        // Pravidla
        Label nadpis = new Label();
        nadpis.setAlignment(Pos.TOP_CENTER);
        nadpis.setStyle("-fx-padding: 5 3 3 10;-fx-background-radius: 15 15 0 0;-fx-font-size: 50; " +
                "-fx-font-weight: bold;-fx-font-style: italic;-fx-text-fill: black;" +
                "-fx-background-color: radial-gradient(center 50% 50%, radius 50%, #ff945d 0%, #F7FF00 100%);");

        Label pravidla = new Label();
        pravidla.setAlignment(Pos.TOP_LEFT);
        pravidla.setPrefWidth(650);
        pravidla.setPrefHeight(500);
        pravidla.setStyle("-fx-background-color: white;-fx-text-fill: black;-fx-font-size: 20;" +
                "-fx-padding: 60 10 10 50;-fx-background-radius: 45;-fx-line-spacing: 15;" +
                "-fx-background-color: radial-gradient(center 50% 50%, radius 50%, #F7FF00FF 0%, #FF945DFF 100%);");

        ImageView rightClick = new ImageView(new Image(getClass().getResourceAsStream("/images/right-click.png")));
        rightClick.setFitHeight(75);
        rightClick.setFitWidth(75);
        rightClick.setLayoutX(900);
        rightClick.setLayoutY(750);

        VBox zaklad = new VBox(nadpis, pravidla);
        zaklad.setLayoutX(627);
        zaklad.setLayoutY(375);
        zaklad.setMouseTransparent(true);

        napoveda.setOnMouseEntered(e1 -> {

            pocitadlo++;

            if (pocitadlo % 2 == 0) {

                help.setImage(new Image(getClass().getResourceAsStream("/images/help-black.jpg")));

            } else {
                help.setImage(new Image(getClass().getResourceAsStream("/images/help-white.jpg")));
                napoveda.setCursor(Cursor.HAND);
                zaklad.setAlignment(Pos.CENTER);
                nadpis.setText("How to play?");
                pravidla.setText("– Drag and drop the puzzles on the game board" + "\n"
                        + "– Use the right mouse button to move the puzzle in the board" + "\n" +
                        "– During the game you can use a hint" + "\n" +
                        "– Correct solutions are awarded a total score \uD83C\uDFC6");


                if (!(pane.getChildren().contains(zaklad))) {

                    pane.getChildren().addAll(zaklad, rightClick);
                }
            }
        });
        napoveda.setOnMouseExited(e1 -> {

            pocitadlo++;

            if (pocitadlo % 2 == 0) {

                help.setImage(new Image(getClass().getResourceAsStream("/images/help-black.jpg")));
                pane.getChildren().removeAll(zaklad, rightClick);

            } else {
                help.setImage(new Image(getClass().getResourceAsStream("/images/help-white.jpg")));

            }
        });

        pane.getChildren().addAll(napoveda);

        /** Main Pane **/
        naV.getChildren().addAll(backToMenu, Photo, stopky);

        pane.setStyle("-fx-background-color: black;");
        pane.getChildren().add(naV);
    }

    public void setShadowStyle(ImageView style) {

        style.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 30, 0, 0, 0);");
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
                number = 1;

            } else if (obtiznost.equals("Medium")) {

                LevelFX = "Medium";
                radek = 5;
                sloupec = 5;
                PocetFotek = 25;
                Piece = 140;
                Gap = 15;
                halfGrid = 12;
                Spacing = 900;
                number = 3;
                nasobek = 4;

            } else if (obtiznost.equals("Hard")) {

                LevelFX = "Hard";
                radek = 7;
                sloupec = 7;
                PocetFotek = 49;
                Piece = 95;
                Gap = 12;
                halfGrid = 24;
                Spacing = 960;
                number = 5;
                nasobek = 6;

            } else if (obtiznost.equals("Expert")) {

                LevelFX = "Expert";
                radek = 10;
                sloupec = 10;
                PocetFotek = 100;
                Piece = 70;
                Gap = 10;
                halfGrid = 50;
                Spacing = 920;
                number = 8;
                nasobek = 9;
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

                BufferedReader reader = new BufferedReader(new FileReader("writer-another.txt"));

                obrazek = reader.readLine();

                celeJmenoFotografie = "C:\\Users\\VS\\IdeaProjects\\PuzzleGameFX\\src\\another\\" + obrazek;
                another = new Image(new FileInputStream(celeJmenoFotografie));
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

        PocetFotek = radek * sloupec;

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

        // Počet fotek dle obtížnosti
        BufferedImage images[] = new BufferedImage[PocetFotek];

        // Počáteční fotografie vybraná uživatelem
        if (celeJmenoFotografie.length() > 30) {

            fotografie = ImageIO.read(new File(celeJmenoFotografie));

        } else {
            fotografie = ImageIO.read(getClass().getResourceAsStream(celeJmenoFotografie));
        }
        sirka = fotografie.getWidth() / sloupec;
        vyska = fotografie.getHeight() / radek;

        int VybranyObrazek = 0;

        // Algoritmus pro generování obrázků z fotografie
        for (int radky = 0; radky < radek; radky++) {

            for (int sloupce = 0; sloupce < sloupec; sloupce++) {

                images[VybranyObrazek] = (new BufferedImage(sirka, vyska, fotografie.getType()));

                Graphics2D grafika2D = images[VybranyObrazek++].createGraphics();

                int vybranaFotkaX = sirka * sloupce;
                int vybranaFotkaY = vyska * radky;

                int PuzzlePieceX = sirka * sloupce + sirka;
                int PuzzlePieceY = vyska * radky + vyska;

                // Vykreslení jednotlivých obrázků (částic puzzle)
                grafika2D.drawImage(fotografie, 0, 0, sirka, vyska, vybranaFotkaX, vybranaFotkaY,
                        PuzzlePieceX, PuzzlePieceY, null);
                grafika2D.dispose();

            }
        }

        for (int i = 0; i < PocetFotek; i++) {

            PuzzlePiece piece = new PuzzlePiece(images[i], i);
            fotky.add(piece);
        }

        Collections.shuffle(fotky, new Random());

        // Zobrazení jednotlivých obrázků z vybrané fotografie
        for (int m = 0; m < PocetFotek; m++) {

            ImageView[] imageView = new ImageView[PocetFotek];
            imageView[m] = new ImageView();
            Fotky = imageView[m];
            Fotky.setImage(fotky.get(m).image);
            Fotky.setFitWidth(Piece);
            Fotky.setFitHeight(Piece);
            Fotky.setCursor(Cursor.cursor("OPEN_HAND"));

            // Získávání souřadnic pro jednotlivé částice do rozvržení GridPane
            int x = m / sloupec;
            int y = m % radek;

            // Funkce pro přetahování částic puzzle
            setOnDragDetected(Fotky);
            setOnDragDone(Fotky);

            // Rozdělení částic do dvou skupin
            if (m <= halfGrid) {

                grid1.add(Fotky, x, y);

            } else {

                grid2.add(Fotky, x, y);
            }

            // Drop place
            node = new Pane();
            node.setPrefSize(Piece, Piece);

            // Pozice obrazku pri tahu mysi
            Fotky.setOnMouseDragged(event -> {

                pozice = (x * radek) + y;
                iD = fotky.get(pozice).cislo;

                System.out.println(poziceObrazku);
                System.out.println("Pozice: " + pozice + ", id: " + iD);

            });

            node.setOnMouseDragged(event -> {

                pozice = (x * radek) + y;

                for (Map.Entry<Integer, Integer> entry : poziceObrazku.entrySet()) {

                    if (entry.getValue().equals(pozice)) {

                        iD = entry.getKey();
                        System.out.println(poziceObrazku);

                        break;
                    }
                }
                System.out.println("Pozice: " + pozice + ", id: " + iD);

            });

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

            tableView.getItems().add(new Vysledek(nazevObrazku, LevelFX, getCas().toString(), showHintUsed, skoreFX));
        }
        tableView.setEditable(false);
        tableView.setSelectionModel(null);
        tableView.setMouseTransparent(true);
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

            dataTabulky.add(new Vysledek(nazevObrazku, LevelFX, getCas().toString(), showHintUsed, skoreFX));

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
            dataTabulky.add(new Vysledek(nazevObrazku, LevelFX, getCas().toString(), showHintUsed, skoreFX));

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

            if (showHint != null) {
                pane.getChildren().remove(showHint);
                napoveda.setDisable(false);
            }

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

                for (Node node : dragboard.getChildren()) {

                    node.setOnDragOver(onDragOver -> {
                        onDragOver.consume();

                    });
                }
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

    public void presazeniLimitu(String levelFX, int x, int y, int hodnota, int nastav) {

        if (LevelFX == levelFX && x % hodnota == 0) {

            lokaceX = nastav;
        }
        if (LevelFX == levelFX && y % hodnota == 0) {

            lokaceY = nastav;
        }
    }

    // Tah polozil castici obrazku (puzzle) do daneho objektu
    public void setOnDragDropped(Pane dragboard) {

        dragboard.setOnDragDropped((DragEvent event) -> {

            Dragboard db = event.getDragboard();
            boolean puzzleJePolozena = false;
            int x = (int) (event.getSceneX() - FinalGrid.getLayoutX());
            int y = (int) (event.getSceneY() - FinalGrid.getLayoutY());
            lokaceX = x / Piece;
            lokaceY = y / Piece;

            presazeniLimitu("Easy", x, y, 675, 2);
            presazeniLimitu("Medium", x, y, 700, 4);
            presazeniLimitu("Hard", x, y, 665, 6);
            presazeniLimitu("Expert", x, y, 700, 9);

            NapisLokaci(x);

            if (db.hasImage()) {

                ImageView vybranyObrazek = new ImageView(db.getImage());
                dragboard.getChildren().add(vybranyObrazek);
                vybranyObrazek.setFitWidth(Piece);
                vybranyObrazek.setFitHeight(Piece);
                vybranyObrazek.setCursor(Cursor.cursor("OPEN_HAND"));

                poziceObrazku.put(iD, lokace);

                System.out.println(poziceObrazku);

                int vysledek = 0;

                for (Map.Entry<Integer, Integer> entry : poziceObrazku.entrySet()) {

                    // Poloha obrázku není správná
                    if (!entry.getKey().equals(entry.getValue())) {

                        vysledek++;
                        System.out.println("CHYBA: " + vysledek);

                    }
                }

                setOnDragDetected(vybranyObrazek);
                setOnDragDone(vybranyObrazek);

                dragboard.setMouseTransparent(false);
                vybranyObrazek.setMouseTransparent(false);

                if (poziceObrazku.size() == PocetFotek) {

                    Label kontrola = new Label();
                    kontrola.setPrefSize(308, 104);
                    kontrola.setAlignment(Pos.CENTER);
                    kontrola.setLayoutX(750);
                    kontrola.setLayoutY(100);
                    pane.getChildren().add(kontrola);

                    if (vysledek == 0) {

                        FinalGrid.setStyle("-fx-border-color:" + ColorWIN() + "-fx-border-width: 10");

                        kontrola.setText("You WON!");
                        kontrola.setStyle("-fx-text-fill: " + ColorWIN() + Vzhled());

                        Button showResults = new Button("Show Results");
                        showResults.setPrefSize(208, 52);
                        showResults.setAlignment(Pos.CENTER);
                        showResults.setLayoutX(875);
                        showResults.setLayoutY(1000);

                        pane.getChildren().remove(napoveda);
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

                        kontrola.setText("You LOST!");

                        kontrola.setStyle("-fx-text-fill: " + ColorLOSE() + Vzhled());
                    }

                    FinalGrid.setDisable(true);
                    timeline.stop();
                    skoreFX = (int) Math.round(1 / (0.0000398 * Math.sqrt(2 * Math.PI) * Math.pow(Math.E, (Math.pow(-cas.celkovyCas, 2) / 10000000))));
                    Photo.setDisable(true);
                    pane.getChildren().remove(napoveda);

                    Button playAgain = new Button("PLAY AGAIN");
                    playAgain.setLayoutX(1200);
                    playAgain.setLayoutY(970);

                    playAgain.setOnAction(event1 -> {

                        tryAgain(event1);
                    });

                    pane.getChildren().add(playAgain);
                }

                event.consume();

                puzzleJePolozena = true;
            }
            event.setDropCompleted(puzzleJePolozena);

            event.consume();
        });
    }

    private void NapisLokaci(int a) {

        if (a == 0) {

            lokaceX = 0;
        }

        if (lokaceY > 0) {

            if (lokaceY == 1) {

                lokaceX += radek - 1;

            } else if (lokaceY == 2) {

                lokaceX += radek + number;

            } else {

                lokaceX += radek + number + nasobek * (lokaceY - 2);
            }
        }

        lokace = lokaceX + lokaceY;
    }

    public String Vzhled() {

        return "-fx-font-size: 60;-fx-background-color: black;-fx-font-weight: bold;" +
                "-fx-font-style: italic;";
    }

    public String ColorWIN() {

        return "linear-gradient(to right, rgba(34,193,195,1) 0%, rgba(253,187,45,1) 100%);";
    }

    public String ColorLOSE() {

        return "linear-gradient(to right, rgba(255,0,0,1) 0%, rgba(255,231,0,1) 100%);";
    }

    public Parent getRoot() {

        return pane;
    }

    public void tryAgain(ActionEvent event) {

        try {
            MainFX mainFX = new MainFX();
            Scene scene = new Scene(mainFX.getRoot());
            scene.getStylesheets().add(getClass().getResource("/css/MainScene.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setFullScreenExitHint("");
            stage.setFullScreen(true);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}