/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwayproject;

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
public class Page5Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    
    private void A(ActionEvent event) throws IOException {
        Parent p=FXMLLoader.load(getClass().getResource("Page8.fxml"));
        Scene sc=new Scene(p);
        Stage w=(Stage)((Node)event.getSource()).getScene().getWindow();
        w.setScene(sc);
        w.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void B(ActionEvent event) throws IOException {
        Parent p=FXMLLoader.load(getClass().getResource("Page11.fxml"));
        Scene sc=new Scene(p);
        Stage w=(Stage)((Node)event.getSource()).getScene().getWindow();
        w.setScene(sc);
        w.show();
    }

    @FXML
    private void C(ActionEvent event) throws IOException {
        Parent p=FXMLLoader.load(getClass().getResource("Page13.fxml"));
        Scene sc=new Scene(p);
        Stage w=(Stage)((Node)event.getSource()).getScene().getWindow();
        w.setScene(sc);
        w.show();
    }

    @FXML
    private void D(ActionEvent event) throws IOException {
        Parent p=FXMLLoader.load(getClass().getResource("Page15.fxml"));
        Scene sc=new Scene(p);
        Stage w=(Stage)((Node)event.getSource()).getScene().getWindow();
        w.setScene(sc);
        w.show();
    }

    @FXML
    private void E(ActionEvent event) throws IOException {
        Parent p=FXMLLoader.load(getClass().getResource("Page16.fxml"));
        Scene sc=new Scene(p);
        Stage w=(Stage)((Node)event.getSource()).getScene().getWindow();
        w.setScene(sc);
        w.show();
        
    }
    
}
