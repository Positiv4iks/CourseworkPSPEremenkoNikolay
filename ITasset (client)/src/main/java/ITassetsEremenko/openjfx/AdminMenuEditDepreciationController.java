package ITassetsEremenko.openjfx;

import ITassetsEremenko.config.Connection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ITassetsEremenko.model.DataObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminMenuEditDepreciationController extends Connection {
    private DataObject dataObject;
    private ArrayList<DataObject> dataObjects;
    private ObservableList<DataObject> depreciation = FXCollections.observableArrayList();
    private ObservableList<Integer> idsList = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField monthPrice;

    @FXML
    private Button editBtn;

    @FXML
    private TextField name;

    @FXML
    private ComboBox idList;

    @FXML
    private TextField yearPrice;

    @FXML
    private TextField monthPercent;

    @FXML
    private TextField yearPercent;

    @FXML
    private TextField payBack;

    @FXML
    private TextField rentability;

    @FXML
    private TextField totalBenefit;


    @FXML
    void onBack(ActionEvent event) throws IOException {
        Client.setRoot("AdminMenuEdit");
    }

    @FXML
    void editDepreciation(ActionEvent event) {
        dataObject = new DataObject();
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            dataObject.setCommand("editDepreciation");
            dataObject.setId(dataObjects.get(idList.getSelectionModel().getSelectedIndex()).getId());
            dataObject.setName(name.getText());
            dataObject.setYearPercent(Double.parseDouble(yearPercent.getText()));
            dataObject.setYearPrice(Double.parseDouble(yearPrice.getText()));
            dataObject.setMonthPercent(Double.parseDouble(monthPercent.getText()));
            dataObject.setMonthPrice(Double.parseDouble(monthPrice.getText()));

            dataObject.setPayBack(Double.parseDouble(payBack.getText()));
            dataObject.setRentability(Double.parseDouble(rentability.getText()));
            dataObject.setTotalBenefit(Double.parseDouble(totalBenefit.getText()));


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
                yearPercent.setText(Double.toString(dataObjects.get(idList.getSelectionModel().getSelectedIndex()).getYearPrice()));
                yearPrice.setText(Double.toString(dataObjects.get(idList.getSelectionModel().getSelectedIndex()).getYearPercent()));
                monthPercent.setText(Double.toString(dataObjects.get(idList.getSelectionModel().getSelectedIndex()).getMonthPercent()));
                monthPrice.setText(Double.toString(dataObjects.get(idList.getSelectionModel().getSelectedIndex()).getMonthPrice()));
                payBack.setText(Double.toString(dataObjects.get(idList.getSelectionModel().getSelectedIndex()).getPayBack()));
                rentability.setText(Double.toString(dataObjects.get(idList.getSelectionModel().getSelectedIndex()).getRentability()));
                totalBenefit.setText(Double.toString(dataObjects.get(idList.getSelectionModel().getSelectedIndex()).getTotalBenefit()));
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
            dataObject.setCommand("showDepreciation");
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
