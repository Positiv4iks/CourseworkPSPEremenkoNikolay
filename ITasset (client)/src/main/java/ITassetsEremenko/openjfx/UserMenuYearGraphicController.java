package ITassetsEremenko.openjfx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ITassetsEremenko.config.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import ITassetsEremenko.model.DataObject;

public class UserMenuYearGraphicController extends Connection {
    private DataObject dataObject;
    private ArrayList<DataObject> dataObjects;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BarChart barchart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;


    @FXML
    void onBack(ActionEvent event) throws IOException {
        Client.setRoot("UserMenuGraphicShow");
    }

    @FXML
    void initialize() {
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
            Object obj = in.readObject();
            dataObjects = (ArrayList<DataObject>) obj;
            if (dataObjects.size() != 0) {
                XYChart.Series dataSeries1 = new XYChart.Series<>();
                for(DataObject dataObject1:dataObjects){
                    dataSeries1.getData().add(new XYChart.Data(dataObject1.getName(),dataObject1.getYearPercent()));
                }
                barchart.getData().addAll(dataSeries1);
            }
            closeConnect();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
