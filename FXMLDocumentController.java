/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwayproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author melro
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
     @FXML
    private StackPane rootpane;
    
    public void A(ActionEvent event) throws Exception
    {
        makefadeout();
        
        
                
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void makefadeout() {
        FadeTransition fd = new FadeTransition();
        fd.setDuration(Duration.millis(1000));
        fd.setNode(rootpane);
        fd.setFromValue(1);
        fd.setToValue(0);
        fd.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                loadNextScene();
                
            }

           
        }
        );
        fd.play();
    }
     private void loadNextScene() {
                try {
                    Parent b =(StackPane) FXMLLoader.load(getClass().getResource("Page2.fxml"));
                    Scene c = new Scene(b);
                    Stage window = (Stage)rootpane.getScene().getWindow();
                    window.setScene(c);
                    window.show();
                } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
            }
    
}
