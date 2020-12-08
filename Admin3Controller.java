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
public class Admin3Controller implements Initializable {

    @FXML
    private JFXTextField tid;
    @FXML
    private JFXTextField tn;
    @FXML
    private JFXTextField tt;
    @FXML
    private JFXTextField ac;

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
            String trai_id=tid.getText();
            String train_name=tn.getText();
            String trai_type=tt.getText();
            String avai_class=ac.getText();
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con1;
            con1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","RAILWAY","railway");
            String query1="Insert into train1(train_id, t_name, train_type,avail_class) values(?,?,?,?)";
            PreparedStatement prepare=con1.prepareStatement(query1);
            prepare.setString(1,trai_id);
            prepare.setString(2,train_name);
            prepare.setString(3,trai_type);
            prepare.setString(4,avai_class);
            prepare.executeQuery();
            con1.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Admin3Controller.class.getName()).log(Level.SEVERE, null, ex);
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
