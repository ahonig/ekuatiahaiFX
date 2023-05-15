package com.geotechpy.ekuatiahaifx;

import com.geotechpy.ekuatiahaifx.database.DbUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.geotechpy.ekuatiahaifx.utils.Utils.errorMessage;
import static com.geotechpy.ekuatiahaifx.utils.Utils.successMessage;


public class LoginController implements Initializable {
    public DbUtils dbUtils = new DbUtils();
    Robot robot;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btn_iniciar;

    @FXML
    private CheckBox chk_recordar;

    @FXML
    private PasswordField tf_contrasena;

    @FXML
    private TextField tf_usuario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if ( dbUtils.isDbConnected()) {
            System.out.println("Ok");
        }
        try {
            robot = new Robot();
            for (Node o : anchorPane.getChildren()) {
                if (o instanceof TextField) {
                    o.setOnKeyPressed( evt ->{
                        if(evt.getCode().equals(KeyCode.ENTER)){
                            robot.keyPress(KeyEvent.VK_TAB);
                            robot.keyRelease(KeyEvent.VK_TAB);
                        }
                    });
                }
            }
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

    }

    public void login () {


        if (tf_usuario.getText().isEmpty()){
            errorMessage("Por favor completar los campos");
            tf_usuario.requestFocus();
            return;
        }

        if (tf_contrasena.getText().isEmpty()){
            errorMessage("Por favor completar los campos");
            tf_contrasena.requestFocus();
            return;
        }

        try {
            if (dbUtils.isLogin(tf_usuario.getText(), tf_contrasena.getText())) {
                successMessage("Login Ok");
            }
        } catch (SQLException e) {
            errorMessage("Error de conexion. "+e.getMessage());
        }


    }


}