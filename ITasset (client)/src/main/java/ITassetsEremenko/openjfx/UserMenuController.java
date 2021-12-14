package ITassetsEremenko.openjfx;

import ITassetsEremenko.config.Connection;
import ITassetsEremenko.config.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ITassetsEremenko.model.DataObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserMenuController extends Connection {
    private DataObject dataObject;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button showBtn;

    @FXML
    private Button addBtn;

    @FXML
    void addAssets(ActionEvent event) throws IOException {
        Client.setRoot("UserMenuAdd");
    }

    @FXML
    void showAssets(ActionEvent event) throws IOException {
        Client.setRoot("UserMenuShow");
    }

    @FXML
    void onBack(ActionEvent event) throws IOException {
        Client.setRoot("Authorization");
    }

    @FXML
    void toGraphic(ActionEvent event) throws IOException {
        Client.setRoot("UserMenuGraphicShow");
    }

    @FXML
    void fileRes(ActionEvent event) throws ClassNotFoundException {
        DatabaseConnection fr = new DatabaseConnection();
        fr.filewriter();
    }


    @FXML
    void initialize() {
        dataObject = new DataObject();
        if (socket == null || socket.isClosed()) {
            connect();
        }

    }

}
