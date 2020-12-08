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
public class Admin4Controller implements Initializable {

    @FXML
    private JFXTextField sid;
    @FXML
    private JFXTextField at;
    @FXML
    private JFXTextField tid;
    @FXML
    private JFXTextField dt;
    @FXML
    private JFXTextField ac1;
    @FXML
    private JFXTextField ac2;
    @FXML
    private JFXTextField nonac;

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
            String arrtime=at.getText();
            String departtime=dt.getText();
            String ac1_fare=ac1.getText();
            String ac2_fare=dt.getText();
            String non_ac_sleeper_fare=nonac.getText();
            String trainid=tid.getText();
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","RAILWAY","railway");
            String query="insert into route(arr_time,depart_time,station_id,train_id,ac1_fare,ac2_fare,non_ac_sleeper_fare) values(?,?,?,?,?,?,?)";
            PreparedStatement pre=con.prepareStatement(query);
            pre.setString(1,arrtime);
            pre.setString(2,departtime);
            pre.setString(3,stationid);
            pre.setString(4,trainid);
            pre.setString(5,ac1_fare);
            pre.setString(6,ac2_fare);
            pre.setString(7,non_ac_sleeper_fare);
            pre.executeQuery();
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Admin4Controller.class.getName()).log(Level.SEVERE, null, ex);
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
