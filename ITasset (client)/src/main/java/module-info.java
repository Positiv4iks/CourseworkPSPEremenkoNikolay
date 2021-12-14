module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires sqltool;
    requires javafx.base;
    opens ITassetsEremenko.openjfx to javafx.fxml, javafx.base;
    exports ITassetsEremenko.openjfx;
    exports ITassetsEremenko.model;
    opens ITassetsEremenko.model to javafx.base, javafx.fxml;
    exports ITassetsEremenko.config;
    opens ITassetsEremenko.config to javafx.base, javafx.fxml;
}