package Controller;

import JDBC.LoginDB;
import Models.Session;
import Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.StageHandler;

import java.io.IOException;


public class LoginController extends VBox {
    @FXML
    private TextField userNameField, passwordField;
    @FXML
    private Label responseLabel;
    @FXML
    private Parent root;

    private Session session;
    private LoginDB loginDB = new LoginDB();
    private StageHandler stageHandler = new StageHandler();


    public LoginController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Login2.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
        @FXML
        public void login(ActionEvent ae){
            String userName = userNameField.getText();
            String password = passwordField.getText();
            //if (loginDB.isValid(userName, password)){
            if (2>1){
                User user = new User(userName, password);
                session = new Session(user);
                HomeController homeController = new HomeController(session);
                Stage oldStage = stageHandler.getParentStage(root);
                Stage primaryStage = new Stage();
                primaryStage.setTitle("QuickBlogPoster");
                primaryStage.setHeight(600);
                primaryStage.setWidth(800);
                primaryStage.setScene(new Scene(homeController));
                primaryStage.setResizable(false);
                primaryStage.show();
                oldStage.close();
            }
            else if (loginDB.userNameExist(userName)){
                responseLabel.setText("Wrong password");
                passwordField.getStyleClass().add("error");
            }else {
                responseLabel.setText("User: \"" + userName + "\" doesn't exists");
                userNameField.getStyleClass().add("error");
                passwordField.getStyleClass().add("error");
            }

    }
    @FXML
    public void onEnter(ActionEvent ae) {
        try {
            login(ae);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
