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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
public class AdminController implements Initializable {

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
    private void B(ActionEvent event) throws IOException {
        String user=uid.getText();
        String password=pid.getText();
        String u="admin";
        String p="123456";
        if(user.equals(u) && password.equals(p))
        {
            Parent b = FXMLLoader.load(getClass().getResource("Admin2.fxml"));
            Scene c = new Scene(b);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(c);
            window.show();
        }
    }

    @FXML
    private void A(ActionEvent event) throws IOException {
            Parent b = FXMLLoader.load(getClass().getResource("Page2.fxml"));
            Scene c = new Scene(b);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(c);
            window.show();
    }
    
}
