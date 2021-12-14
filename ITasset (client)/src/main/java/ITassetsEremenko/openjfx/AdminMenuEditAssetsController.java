package ITassetsEremenko.openjfx;

import ITassetsEremenko.config.Connection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ITassetsEremenko.model.DataObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminMenuEditAssetsController extends Connection {
    private DataObject dataObject;
    private ArrayList<DataObject> dataObjects;
    private ObservableList<DataObject> Assets = FXCollections.observableArrayList();
    private ObservableList<Integer> idsList = FXCollections.observableArrayList();

    @FXML
    private TableView<DataObject> showTable = new TableView<DataObject>();

    @FXML
    private TableColumn<DataObject, Integer> idColumn;

    @FXML
    private TableColumn<DataObject, String> nameColumn;

    @FXML
    private TableColumn<DataObject, Double> priceColumn;

    @FXML
    private TableColumn<DataObject, Integer> termOfUseColumn;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button editBtn;

    @FXML
    private TextField price;

    @FXML
    private TextField name;

    @FXML
    private ComboBox idList;

    @FXML
    private TextField termOfUse;

    @FXML
    private TextField incomeMoney;


    @FXML
    void onBack(ActionEvent event) throws IOException {
        Client.setRoot("AdminMenuEdit");
    }

    @FXML
    void editAsset(ActionEvent event) {
        dataObject = new DataObject();
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            dataObject.setCommand("editAssets");
            dataObject.setId(dataObjects.get(idList.getSelectionModel().getSelectedIndex()).getId());
            dataObject.setName(name.getText());
            dataObject.setPrice(Double.parseDouble(price.getText()));
            dataObject.setTermOfUse(Integer.parseInt(termOfUse.getText()));
            dataObject.setIncomeMoney(Integer.parseInt(incomeMoney.getText()));
            out.writeObject(dataObject);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataObject = (DataObject) in.readObject();
            if (dataObject.getResult()) {
                System.out.println("Запись обновлена!");
            }
            closeConnect();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        renderList();
        idList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                name.setText(dataObjects.get(idList.getSelectionModel().getSelectedIndex()).getName());
                price.setText(Double.toString(dataObjects.get(idList.getSelectionModel().getSelectedIndex()).getPrice()));
                termOfUse.setText(Integer.toString(dataObjects.get(idList.getSelectionModel().getSelectedIndex()).getTermOfUse()));
                incomeMoney.setText(Integer.toString(dataObjects.get(idList.getSelectionModel().getSelectedIndex()).getIncomeMoney()));
            }
        });
    }

    public void renderList() {
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
            Object obj;
            obj = in.readObject();
            dataObjects = (ArrayList<DataObject>) obj;
            idList.getSelectionModel().clearSelection();
            idList.setValue(null);
            if (dataObjects.size() != 0) {
                for (DataObject element : dataObjects) {
                    idsList.add(element.getId());
                }
                idList.setItems(idsList);
            }

            closeConnect();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
