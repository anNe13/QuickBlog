package Controller;


import JDBC.PostDB;
import Models.Session;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.Main;
import sample.StageHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;


public class HomeController extends BorderPane {

    @FXML
    private BorderPane root;
    @FXML
    private TextField tittleField;
    @FXML
    private TextArea contentField;
    @FXML
    private Label locationResultLabel;
    @FXML
    private CheckBox locationCheckbox;

    private Session session;
    private PostDB postDB = new PostDB();
    private String location = "";
    private StageHandler stageHandler = new StageHandler();


    public HomeController(Session session) {
        this.session = session;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/home.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void post(ActionEvent ae) {
        Calendar calendar = Calendar.getInstance();
        java.sql.Date curDate = new java.sql.Date(calendar.getTime().getTime());

        String userName = session.getUser().getUserName();
        String tittle = tittleField.getText();
        String content = contentField.getText();

        postDB.post(tittle, content, userName, curDate);


    }

    @FXML
    public void includeLocation(ActionEvent ae) {
        if (!locationCheckbox.isSelected()) {
            locationResultLabel.setText("");
        } else {
            try {
                BufferedReader ipReader = null;
                URL locationData = new URL("http://ip-api.com/line/");
                ipReader = new BufferedReader(new InputStreamReader(locationData.openStream()));
                if (ipReader.readLine().equalsIgnoreCase("fail")) {
                    locationResultLabel.setText("There was an internal problem with the location finder service");
                } else {
                    List<String> resultList = new ArrayList<>();
                    for (int i = 0; i < 5; i++) {
                        resultList.add(ipReader.readLine());
                    }
                    location = resultList.get(4) + ", " + resultList.get(0);
                    locationResultLabel.setText(location);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                locationResultLabel.setText("There was a problem with internet connection");
                e.printStackTrace();

            }

        }
    }
    @FXML
    public void logout(ActionEvent ae){

        Stage stage = (Stage) this.getScene().getWindow();
        stage.close();


        LoginController loginController = new LoginController();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("QuickBlogger");
        primaryStage.setHeight(300);
        primaryStage.setWidth(400);
        Scene scene = new Scene(loginController);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();

    }
}



