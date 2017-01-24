/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

/**
 *
 * @author dam
 */
public class BibliotecaJDBC {
    
      private Connection conexion;
      //connect bbdd
      public void connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/library";
        String usr = "root";
        String pass = "";
        conexion = DriverManager.getConnection(url, usr, pass);
    }

     //disconnect bbdd
    public void disconnect() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
    
    // método que verifica si un usr y pass son válidos
    // devuelve true si existe un usr con esos datos y false en caso contrario
    public boolean verificar(String usr, String pass) throws SQLException {
        // conectamos con la bbdd
        connect();
        boolean ok = false;
        String query = "select * from user where username=? and password=?";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setString(1, usr);
        ps.setString(2, pass);
        ResultSet rs = ps.executeQuery();
        // si el resultado tiene algo
        if (rs.next()) {
            ok = true;
        } else {
            ok = false;
        }
        rs.close();
        ps.close();
        disconnect();
        return ok;
    }
    public boolean verificarAdmin(String usr, String pass) throws SQLException{
        connect();
        
        if(usr == ){
            
        }
        
    }
}
