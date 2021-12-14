package ITassetsEremenko.openjfx;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthorizationController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button adminBtn;

    @FXML
    private Button userBtn;

    @FXML
    void initialize() {
        assert adminBtn != null : "fx:id=\"adminBtn\" was not injected: check your FXML file 'Authorization.fxml'.";
        assert userBtn != null : "fx:id=\"userBtn\" was not injected: check your FXML file 'Authorization.fxml'.";

    }

    @FXML
    void toAdminAuthorization(ActionEvent event) throws IOException {
        Client.setRoot("AuthorizationAdmin");
    }

    @FXML
    void toUserAuthorization(ActionEvent event) throws IOException {
        Client.setRoot("AuthorizationUser");
    }

}
