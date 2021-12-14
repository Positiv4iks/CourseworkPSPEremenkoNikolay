module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires sqltool;
    requires javafx.base;
    opens org.openjfx.openjfx to javafx.fxml, javafx.base;
    exports ITassetsEremenko.server;
    opens ITassetsEremenko.server to javafx.base, javafx.fxml;
    exports ITassetsEremenko.model;
    opens ITassetsEremenko.model to javafx.base, javafx.fxml;
}