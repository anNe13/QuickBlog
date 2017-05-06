package Controller;


import Controller.Popup.Popup;
import JDBC.PostDB;
import Models.Session;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.StageHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.List;
import java.util.stream.Collectors;


public class HomeController extends BorderPane {

    @FXML
    private BorderPane root;
    @FXML
    private TextField titleField;
    @FXML
    private TextArea contentField;
    @FXML
    private Label locationResultLabel, titleCounterLabel, contentCounterLabel;
    @FXML
    private CheckBox locationCheckbox;
    @FXML
    private ImageView postButtonImage;
    @FXML
    private Button postButton;

    private Session session;
    private PostDB postDB = new PostDB();
    private String location = "";
    private StageHandler stageHandler = new StageHandler();
    Popup popup = new Popup();


    public HomeController(Session session) {
        this.session = session;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void post(ActionEvent ae) {
        String userName = session.getUser().getUserName();
        String title = titleField.getText();
        String content = contentField.getText();
            postDB.post(title, content, userName, location);
            clearAllFields();
            popup.puplishInfo();


    }

    @FXML
    public void includeLocation(ActionEvent ae) {


        if (!locationCheckbox.isSelected()) {
            locationResultLabel.setText("");
            location = null;
        } else {
            List<String> eachLine;
            try {
                URL locationData = new URL("http://ip-api.com/line/");
                URLConnection openConnection = locationData.openConnection();
                    try (BufferedReader buffer = new BufferedReader(new InputStreamReader(openConnection.getInputStream()))) {
                                eachLine = buffer.lines().collect(Collectors.toList());
                }
                if (eachLine.get(0).equals("success")) {
                    location = eachLine.get(4) + ", " + eachLine.get(1);
                    locationResultLabel.setText(location);
                }
                else {
                    locationResultLabel.setText("There was an internal problem with the location finder service");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
                locationResultLabel.setText("There was a problem with internet connection");
            } catch (IOException e) {
                locationResultLabel.setText("There was a problem with internet connection");
                e.printStackTrace();

            }

        }
    }

    @FXML
    public void logout(ActionEvent ae) {
        Stage stage = (Stage) this.getScene().getWindow();
        stage.close();
        LoginController loginController = new LoginController();
        Stage primaryStage = new Stage();
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/birdicon.png")));
        primaryStage.setTitle("QuickBlogger");
        primaryStage.setHeight(300);
        primaryStage.setWidth(400);
        Scene scene = new Scene(loginController);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    @FXML
    public void inputLengthControll(KeyEvent event){


        if (titleField.getLength() > 40){
            titleCounterLabel.textProperty()
                    .bind(titleField.textProperty()
                            .length()
                            .multiply(-1)
                            .add(40)
                            .asString("%d"));

            titleCounterLabel.visibleProperty().setValue(true);
        }else {
            titleCounterLabel.textProperty().unbind();
            titleCounterLabel.visibleProperty().setValue(false);
        }

        contentCounterLabel.textProperty()
                .bind(contentField.textProperty()
                        .length()
                        .multiply(-1)
                        .add(400)
                        .asString("%d"));

        if(contentField.getLength() > 400){
            contentCounterLabel.setTextFill(Color.web("red"));
        } else {
            contentCounterLabel.setTextFill(Color.web("white"));
        }
        postButton.disableProperty().bind(Bindings.or(
                titleField.textProperty().length().greaterThan(40),
                contentField.textProperty().length().greaterThan(400)));

    }

    public void clearAllFields() {
        titleField.setText("");
        contentField.setText("");
        locationResultLabel.setText("");
        location = "";
    }
}



