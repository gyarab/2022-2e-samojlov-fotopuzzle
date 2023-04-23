package puzzlegame;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;


/**
 * @author VS
 */
public class SettingsMenuKontroler implements Initializable {

    @FXML
    private ImageView IMGview2;

    @FXML
    private ImageView Mesto1;

    @FXML
    private ImageView Mesto2;

    @FXML
    private ImageView IMgNew;

    @FXML
    private ImageView Tygr;

    @FXML
    private ImageView Liska;

    @FXML
    private ImageView CheckMark;

    @FXML
    private RadioButton easy;

    @FXML
    private RadioButton medium;

    @FXML
    private RadioButton hard;

    @FXML
    private RadioButton expert;

    @FXML
    private BorderPane mesto1;

    @FXML
    private BorderPane tygr;

    @FXML
    private BorderPane liska;

    @FXML
    private BorderPane mesto2;

    @FXML
    private BorderPane imageNew;

    @FXML
    private Button another;

    private ToggleGroup difficulties;
    private String imageFile;
    private FileWriter writer1 = null;
    private FileWriter writer2 = null;
    private FileWriter aFileWriter = null;
    File obrazky;
    File obtiznosti;
    File anotherTXT;
    String odpoved1;
    String odpoved2;
    String odpoved3;
    FileReader hledej1;
    FileReader hledej2;
    FileReader hledej3;
    FileChooser fileChooser;
    File selectedFile;
    Scanner scanner1;
    Scanner scanner2;
    Scanner scanner3;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        difficulties = new ToggleGroup();
        easy.setToggleGroup(difficulties);
        medium.setToggleGroup(difficulties);
        hard.setToggleGroup(difficulties);
        expert.setToggleGroup(difficulties);

        obrazky = new File("obrazky.txt");
        obtiznosti = new File("obtiznosti.txt");
        anotherTXT = new File("writer-another.txt");

        for (int i = 0; i < 1; i++) {

            Obtiznosti();
            Obrazky();
            Images();
        }
    }

    public void Obtiznosti() {

        try {

            hledej1 = new FileReader(obtiznosti);

        } catch (FileNotFoundException ex) {

            Logger.getLogger(SettingsMenuKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        scanner1 = new Scanner(hledej1);

        while (scanner1.hasNext()) {

            odpoved1 = scanner1.nextLine();
        }
        if (odpoved1.equals("Easy")) {

            easy.setSelected(true);
        } else if (odpoved1.equals("Medium")) {

            medium.setSelected(true);
        } else if (odpoved1.equals("Hard")) {

            hard.setSelected(true);
        } else if (odpoved1.equals("Expert")) {

            expert.setSelected(true);
        }
    }

    public void Obrazky() {

        try {

            hledej2 = new FileReader(obrazky);

        } catch (FileNotFoundException ex) {

            Logger.getLogger(SettingsMenuKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        scanner2 = new Scanner(hledej2);

        while (scanner2.hasNext()) {

            odpoved2 = scanner2.nextLine();
        }
        switch (odpoved2) {

            case "Mesto1":
                Mesto1Selected();
                break;

            case "Mesto2":
                Mesto2Selected();
                break;

            case "Tygr":
                TygrSelected();
                break;

            case "Liska":
                LiskaSelected();
                break;

            default:
                AnotherSelected();

                try {
                    hledej3 = new FileReader(anotherTXT);
                    scanner3 = new Scanner(hledej3);

                    while (scanner3.hasNext()) {

                        for (int cyklus = 0; cyklus < 1; cyklus++) {

                            odpoved3 = scanner3.nextLine();

                        }
                    }

                    Image imagefx = new Image(new FileInputStream("C:\\Users\\VS\\IdeaProjects\\PuzzleGameFX\\src\\another\\" + odpoved3));
                    IMgNew.setImage(imagefx);
                    scanner3.close();

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(SettingsMenuKontroler.class.getName()).log(Level.SEVERE, null, ex);

                }
        }
    }

    public void Images() {

        Image image = new Image(getClass().getResourceAsStream("/images/SettingsIcon.png"));
        IMGview2.setImage(image);

        Image img = new Image(getClass().getResourceAsStream("/images/puzzle1.jpg"));
        Mesto1.setImage(img);

        Image IMG = new Image(getClass().getResourceAsStream("/images/puzzle2.jpg"));
        Tygr.setImage(IMG);

        Image iMg = new Image(getClass().getResourceAsStream("/images/puzzle3.jpg"));
        Liska.setImage(iMg);

        Image ImG = new Image(getClass().getResourceAsStream("/images/puzzle4.jpg"));
        Mesto2.setImage(ImG);

    }

    @FXML
    public void menuButtonClicked(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/PuzzleMenu.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void AnotherPictureClicked(ActionEvent event) throws IOException {

        fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg"));

        selectedFile = fileChooser.showOpenDialog(another.getScene().getWindow());

        if (selectedFile != null) {

            imageFile = selectedFile.toURI().toURL().toString();
            Image image = new Image(imageFile);
            IMgNew.setImage(image);

            try {

                aFileWriter = new FileWriter(anotherTXT);
                aFileWriter.write(selectedFile.getName());

                Path source = Paths.get(selectedFile.toURI());
                Path dest = Paths.get("C:\\Users\\VS\\IdeaProjects\\PuzzleGameFX\\src\\another\\" + selectedFile.getName());
                Files.copy(source, dest);

                Image Efekt = new Image(getClass().getResourceAsStream("/images/checkmark.png"));
                CheckMark.setImage(Efekt);
                CheckMark.setVisible(true);

                RotateTransition efektRotace = new RotateTransition();
                efektRotace.setNode(CheckMark);
                efektRotace.setDuration(Duration.millis(750));
                efektRotace.setCycleCount(1);
                efektRotace.setInterpolator(Interpolator.LINEAR);
                efektRotace.setAxis(Rotate.X_AXIS);
                efektRotace.setByAngle(360);
                efektRotace.play();

            } finally {

                try {
                    aFileWriter.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void Zmena(FileWriter writer, File file, String text) {

        try {
            writer = new FileWriter(file);
            writer.write(text);

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void difficultyChanged() {

        if (difficulties.getSelectedToggle().equals(easy)) {

            Zmena(writer1, obtiznosti, "Easy");
        }
        if (difficulties.getSelectedToggle().equals(medium)) {

            Zmena(writer1, obtiznosti, "Medium");
        }
        if (difficulties.getSelectedToggle().equals(hard)) {

            Zmena(writer1, obtiznosti, "Hard");
        }
        if (difficulties.getSelectedToggle().equals(expert)) {

            Zmena(writer1, obtiznosti, "Expert");
        }
    }

    public void mesto1Clicked() {

        Mesto1Selected();

        Zmena(writer2, obrazky, "Mesto1");
    }

    public void liskaClicked() {

        LiskaSelected();

        Zmena(writer2, obrazky, "Liska");
    }

    public void mesto2Clicked() {

        Mesto2Selected();

        Zmena(writer2, obrazky, "Mesto2");
    }

    public void tygrClicked() {

        TygrSelected();
        Zmena(writer2, obrazky, "Tygr");
    }

    public void NewPhotoClicked() {

        AnotherSelected();

        Zmena(writer2, obrazky, "AnotherPicture");

    }

    public void TygrSelected() {

        mesto1.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        mesto2.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        tygr.setStyle("-fx-border-color: yellow; -fx-border-style: solid; -fx-border-width: 5;");
        liska.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        imageNew.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");

    }

    public void Mesto1Selected() {

        mesto1.setStyle("-fx-border-color: yellow; -fx-border-style: solid; -fx-border-width: 5;");
        mesto2.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        tygr.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        liska.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        imageNew.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
    }

    public void LiskaSelected() {

        mesto1.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        mesto2.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        tygr.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        liska.setStyle("-fx-border-color: yellow; -fx-border-style: solid; -fx-border-width: 5;");
        imageNew.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
    }

    public void Mesto2Selected() {

        mesto1.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        mesto2.setStyle("-fx-border-color: yellow; -fx-border-style: solid; -fx-border-width: 5;");
        tygr.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        liska.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        imageNew.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
    }

    public void AnotherSelected() {

        if (IMgNew.getImage() == null) {

            imageNew.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");

        } else {
            imageNew.setStyle("-fx-border-color: yellow; -fx-border-style: solid; -fx-border-width: 5;");
        }
        mesto1.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        mesto2.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        tygr.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        liska.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
    }
}




