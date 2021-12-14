package ITassetsEremenko.openjfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.event.ActionEvent;


public class UserMenuGraphicShowController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button showBtn;

    @FXML
    void showYearGraphic(ActionEvent event) throws IOException {
        Client.setRoot("UserMenuYearGraphic");
    }

    @FXML
    void showMonthGraphic(ActionEvent event) throws IOException  {
        Client.setRoot("UserMenuMonthGraphic");
    }

    @FXML
    void onBack(ActionEvent event) throws IOException {
        Client.setRoot("UserMenu");
    }

    @FXML
    void initialize() {

    }
}
