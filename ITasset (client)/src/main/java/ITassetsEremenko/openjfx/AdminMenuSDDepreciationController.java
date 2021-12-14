package ITassetsEremenko.openjfx;

import ITassetsEremenko.config.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import ITassetsEremenko.model.DataObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminMenuSDDepreciationController extends Connection {
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
    private Button removeBtn;

    @FXML
    void onBack(ActionEvent event) throws IOException {
        Client.setRoot("AdminMenuSD");
    }

    @FXML
    void removeDepreciation(ActionEvent event) {
        dataObject = new DataObject();
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            dataObject.setCommand("deleteDepreciation");
            dataObject.setId(showSecondTable.getSelectionModel().getSelectedItem().getId());
            System.out.println(dataObject.getId());
            out.writeObject(dataObject);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataObject = (DataObject) in.readObject();
            if (dataObject.getResult()) {
                System.out.println("Запись удалена!");
                closeConnect();
                renderTable();
            } else {
                closeConnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        renderTable();
    }

    public void renderTable() {
        dataObject = new DataObject();
        dataObjects = null;
        // dataObjects = new ArrayList<DataObject>();
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
