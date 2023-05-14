module com.geotechpy.ekuatiahaifx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires java.desktop;

    opens com.geotechpy.ekuatiahaifx to javafx.fxml;
    exports com.geotechpy.ekuatiahaifx;
}