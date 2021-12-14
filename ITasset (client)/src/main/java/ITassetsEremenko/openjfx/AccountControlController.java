package ITassetsEremenko.openjfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ITassetsEremenko.config.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ITassetsEremenko.model.DataObject;

public class AccountControlController extends Connection {
    private DataObject dataObject;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button registrationBtn;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Button addUser;

    @FXML
    private Button removeUser;

    public AccountControlController(){
        dataObject = new DataObject();
        if (socket == null || socket.isClosed()) {
            connect();
        }
    }

    @FXML
    void onBack(ActionEvent event) throws IOException {
        Client.setRoot("AdminMenu");
    }

    @FXML
    void toAddUser(ActionEvent event) {
        dataObject = new DataObject();
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            dataObject.setCommand("userRegistration");
            dataObject.setLogin(login.getText().trim());
            dataObject.setPassword(password.getText().trim());
            out.writeObject(dataObject);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataObject = (DataObject) in.readObject();
            if (!dataObject.getResult()) {
                System.out.println("Регистрация пользователя прошла успешно!");
                Client.setRoot("AdminMenu");
            }
            closeConnect();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void toAdminRegistration(ActionEvent event) {
        dataObject = new DataObject();
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            dataObject.setCommand("adminRegistration");
            dataObject.setLogin(login.getText().trim());
            dataObject.setPassword(password.getText().trim());
            out.writeObject(dataObject);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataObject = (DataObject) in.readObject();
            if (!dataObject.getResult()) {
                System.out.println("Регистрация администратора прошла успешно!");
                Client.setRoot("AdminMenu");
            }
            closeConnect();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void toRemoveUser(ActionEvent event) {
        dataObject = new DataObject();
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            dataObject.setCommand("deleteUser");
            dataObject.setLogin(login.getText().trim());
            dataObject.setPassword(password.getText().trim());
            out.writeObject(dataObject);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataObject = (DataObject) in.readObject();
            if (dataObject.getResult()) {
                System.out.println("Удаление пользователя прошло успешно!");
                Client.setRoot("AdminMenu");
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

    }
}
