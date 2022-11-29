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
    RadioButton easy;

    @FXML
    RadioButton medium;

    @FXML
    RadioButton hard;

    @FXML
    RadioButton expert;

    @FXML
    private BorderPane mesto1;

    @FXML
    BorderPane tygr;

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
    Path from;
    Path to;

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
            }
            if (odpoved1.equals("Medium")) {

                medium.setSelected(true);
            }
            if (odpoved1.equals("Hard")) {

                hard.setSelected(true);
            }
            if (odpoved1.equals("Expert")) {

                expert.setSelected(true);
            }

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
                    mesto1.setStyle("-fx-border-color: yellow; -fx-border-style: solid; -fx-border-width: 5;");
                    mesto2.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
                    tygr.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
                    liska.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
                    imageNew.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
                    break;

                case "Mesto2":
                    mesto1.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
                    mesto2.setStyle("-fx-border-color: yellow; -fx-border-style: solid; -fx-border-width: 5;");
                    tygr.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
                    liska.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
                    imageNew.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
                    break;

                case "Tygr":

                    mesto1.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
                    mesto2.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
                    tygr.setStyle("-fx-border-color: yellow; -fx-border-style: solid; -fx-border-width: 5;");
                    liska.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
                    imageNew.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
                    break;

                case "Liska":
                    mesto1.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
                    mesto2.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
                    tygr.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
                    liska.setStyle("-fx-border-color: yellow; -fx-border-style: solid; -fx-border-width: 5;");
                    imageNew.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
                    break;

                default:
                    mesto1.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
                    mesto2.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
                    tygr.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
                    liska.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
                    imageNew.setStyle("-fx-border-color: yellow; -fx-border-style: solid; -fx-border-width: 5;");

                    try {
                        hledej3 = new FileReader(anotherTXT);
                        scanner3 = new Scanner(hledej3);

                        while (scanner3.hasNext()) {

                            for (int cyklus = 0; cyklus < 1; cyklus++) {

                                odpoved3 = scanner3.nextLine();

                            }
                        }

                        String c = "/another/Photos/";
                        Image imagefx = new Image(getClass().getResourceAsStream(c + odpoved3));
                        IMgNew.setImage(imagefx);
                        scanner3.close();

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(SettingsMenuKontroler.class.getName()).log(Level.SEVERE, null, ex);

                    }
            }

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

    }

    @FXML
    public void menuButtonClicked(ActionEvent event) throws Exception {

        Scanner SCAnother = new Scanner(anotherTXT);
        Scanner SCLevel = new Scanner(obtiznosti);
        Scanner SCImage = new Scanner(obrazky);

        System.out.println("\n" + "Level: " + SCLevel.nextLine());

        BufferedReader hledejObrazky = new BufferedReader(new FileReader("obrazky.txt"));
        String radek;

        while ((radek = hledejObrazky.readLine()) != null) {

            if(radek.equals("AnotherPicture")){

                System.out.println("Selected image: " + SCAnother.nextLine() + " (AnotherPicture)" + "\n");
            }
            else{

                System.out.println("Selected image: " + SCImage.nextLine() + "\n");
            }
        }

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

                from = Paths.get(selectedFile.toURI());
                to = Paths.get("C:\\Users\\VS\\Documents\\NetBeansProjects\\Puzzle\\src\\another\\Photos\\" + selectedFile.getName());

                aFileWriter.write(selectedFile.getName());
                Files.copy(from, to);


            } catch (Exception e) {

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

    public void difficultyChanged() {

        if (difficulties.getSelectedToggle().equals(easy)) {

            try {
                writer1 = new FileWriter(obtiznosti);
                writer1.write("Easy");

            } catch (IOException e) {
                e.printStackTrace();

            } finally {

                try {
                    writer1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        if (difficulties.getSelectedToggle().equals(medium)) {

            try {
                writer1 = new FileWriter(obtiznosti);
                writer1.write("");
                writer1.write("Medium");

            } catch (IOException e) {
                e.printStackTrace();

            } finally {

                try {
                    writer1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        if (difficulties.getSelectedToggle().equals(hard)) {

            try {
                writer1 = new FileWriter(obtiznosti);
                writer1.write("");
                writer1.write("Hard");

            } catch (IOException e) {
                e.printStackTrace();

            } finally {

                try {
                    writer1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        if (difficulties.getSelectedToggle().equals(expert)) {

            try {
                writer1 = new FileWriter(obtiznosti);
                writer1.write("");
                writer1.write("Expert");

            } catch (IOException e) {
                e.printStackTrace();

            } finally {

                try {
                    writer1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void mesto1Clicked() {

        mesto1.setStyle("-fx-border-color: yellow; -fx-border-style: solid; -fx-border-width: 5;");
        mesto2.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        tygr.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        liska.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        imageNew.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");

        try {
            writer2 = new FileWriter(obrazky);
            writer2.write("Mesto1");

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                writer2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void liskaClicked() {

        mesto1.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        mesto2.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        tygr.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        liska.setStyle("-fx-border-color: yellow; -fx-border-style: solid; -fx-border-width: 5;");
        imageNew.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");

        try {
            writer2 = new FileWriter(obrazky);
            writer2.write("");
            writer2.write("Liska");

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                writer2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void mesto2Clicked() {

        try {
            writer2 = new FileWriter(obrazky);
            writer2.write("");
            writer2.write("Mesto2");

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                writer2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        mesto1.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        mesto2.setStyle("-fx-border-color: yellow; -fx-border-style: solid; -fx-border-width: 5;");
        tygr.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        liska.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        imageNew.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
    }

    public void tygrClicked() {

        try {
            writer2 = new FileWriter(obrazky);
            writer2.write("");
            writer2.write("Tygr");

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                writer2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        mesto1.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        mesto2.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        tygr.setStyle("-fx-border-color: yellow; -fx-border-style: solid; -fx-border-width: 5;");
        liska.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        imageNew.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");

    }

    public void NewPhotoClicked() {

        try {
            writer2 = new FileWriter(obrazky);
            writer2.write("");
            writer2.write("AnotherPicture");

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                writer2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (IMgNew.getImage() == null) {

            imageNew.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
            mesto1.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
            mesto2.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
            tygr.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
            liska.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");

        } else {
            imageNew.setStyle("-fx-border-color: yellow; -fx-border-style: solid; -fx-border-width: 5;");
            mesto1.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
            mesto2.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
            tygr.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
            liska.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 5;");
        }
    }

}




