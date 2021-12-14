package ITassetsEremenko.server;

import java.io.IOException;
import java.sql.SQLException;

public class Server {

    public static void main(String[] args) throws IOException, InstantiationException, SQLException, IllegalAccessException, ClassNotFoundException {

          new ServerTCP();

    }
}
