package com.geotechpy.ekuatiahaifx.database;
import com.geotechpy.ekuatiahaifx.models.usuarios;
import com.geotechpy.ekuatiahaifx.utils.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtils {
    Connection connection;
    private String saltString = "!m3c8C9$D$5M";
    public DbUtils() {
        connection = SqliteConnection.Connector();
        if ( connection == null ) {
            System.out.println("No se pudo establecer la conexion.");
            System.exit(1);
        }
    }

    public Connection getConnection(){
        return this.connection;
    }

    public boolean isDbConnected() {
        try{
            return !connection.isClosed();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static String md5(String plainText) throws NoSuchAlgorithmException {
        return md5(null, plainText);
    }

    public static String md5(String salt, String plainText)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        if (salt != null) {
            md.update(salt.getBytes());
        }
        md.update(plainText.getBytes());

        byte byteData[] = md.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return sb.toString();
    }


    public boolean isLogin(String user, String pass) throws SQLException {
        String saltedPassword = null;
        try {
            saltedPassword = md5(md5(saltString), md5(pass));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error a realizar el login: "+e.getMessage());
            return false;
        }
        return usuarios.isLogin(user, saltedPassword);
    }



}
