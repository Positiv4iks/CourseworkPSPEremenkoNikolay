package ITassetsEremenko.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuSDController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void onBack(ActionEvent event) throws IOException {
        Client.setRoot("AdminMenu");
    }

    @FXML
    void toDeleteAssets(ActionEvent event) throws IOException {
        Client.setRoot("AdminMenuSDAssets");
    }

    @FXML
    void toDeleteDepreciation(ActionEvent event) throws IOException {
        Client.setRoot("AdminMenuSDDepreciation");
    }

    @FXML
    void toDeleteEmployee(ActionEvent event) throws IOException {
        Client.setRoot("AdminMenuSDEmployee");
    }


    @FXML
    void initialize() {

    }
}
