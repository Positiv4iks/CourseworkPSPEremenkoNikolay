package ITassetsEremenko.openjfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ITassetsEremenko.animation.Shake;
import ITassetsEremenko.config.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ITassetsEremenko.model.DataObject;

public class AuthorizationAdminController extends Connection {
    private DataObject dataObject;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button enterBtn;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    public AuthorizationAdminController(){
        dataObject = new DataObject();
        if (socket == null || socket.isClosed()) {
            connect();
        }
    }

    @FXML
    void onBack(ActionEvent event) throws IOException {
        Client.setRoot("Authorization");
    }

    @FXML
    void toAdminEnter(ActionEvent event) {
        dataObject = new DataObject();
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            dataObject.setCommand("adminAuthorization");
            dataObject.setLogin(login.getText().trim());
            dataObject.setPassword(password.getText().trim());
            out.writeObject(dataObject);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataObject = (DataObject) in.readObject();
            if(dataObject.getResult()){
                System.out.println("Выполнен вход Администратора");
                Client.setRoot("AdminMenu");
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
    void initialize() {

    }
}
