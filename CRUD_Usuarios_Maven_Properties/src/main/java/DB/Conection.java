/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author jgame
 */
public class Conection {
    static public Connection getConection() throws SQLException, FileNotFoundException, IOException{
        Connection con;
        try (InputStream input = new FileInputStream("C:/Users/Jorge/Documents/NetBeansProjects/EjerciciosFormularios/CRUD_Usuarios_Maven_Properties/src/main/java/DB/DBProperties.properties")) {
            
            Properties prop = new Properties();
            prop.load(input);
            
         con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("pass"));
         return con;
        }catch (SQLException e){
            System.out.println(e);
        }catch(IOException e){
            System.out.println(e);
        }
        return null;
    }
}
