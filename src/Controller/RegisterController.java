package Controller;

import JDBC.UserDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.UnknownHostException;


public class RegisterController extends VBox {
    @FXML
    private TextField userNameField, passwordField1, passwordField2;
    @FXML
    private Label responseLabel;

    private UserDB userDB = new UserDB();

    public RegisterController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/registernew.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void register(ActionEvent ae)  {

        if (validate()) {
            userDB.tryRegisterUser(userNameField.getText(), passwordField1.getText());
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
    }

    public void onEnter(ActionEvent ae) {
        register(ae);
    }

    public boolean validate()  {
        responseLabel.setText("");
        String username = userNameField.getText();
        String password1 = passwordField1.getText();
        String password2 = passwordField2.getText();
        if (username.isEmpty()) {
            responseLabel.setText("please set username");
        } else if (userDB.userNameExist(username)) {
            responseLabel.setText("user " + username + " alredy exists");
        } else if (!password1.isEmpty() && !password2.isEmpty() && !password1.equals(password2)) {
            responseLabel.setText("both password inputs needs to be equal");
        } else if (password1.isEmpty() || password2.isEmpty()) {
            responseLabel.setText("please fill all fields");
        } else {
            return true;
        }
        return false;

    }
}

