package ITassetsEremenko.openjfx;

import ITassetsEremenko.config.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import ITassetsEremenko.model.DataObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserMenuShowAssetsController extends Connection {
    private DataObject dataObject;
    private ArrayList<DataObject> dataObjects;
    private ObservableList<DataObject> Assets = FXCollections.observableArrayList();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<DataObject> showTable = new TableView<DataObject>();

    @FXML
    void onBack(ActionEvent event) throws IOException {
        Client.setRoot("UserMenuShow");
    }

    @FXML
    void initialize() {
        renderTable();
    }

    public void renderTable() {
        dataObject = new DataObject();
        dataObjects = null;
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            dataObject.setCommand("showAssets");
            out.writeObject(dataObject);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object obj = in.readObject();
            dataObjects = (ArrayList<DataObject>) obj;
            for (int i = 0; i < showTable.getItems().size(); i++) {
                showTable.getItems().clear();
            }
            if (dataObjects.size() != 0) {
                Assets.addAll(dataObjects);
                showTable.setItems(Assets);
            }
            closeConnect();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}