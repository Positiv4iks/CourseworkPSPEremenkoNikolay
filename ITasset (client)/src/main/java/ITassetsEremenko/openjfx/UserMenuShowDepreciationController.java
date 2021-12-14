package ITassetsEremenko.openjfx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ITassetsEremenko.config.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import ITassetsEremenko.model.DataObject;

public class UserMenuShowDepreciationController extends Connection {
    private DataObject dataObject;
    private ArrayList<DataObject> dataObjects;
    private ObservableList<DataObject> depreciation = FXCollections.observableArrayList();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<DataObject> showSecondTable = new TableView<DataObject>();

    @FXML
    void onBack(ActionEvent event) throws IOException {
        Client.setRoot("UserMenuShow");
    }

    @FXML
    void initialize() {
        renderSecondTable();
    }

    public void renderSecondTable() {
        dataObject = new DataObject();
        dataObjects = null;
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            dataObject.setCommand("showDepreciation");
            out.writeObject(dataObject);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object obj = in.readObject();
            dataObjects = (ArrayList<DataObject>) obj;
            for (int i = 0; i < showSecondTable.getItems().size(); i++) {
                showSecondTable.getItems().clear();
            }
            if (dataObjects.size() != 0) {
                depreciation.addAll(dataObjects);
                showSecondTable.setItems(depreciation);
            }
            closeConnect();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}