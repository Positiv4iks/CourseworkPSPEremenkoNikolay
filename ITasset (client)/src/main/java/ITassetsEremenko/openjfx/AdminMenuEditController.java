package ITassetsEremenko.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuEditController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    void onBack(ActionEvent event) throws IOException {
        Client.setRoot("AdminMenu");
    }


    @FXML
    void toEditAssets(ActionEvent event) throws IOException {
        Client.setRoot("AdminMenuEditAssets");
    }

    @FXML
    void toEditDepreciation(ActionEvent event) throws IOException {
        Client.setRoot("AdminMenuEditDepreciation");
    }

    @FXML
    void toEditEmployee(ActionEvent event) throws IOException {
        Client.setRoot("AdminMenuEditEmployee");
    }

    @FXML
    void initialize() {

    }
}
