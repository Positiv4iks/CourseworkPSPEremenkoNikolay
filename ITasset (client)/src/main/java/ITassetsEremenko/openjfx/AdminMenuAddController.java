package ITassetsEremenko.openjfx;

import ITassetsEremenko.config.Connection;
import ITassetsEremenko.model.DataObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AdminMenuAddController extends Connection {
    private DataObject dataObject;
    private ArrayList<DataObject> dataObjects;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField price;

    @FXML
    private TextField name;

    @FXML
    private Button addDepreciationBtn;

    @FXML
    private TextField termOfUse;

    @FXML
    private TextField incomeMoney; // сделать payBack

    @FXML
    private Button addBtn;


    @FXML
    void addInfo(ActionEvent event) {
        dataObject = new DataObject();
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            dataObject.setCommand("addAssets");
            if (!price.getText().isEmpty()) {
                dataObject.setPrice(Double.parseDouble(price.getText()));
            }


            dataObject.setName(name.getText());

            if (!termOfUse.getText().isEmpty()) {
                dataObject.setTermOfUse(Integer.parseInt(termOfUse.getText()));
            }


            if (!incomeMoney.getText().isEmpty()) {
                dataObject.setIncomeMoney(Integer.parseInt(incomeMoney.getText()));
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Добавление нового актива");
                alert.setHeaderText("Шаблон актива успешно добавлен");
                alert.setContentText("Данные установлены в первоначальное значнеие 0, в дальнейшем их можно отредактировать ");
                alert.showAndWait();
            }

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
    void addDepreciation(ActionEvent event) {

        dataObject = new DataObject();
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            dataObject.setCommand("addDepreciation");
            dataObject.setName(name.getText());

           dataObject.setYearPercent(((double)Math.round((100/Double.parseDouble(termOfUse.getText())) *   1000 )) /1000 ); // Округление до 1000
           dataObject.setYearPrice(((double)Math.round((Double.parseDouble(price.getText())/Integer.parseInt(termOfUse.getText()) *   1000))) / 1000);
           dataObject.setMonthPercent(((double)Math.round((((100/Double.parseDouble(termOfUse.getText()))/12) *   1000))) / 1000);
           dataObject.setMonthPrice(((double)Math.round((((Double.parseDouble(price.getText())/(Integer.parseInt(termOfUse.getText())*12)) * 1000)))) / 1000);
           dataObject.setPayBack(((double)Math.round((Double.parseDouble(price.getText())/Double.parseDouble(incomeMoney.getText()) *   1000))) / 1000); // Окупаемость
           dataObject.setRentability(((double)Math.round((Double.parseDouble(incomeMoney.getText())/Double.parseDouble(price.getText()) * 100))));
           dataObject.setTotalBenefit(((double)Math.round((Double.parseDouble(incomeMoney.getText())*Double.parseDouble(termOfUse.getText()) - Double.parseDouble(price.getText())))));

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
            dataObject.setCommand("showAssets");
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
