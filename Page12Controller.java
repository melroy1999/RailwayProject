/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwayproject;

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
public class Page12Controller implements Initializable { 
    @FXML
    private JFXTextField pnrid;
    @FXML
    private JFXTextField passnameid;
    @FXML
    private JFXTextField ageid;
    @FXML
    private JFXTextField genderid;
    @FXML
    private JFXTextField seattypeid;
    @FXML
    private JFXTextField seatnoid;
    @FXML
    private JFXTextField phoneid;
    @FXML
    private JFXTextField trainid;
    @FXML
    private JFXTextField traveldateid;
    @FXML
    private JFXTextField destid;

    /**
     * Initializes the controller class.
     */
     public void SetTextCancel(String pnr1, String name, String age, String gender, String seattype, String seatno, String phone, String trainid, String traveldate, String dest) {
        this.pnrid.setText(pnr1);
        this.passnameid.setText(name);
        this.ageid.setText(age);
        this.genderid.setText(gender);
        this.seattypeid.setText(seattype);
        this.seatnoid.setText(seatno);
        this.phoneid.setText(phone);
        this.trainid.setText(trainid);
        this.traveldateid.setText(traveldate);
        this.destid.setText(dest);
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       
    } 

    @FXML
    private void A(ActionEvent event) throws SQLException, IOException {
        try {
            infoBox2("Are you sure you want to cancel?",null,"Confirmation");
            String pnr=pnrid.getText();
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","RAILWAY","railway");
            String sql="delete from passenger where pnr=?";
            PreparedStatement pre=conn.prepareStatement(sql);
            pre.setString(1,pnr);
            pre.executeQuery();
            infoBox2("Succesfully deleted passenger with pnr"+" "+pnr,null,"Success");
            Parent b = FXMLLoader.load(getClass().getResource("Page4.fxml"));
            Scene c = new Scene(b);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(c);
            window.show();
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Page12Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
   public static void infoBox2(String infoMessage, String headerText, String title)
       {
           Alert alert=new Alert(Alert.AlertType.WARNING);
           alert.setContentText(infoMessage);
           alert.setTitle(title);
           alert.setHeaderText(headerText);
           alert.showAndWait();
           
           
       }
    
    
}
