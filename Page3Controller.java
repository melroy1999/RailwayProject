/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwayproject;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author melro
 */
public class Page3Controller implements Initializable {

    @FXML
    private JFXTextField uid;
    @FXML
    private JFXPasswordField pid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Register(Event event) throws SQLException, IOException {
            String username=uid.getText();
            String password=pid.getText();
            int user=username.length();
            int pass=password.length();
            if(username.isEmpty() && password.isEmpty())
            {
                infoBox1("Please Enter the username and password",null,"Failed");
            }
            else if(username.isEmpty())
            {
                infoBox1("Please Enter username",null,"Failed");
            }
            else if(password.isEmpty())
            {
                infoBox1("Please Enter password",null,"Failed");
            }
            else if(user<5)
            {
                infoBox1("Minumum Characters for username is 5",null,"Failed");
            }
            else if(pass<5)
            {
                infoBox1("Minumum Characters for password is 5",null,"Failed");
            }
            else
            {
            try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","RAILWAY","railway");
            String query="insert into user1(u_id,password) values(?,?)";
            PreparedStatement pre=con.prepareStatement(query);
            pre.setString(1,username);
            pre.setString(2,password);
            pre.executeQuery();
            con.close();
            Parent b = FXMLLoader.load(getClass().getResource("Page2.fxml"));
        Scene c = new Scene(b);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(c);
        window.show();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Page3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    }
    public void A(ActionEvent event) throws Exception
    {
        Parent b = FXMLLoader.load(getClass().getResource("Page2.fxml"));
        Scene c = new Scene(b);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(c);
        window.show();
        
                
    }
    public static void infoBox1(String infoMessage, String headerText, String title)
       {
           Alert alert=new Alert(Alert.AlertType.WARNING);
           alert.setContentText(infoMessage);
           alert.setTitle(title);
           alert.setHeaderText(headerText);
           alert.showAndWait();
           
           
       }
    
}
