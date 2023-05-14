package com.geotechpy.ekuatiahaifx;

import com.geotechpy.ekuatiahaifx.database.DbUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;



public class LoginController implements Initializable {
    public DbUtils dbUtils = new DbUtils();
    Robot robot;
    @FXML
    private AnchorPane anchorPane;

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
}