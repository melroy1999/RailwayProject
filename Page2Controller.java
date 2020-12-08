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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author melro
 */
public class Page2Controller implements Initializable {

    @FXML
    private JFXTextField uid;
    @FXML
    private JFXPasswordField pid;
    @FXML
    private StackPane rootpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rootpane.setOpacity(0);
        makefadetransition();
        // TODO
    }    

    @FXML
    private void Login(Event event) throws SQLException, IOException {
        try {
            String user=uid.getText();
            String pass=pid.getText();
            
            
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","RAILWAY","railway");
            String sql="select * from user1 where u_id= ? and password= ? ";
            PreparedStatement prepareStatement=conn.prepareStatement(sql);
            prepareStatement.setString(1,user);
            prepareStatement.setString(2,pass);
            ResultSet rs=prepareStatement.executeQuery();
            if(!rs.next())
            {
                infoBox("Invalid Credentails",null,"Failed");
            }
            else
            {
                infoBox("Login Successfull",null,"Success");
                Parent d = FXMLLoader.load(getClass().getResource("Page4.fxml"));
                Scene e = new Scene(d);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(e);
                window.show();
            }  
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Page2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
    public static void infoBox(String infoMessage, String headerText, String title)
       {
           Alert alert=new Alert(AlertType.WARNING);
           alert.setContentText(infoMessage);
           alert.setTitle(title);
           alert.setHeaderText(headerText);
           alert.showAndWait();
       }

    @FXML
    private void Create(Event event) throws IOException {
        Parent b = FXMLLoader.load(getClass().getResource("Page3.fxml"));
        Scene c = new Scene(b);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(c);
        window.show(); 
    }
    @FXML
    private void A(Event event) throws IOException {
        Parent b = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        Scene c = new Scene(b);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(c);
        window.show(); 
    }
    private void makefadetransition(){
        FadeTransition fd=new FadeTransition();
        fd.setDuration(Duration.millis(1000));
        fd.setNode(rootpane);
        fd.setFromValue(0);
        fd.setToValue(1);
        fd.play();
    }
}
