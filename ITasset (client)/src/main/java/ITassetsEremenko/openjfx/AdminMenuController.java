package ITassetsEremenko.openjfx;

import ITassetsEremenko.config.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ITassetsEremenko.model.DataObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AdminMenuController {
    private ObservableList<DataObject> depreciation = FXCollections.observableArrayList();
    private DataObject dataObject;
    private ArrayList<DataObject> dataObjects;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button showBtn;



    @FXML
    void toAddMenu(ActionEvent event) throws IOException { // функция запускающаяся при нажатии на кнопку
        Client.setRoot("AdminMenuAdd"); // Навзание fxml куда будет переход
    }

    @FXML
    void toShowMenu(ActionEvent event) throws IOException {
        Client.setRoot("AdminMenuShow");
    }

    @FXML
    void toEditMenu(ActionEvent event) throws IOException {
        Client.setRoot("AdminMenuEdit");
    }

    @FXML
    void toDeleteMenu(ActionEvent event) throws IOException {
        Client.setRoot("AdminMenuSD");
    }

    @FXML
    void toGraphic(ActionEvent event) throws IOException {
        Client.setRoot("AdminMenuGraphicShow");
    }

    @FXML
    void onBack(ActionEvent event) throws IOException {
        Client.setRoot("Authorization");
    }

    @FXML
    void toAccControl(ActionEvent event) throws IOException {
        Client.setRoot("AccountControl");
    }

    @FXML
    void toAddMenuEmployee(ActionEvent event) throws IOException {
        Client.setRoot("AdminMenuAddEmployee");
    }

    @FXML
    void fileRes(ActionEvent event) throws IOException, ClassNotFoundException {
        DatabaseConnection fr = new DatabaseConnection();
        fr.filewriter();
    }

    @FXML
    void initialize() {
    }


}

