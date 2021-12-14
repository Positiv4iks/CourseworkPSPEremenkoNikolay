package ITassetsEremenko.config;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {
    protected static String current;
    protected static int current_id;

    private static final String IP = "127.0.0.1";
    private static final int PORT = 1010;
    protected Socket socket;
    protected ObjectOutputStream out;
    protected ObjectInputStream in;
    protected void connect() {
        try {
            socket = new Socket(IP,PORT);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void closeConnect() {
        try {
            socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}