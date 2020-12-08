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
import java.sql.ResultSet;
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
public class Page13Controller implements Initializable {

    @FXML
    private JFXTextField pnrid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void A(ActionEvent event) throws SQLException, IOException {
        try {
            String pnr=pnrid.getText();
            int length=pnr.length();
            if(length==10)
            {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","RAILWAY","railway");
            String query="select * from passenger where pnr=?";
            PreparedStatement prepareStatement=con.prepareStatement(query);
            prepareStatement.setString(1, pnr);
            ResultSet rs=prepareStatement.executeQuery();
            while(rs.next())
            {
                FXMLLoader Loader=new FXMLLoader();
                Loader.setLocation(getClass().getResource("Page14.fxml"));
                Loader.load();
                Page14Controller display=Loader.getController();
                display.SetTextCancel(rs.getString("pnr"),rs.getString("p_name"),rs.getString("age"),rs.getString("gender"),rs.getString("seat_type"),rs.getString("seat_no"),rs.getString("phone_no"),rs.getString("train_id"),rs.getString("travel_date"),rs.getString("destination"));
                Parent p=Loader.getRoot();
                Scene c = new Scene(p);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(c);
                window.show();   
            }
            }
            else
            {
                infoBox("Please Enter Valid PNR",null,"Failed");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Page13Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private static void infoBox(String infoMessage, String headerText, String title)
       {
           Alert alert=new Alert(Alert.AlertType.WARNING);
           alert.setContentText(infoMessage);
           alert.setTitle(title);
           alert.setHeaderText(headerText);
           alert.showAndWait();
           
           
       }

    @FXML
    private void B(ActionEvent event) throws IOException {
        Parent b = FXMLLoader.load(getClass().getResource("Page4.fxml"));
        Scene c = new Scene(b);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(c);
        window.show();
    }
    
}
