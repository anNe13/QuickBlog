package Controller.Popup;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class Popup extends VBox {
    @FXML
    private Parent root;

    public void warning(String header, String content) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.showAndWait();
    }

    public void puplishInfo(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Published");
        alert.setContentText("Your post is now published on ");


        FlowPane flowPane = new FlowPane();
        Hyperlink link = new Hyperlink("http://quickbloger.azurewebsites.net/");
        link.setOnAction(event -> {
            try {
                java.awt.Desktop.getDesktop().browse(new URI("http://quickbloger.azurewebsites.net/"));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });

        flowPane.getChildren().addAll( link);
        alert.getDialogPane().contentProperty().set(flowPane);
        alert.showAndWait();
    }
}


