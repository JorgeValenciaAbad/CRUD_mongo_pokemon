package com.mycompany.crudform;

import DB.Conection;
import static DB.Conection.getConection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private TextField user;
    @FXML
    private TextField pass;
    @FXML
    private TextField discount;
    @FXML
    private RadioButton premium;
    @FXML
    private RadioButton noPremium;
    @FXML

    private void addClient() throws SQLException, IOException {
        float discon = Float.parseFloat(discount.getText());
        String sql = "INSERT INTO clients ( email, pass, discount, premium) VALUES ('" + user.getText() + "','" + pass.getText() + "'," + discon/100 + "," + premium.isSelected() + ");";
        try {
            Connection con = Conection.getConection();
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @FXML
    private void findClient() throws IOException {
         String sql = "SELECT * FROM clients WHERE email = '"+user.getText()+"';";
        ResultSet rs = null;
        try{
            Connection con = Conection.getConection();
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
            if (rs.getBoolean("premium")){
                System.out.println("Usuario Registrado es premium");
            }else{
                System.out.println("Usuario Registrado no es premium"); 
            }
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    @FXML
    private void price() throws IOException {
        float totalprice=0;
        String sql = "SELECT * FROM clients;";
        ResultSet rs = null;
        try{
            Connection con = Conection.getConection();
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
            if (rs.getBoolean("premium")){
                totalprice += 35.5 * (1 - rs.getFloat("discount"));

            }else{
                totalprice += 20.5 * (1 - rs.getFloat("discount")); 
            }
            }
            System.out.println(totalprice);
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    @FXML
    private void clean() {
        user.setText("");
        pass.setText("");
        discount.setText("");
    }
    @FXML
    private void exit() {
        System.exit(0);
    }

}
