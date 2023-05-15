package com.geotechpy.ekuatiahaifx.models;

import com.geotechpy.ekuatiahaifx.database.DbUtils;
import com.geotechpy.ekuatiahaifx.utils.Estados;

import java.awt.image.DataBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.geotechpy.ekuatiahaifx.database.DbUtils.md5;

public class usuarios {
    private String usua_codigo;
    private String usua_nombre;
    private String usua_clave;
    private Estados usua_estado;
    private String usua_tipo;

    public String getUsua_codigo() {
        return usua_codigo;
    }

    public void setUsua_codigo(String usua_codigo) {
        this.usua_codigo = usua_codigo;
    }

    public String getUsua_nombre() {
        return usua_nombre;
    }

    public void setUsua_nombre(String usua_nombre) {
        this.usua_nombre = usua_nombre;
    }

    public String getUsua_clave() {
        return usua_clave;
    }

    public void setUsua_clave(String usua_clave) {
        this.usua_clave = usua_clave;
    }

    public Estados getUsua_estado() {
        return usua_estado;
    }

    public void setUsua_estado(Estados usua_estado) {
        this.usua_estado = usua_estado;
    }

    public String getUsua_tipo() {
        return usua_tipo;
    }

    public void setUsua_tipo(String usua_tipo) {
        this.usua_tipo = usua_tipo;
    }

    public static boolean isLogin(String user, String pass) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        DbUtils dbUtils = new DbUtils();
        if (dbUtils.isDbConnected()) {
            connection = dbUtils.getConnection();
        }

        try {
            String query = "SELECT * FROM usuarios WHERE usua_codigo = ? AND usua_clave = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2,pass);
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ){
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }

    }

}
