package ITassetsEremenko.server;

import ITassetsEremenko.model.DataObject;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ClientHandler {
    private ServerTCP server;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private DatabaseConnection db;
    static int counter = 0;

    public ClientHandler(ServerTCP srv, Socket sock) throws ClassNotFoundException {
        try {
            this.server = srv;
            this.socket = sock;
            this.db = new DatabaseConnection();
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
            new Thread(() -> {
                counter++;
                System.out.println("Подключение №" + counter + " Адрес: " + socket.getInetAddress() + ", Порт:" + socket.getPort() + "(" + socket.getLocalPort() + ")");
                try {
                    DataObject dataObj;
                    try {
                        dataObj = (DataObject) in.readObject();
                        switch (dataObj.getCommand()) {
                            case "adminAuthorization": {
                                dataObj = this.db.getAuthorization(dataObj, DatabaseOptions.ADMIN_TABLE);
                                sendMsg(dataObj);
                                break;
                            }
                            case "userAuthorization": {
                                dataObj = this.db.getAuthorization(dataObj, DatabaseOptions.USER_TABLE);
                                sendMsg(dataObj);
                                break;
                            }
                            case "adminRegistration": {
                                dataObj = this.db.getAuthorization(dataObj, DatabaseOptions.ADMIN_TABLE);
                                if (!dataObj.getResult()) {
                                    dataObj = this.db.getRegistration(dataObj, DatabaseOptions.ADMIN_TABLE);
                                }
                                sendMsg(dataObj);
                                break;
                            }
                            case "userRegistration": {
                                dataObj = this.db.getAuthorization(dataObj, DatabaseOptions.USER_TABLE);
                                if (!dataObj.getResult()) {
                                    dataObj = this.db.getRegistration(dataObj, DatabaseOptions.USER_TABLE);
                                }
                                sendMsg(dataObj);
                                break;
                            }
                            case "deleteUser": {
                                this.db.deleteUser(dataObj, DatabaseOptions.USER_TABLE);
                                sendMsg(dataObj);
                                break;
                            }
                            case "showAssets": {
                                ArrayList<DataObject> objectArrayList = null;
                                objectArrayList = this.db.getAssets(objectArrayList);
                                sendObj(objectArrayList);
                                break;
                            }
                            case "editAssets": {
                                this.db.editAssets(dataObj);
                                sendMsg(dataObj);
                                break;
                            }
                            case "addAssets": {
                                dataObj = this.db.getAsset(dataObj);
                                sendMsg(dataObj);
                                break;
                            }
                            case "deleteAssets": {
                                this.db.deleteAssets(dataObj);
                                sendMsg(dataObj);
                                break;
                            }
                            case "addDepreciation": {
                                dataObj = this.db.getDepreciation(dataObj);
                                sendMsg(dataObj);
                                break;
                            }
                            case "showDepreciation": {
                                ArrayList<DataObject> objectArrayList = null;
                                objectArrayList = this.db.getDepreciation(objectArrayList);
                                sendObj(objectArrayList);
                                break;
                            }
                            case "deleteDepreciation": {
                                this.db.deleteDepreciation(dataObj);
                                sendMsg(dataObj);
                                break;
                            }
                            case "editDepreciation": {
                                this.db.editDepreciation(dataObj);
                                sendMsg(dataObj);
                                break;
                            }
                                // Сотрудники
                                case "addEmployeeData": {
                                    dataObj = this.db.getEmployee(dataObj);
                                    sendMsg(dataObj);
                                    break;
                            }
                            case "showEmployee": {
                                ArrayList<DataObject> objectArrayList = null;
                                objectArrayList = this.db.getEmployee(objectArrayList);
                                sendObj(objectArrayList);
                                break;
                            }

                            case "deleteEmployee": {
                                this.db.deleteEmployee(dataObj);
                                sendMsg(dataObj);
                                break;
                            }

                            case "editEmployee": {
                                this.db.editEmployee(dataObj);
                                sendMsg(dataObj);
                                break;
                            }
                        }
                        System.out.println("Операция прошла успешно");
                    } catch (EOFException e) {

                    } catch (SocketException e) {

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } finally {
//                    try {
//                        socket.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void sendMsg(DataObject msg) {
        try {
            out.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendObj(ArrayList<DataObject> objects) {
        try {
            out.writeObject(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
