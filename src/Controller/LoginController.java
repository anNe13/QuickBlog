package Controller;

import JDBC.UserDB;
import Models.Session;
import Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.StageHandler;

import java.io.IOException;

public class LoginController extends VBox {

    @FXML
    private TextField userNameField, passwordField;
    @FXML
    private Label responseLabel, connectionResponseLabel;
    @FXML
    private Parent root;
    @FXML
    private Hyperlink regLink;

    private Session session;
    private UserDB userDB = new UserDB();
    private StageHandler stageHandler = new StageHandler();


    public LoginController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
        @FXML
        public void login(ActionEvent ae) {
            String userName = userNameField.getText();
            String password = passwordField.getText();

                if (userDB.isValid(userName, password)){
                    User user = new User(userName, password);
                    session = new Session(user);
                    HomeController homeController = new HomeController(session);
                    Stage oldStage = stageHandler.getParentStage(root);
                    Stage primaryStage = new Stage();
                    primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/birdicon.png")));
                    primaryStage.setTitle("QuickBlogPoster");
                    primaryStage.setHeight(600);
                    primaryStage.setWidth(800);
                    primaryStage.setScene(new Scene(homeController));
                    primaryStage.setResizable(false);
                    primaryStage.show();
                    oldStage.close();
                }
                else if (userDB.userNameExist(userName)){
                    responseLabel.setText("Wrong password");
                    passwordField.getStyleClass().add("error");
                }else {
                    responseLabel.setText("User: \"" + userName + "\" doesn't exists");
                    userNameField.getStyleClass().add("error");
                    passwordField.getStyleClass().add("error");
                }

        }

    public void onEnter(ActionEvent ae) {
            login(ae);
    }

    @FXML
    public void registerNew(ActionEvent ae) {
        RegisterController registerController = new RegisterController();
        Stage oldStage = stageHandler.getParentStage(root);
        Stage primaryStage = new Stage();
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/birdicon.png")));
        primaryStage.setTitle("QuickBlogPoster");
        primaryStage.setHeight(300);
        primaryStage.setWidth(400);
        primaryStage.setScene(new Scene(registerController));
        primaryStage.setResizable(false);
        primaryStage.show();
        oldStage.close();
    }
}
