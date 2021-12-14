package ITassetsEremenko.openjfx;

import ITassetsEremenko.animation.Shake;
import ITassetsEremenko.config.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ITassetsEremenko.model.DataObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AuthorizationUserController extends Connection {
    private DataObject dataObject;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button enterBtn;

    @FXML
    private Button registrationBtn;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    void onBack(ActionEvent event) throws IOException {
        Client.setRoot("Authorization");
    }

    @FXML
    void toUserEnter(ActionEvent event) {
        dataObject = new DataObject();
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            dataObject.setCommand("userAuthorization");
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
                System.out.println("Выполнен вход пользователя");
                Client.setRoot("UserMenu");
            }
            else {
                Shake userLoginAnimation = new Shake(login);
                Shake userPassAnimation = new Shake(password);
                userLoginAnimation.playAnimation();
                userPassAnimation.playAnimation();
            }

            closeConnect();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void toUserRegistration(ActionEvent event) {
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
              Client.setRoot("Authorization");
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
        dataObject = new DataObject();
        if (socket == null || socket.isClosed()) {
            connect();
        }
    }

}
