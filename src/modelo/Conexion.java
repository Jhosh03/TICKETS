/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author josh_
 */

public class Conexion {
    private final String base="usuarios";
    private final String user="root";
    private final String password="12345";
    private final String url="jdbc:mysql://192.168.1.120:3306/"+base;
    private Connection con=null;
    
    
    
    public Connection getConexion(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=(Connection) DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No hay conexion a la base de datos");
        }
    return con; 
    }

}
