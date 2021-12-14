package ITassetsEremenko.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AdminMenuGraphicShowController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button showBtn;

    @FXML
    void showYearGraphic(ActionEvent event) throws IOException {
        Client.setRoot("AdminMenuYearGraphic");
    }

    @FXML
    void showMonthGraphic(ActionEvent event) throws IOException  {
        Client.setRoot("AdminMenuMonthGraphic");
    }

    @FXML
    void onBack(ActionEvent event) throws IOException {
        Client.setRoot("AdminMenu");
    }

    @FXML
    void initialize() {

    }
}
