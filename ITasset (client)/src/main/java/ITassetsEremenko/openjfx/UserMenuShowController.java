package ITassetsEremenko.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;




public class UserMenuShowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button showBtn;

    @FXML
    private Button addBtn;

    @FXML
    void showAssets(ActionEvent event) throws IOException {
        Client.setRoot("UserMenuShowAssets");
    }

    @FXML
    void showDepreciation(ActionEvent event) throws IOException {
        Client.setRoot("UserMenuShowDepreciation");
    }



    @FXML
    void onBack(ActionEvent event) throws IOException {
        Client.setRoot("UserMenu");
    }

    @FXML
    void initialize() {

    }



}