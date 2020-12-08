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
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author melro
 */
public class Admin5Controller implements Initializable {

    @FXML
    private JFXTextField sid;
    @FXML
    private JFXTextField sn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void B(Event event) throws SQLException {
        try {
            String stationid=sid.getText();
            String stationname=sn.getText();
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","RAILWAY","railway");
            String query="insert into station(station_id,station_name) values(?,?)";
            PreparedStatement pre=con.prepareStatement(query);
            pre.setString(1,stationid);
            pre.setString(2,stationname);
            pre.executeQuery();
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Admin5Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void C(ActionEvent event) {
    }

    @FXML
    private void A(ActionEvent event) throws IOException {
        Parent b = FXMLLoader.load(getClass().getResource("Admin2.fxml"));
        Scene c = new Scene(b);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(c);
        window.show();
    }
    
}
