package sample;

import Models.Session;
import Models.User;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import Controller.LoginController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        User user = new User();
        Session session = new Session(user);

        primaryStage.getIcons().add(new Image("birdicon.png"));
        LoginController loginController = new LoginController();

        primaryStage.setTitle("QuickBlogger");
        primaryStage.setHeight(300);
        primaryStage.setWidth(400);
        Scene scene = new Scene(loginController);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
