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

public class AdminMenuEditEmployeeController extends Connection {
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
    private TextField surname;

    @FXML
    private TextField nameEmployee;

    @FXML
    private ComboBox idList;

    @FXML
    private TextField department;



    @FXML
    void onBack(ActionEvent event) throws IOException {
        Client.setRoot("AdminMenuEdit");
    }

    @FXML
    void editEmployee(ActionEvent event) {
        dataObject = new DataObject();
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            dataObject.setCommand("editEmployee");
            dataObject.setId(dataObjects.get(idList.getSelectionModel().getSelectedIndex()).getId());
            dataObject.setNameEmployee(nameEmployee.getText());
            dataObject.setSurname(surname.getText());
            dataObject.setDepartment(department.getText());
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
                nameEmployee.setText(dataObjects.get(idList.getSelectionModel().getSelectedIndex()).getNameEmployee());
                surname.setText(dataObjects.get(idList.getSelectionModel().getSelectedIndex()).getSurname());
                department.setText(dataObjects.get(idList.getSelectionModel().getSelectedIndex()).getDepartment());
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
            dataObject.setCommand("showEmployee");
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
