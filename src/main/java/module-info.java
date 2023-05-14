module com.geotechpy.ekuatiahaifx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.geotechpy.ekuatiahaifx to javafx.fxml;
    exports com.geotechpy.ekuatiahaifx;
}