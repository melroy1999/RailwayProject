/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwayproject;

import com.jfoenix.controls.JFXButton;
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
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author melro
 */
public class Page16Controller implements Initializable {

    @FXML
    private JFXButton btn;
    @FXML
    private JFXTextField train;
    @FXML
    private Label endp;
    @FXML
    private Label startp;
    String t;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               btn.setVisible(false);
               startp.setVisible(false);
               endp.setVisible(false);
               t=train.getText();
               Duration duration = Duration.seconds(50);
               Duration duration1 = Duration.seconds(5);
//Create new translate transition
               PauseTransition p=new PauseTransition(Duration.millis(1000));
               TranslateTransition transition = new TranslateTransition(duration, btn);
               transition.setNode(btn);
               transition.setByX(-670);
               transition.play();
    }    

    @FXML
    private void goo(ActionEvent event) throws SQLException, ClassNotFoundException {
             String pnr=train.getText();
             int pnrlen=pnr.length();
             String name="";
             if(pnrlen==10)
             {
             String dest="";
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","RAILWAY","railway");
            String query="select p_name,destination from passenger where pnr=?";
            PreparedStatement pre=con.prepareStatement(query);
            pre.setString(1,pnr);
            ResultSet rs=pre.executeQuery();
            while(rs.next())
            {
                name=rs.getString("p_name");
                dest=rs.getString("destination");
            }
            infoBox1("WELCOME \n"+name,null,"Success");
            btn.setVisible(true);
            startp.setVisible(true);
            endp.setVisible(true);
            startp.setText(dest);
            endp.setText("Mangalore");
             }
            else
            {
                   infoBox1("Please enter valid PNR number",null,"Failed"); 
            }
        
        
             
        
    }
    public static void infoBox1(String infoMessage, String headerText, String title)
       {
           Alert alert=new Alert(Alert.AlertType.WARNING);
           alert.setContentText(infoMessage);
           alert.setTitle(title);
           alert.setHeaderText(headerText);
           alert.showAndWait();
           
           
       }

    @FXML
    private void B(ActionEvent event) throws IOException {
        Parent p=FXMLLoader.load(getClass().getResource("Page4.fxml"));
        Scene sc=new Scene(p);
        Stage w=(Stage)((Node)event.getSource()).getScene().getWindow();
        w.setScene(sc);
        w.show();
    }
    
}
