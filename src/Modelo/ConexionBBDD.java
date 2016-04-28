/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mario
 */
public class ConexionBBDD {
    private static Connection conn = null;

    /**
     * Realiza la conexion con la Base de Datos
     *
     * @return Objeto de la clase Connection con la conexion establecida
     */
    public static Connection getConnection() {
        try {
            if (conn == null) {
                Runtime.getRuntime().addShutdownHook(new MiShDwHook());
                String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost/empresa";
                String usuario = "root";
                String password = "";
                Class.forName(driver);
                conn = DriverManager.getConnection(url, usuario, password);
            }

            return conn;
        } catch (SQLException | ClassNotFoundException ex) {
            return null;
        }

    }
    /*
     * Creamos un hilo para que automaticamente se cierre la conexion de la BD
     */

    static class MiShDwHook extends Thread {

        @Override
        public void run() {
            try {
                Connection conn = ConexionBBDD.getConnection();
                conn.close();
            } catch (SQLException ex) {
                 ex.getErrorCode();
            }
    }
        }
}
