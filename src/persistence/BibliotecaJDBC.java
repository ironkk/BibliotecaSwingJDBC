/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Author;
import model.Book;

/**
 *
 * @author dam
 */
public class BibliotecaJDBC {

    private final static String user = "admin";
    private final static String pass = "admin";

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

    public java.util.List<String> AllAuthors(String name) throws SQLException {
        java.util.List<String> authors = new ArrayList<>();

        String query = "select name from author";
        PreparedStatement ps = conexion.prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);
         while (rs.next()) {
            Author p = new Author();
            p.setName(rs.getString("NAME"));
            authors.add(p.getName());
         }
        rs.close();
        ps.close();
        disconnect();
        return authors;

    }

    public java.util.List<String> AllGenre() throws SQLException {
        java.util.List<String> genres = new ArrayList<>();

        String query = "select genre from book";
        PreparedStatement ps = conexion.prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);
        while (rs.next()) {
            Book p = new Book();
            p.setGenre(rs.getString("GENRE"));
            genres.add(p.getGenre());
         }
        
        rs.close();
        ps.close();
        disconnect();
        return genres;

    }
    
       public java.util.List<String> AllBooks() throws SQLException {
       java.util.List<String> books = new ArrayList<>();
       
               String query = "select title from book";
          PreparedStatement ps = conexion.prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);
         while (rs.next()) {
            Book p = new Book();
            p.setTitle(rs.getString("TITLE"));
            books.add(p.getTitle());
         }
        rs.close();
         ps.close();
        disconnect();
        return books;

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

}
