package ITassetsEremenko.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {
    protected static int PORT=1010;
    public ServerTCP() {


        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер успешно запущен!");
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(this,socket);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }










    /*
    static int counter = 0;
    private static ServerTCP instance; // приватное статистическое поле
    private ServerTCP(){} // конструктор приватный чтоб не было доступа за пределами класса

    public ServerTCP getInstance() { // Объявить статический создающий метод, который будет использоваться для получения одиночки
        if(instance == null){		// если объект еще не создан
            instance = new ServerTCP();	// создать новый объект
        }
        new Thread(() -> {
            Socket socket = new Socket();
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                System.out.println("Сервер успешно запущен!");
                while (true) {
                     socket = serverSocket.accept();
                    new ClientHandler(this,socket);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            counter++;
            System.out.println("Подключение №" + counter + " Адрес: " + socket.getInetAddress() + ", Порт:" + socket.getPort() + "(" + socket.getLocalPort() + ")");
        });

        return instance;
    }

     */
}

/*
public class ServerTCP {
    private static ServerTCP instance; // создали объект
    private ServerTCP(){}

    protected static int PORT=1010;
    public ServerTCP getInstance() {
        if(instance == null){		//если объект еще не создан
            instance = new ServerTCP();	//создать новый объект
        }

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер успешно запущен!");
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(this,socket);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return instance;
    }
}
*/