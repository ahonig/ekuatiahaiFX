package com.geotechpy.ekuatiahaifx;

import com.geotechpy.ekuatiahaifx.database.DbUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.geotechpy.ekuatiahaifx.utils.Utils.errorMessage;


public class LoginController implements Initializable {
    private final DbUtils dbUtils = new DbUtils();
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

                Stage stage = (Stage) btn_iniciar.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(EkuatiahaiFXApplication.class.getResource("menu-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage = new Stage();
                stage.setTitle("Iniciar");
                Image image = new Image(Objects.requireNonNull(EkuatiahaiFXApplication.class.getResource("favicon-32x32.png")).toString());
                stage.getIcons().add(image);
                stage.setMaximized(true);
                stage.setScene(scene);
                stage.show();
            } else {
                errorMessage("EL usuario y/o la contraseña son incorrectos");
            }
        } catch (SQLException e) {
            errorMessage("Error de conexion. "+e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}