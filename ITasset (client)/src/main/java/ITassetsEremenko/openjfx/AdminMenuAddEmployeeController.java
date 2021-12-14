package ITassetsEremenko.openjfx;

import ITassetsEremenko.config.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ITassetsEremenko.model.DataObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminMenuAddEmployeeController extends Connection {
    private DataObject dataObject;
    private ArrayList<DataObject> dataObjects;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameEmployee;

    @FXML
    private TextField surname;

    @FXML
    private TextField department;


    @FXML
    private Button addBtnEmpl;

    @FXML
    void addEmployee(ActionEvent event) {
        dataObject = new DataObject();
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            dataObject.setCommand("addEmployeeData");
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
                System.out.println("Запись добавлена!");
            }
            closeConnect();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



    @FXML
    void onBack(ActionEvent event) throws IOException {
        Client.setRoot("AdminMenu");
    }

    @FXML
    void initialize() {
        dataObject = new DataObject();
        dataObjects = null;
        // dataObjects = new ArrayList<DataObject>();
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
            Object obj = in.readObject();
            dataObjects = (ArrayList<DataObject>) obj;
            closeConnect();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
