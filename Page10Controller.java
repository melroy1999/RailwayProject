/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwayproject;

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
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author melro
 */
public class Page10Controller implements Initializable {

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
    @FXML
    private JFXTextField noseats;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
      public void SetText5(String pnr,String p_name,String age,String gender,String seat_type,String seat_no,String phone_no,String train_id,String travel_date,String destination,String no_of_seats)
    {
        this.pnrid.setText(pnr);
        this.passnameid.setText(p_name);
         this.ageid.setText(age);
         this.genderid.setText(gender);
         this.seattypeid.setText(seat_type);
         this.seatnoid.setText(seat_no);
        this.phoneid.setText(phone_no);
        this.trainid.setText(train_id);
        this.traveldateid.setText(travel_date);
        this.destid.setText(destination);
        this.noseats.setText(no_of_seats);
        
       
        
    }

    @FXML
    private void A(ActionEvent event) throws IOException {
        Parent b = FXMLLoader.load(getClass().getResource("Page4.fxml"));
        Scene c = new Scene(b);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(c);
        window.show();
    }
    private static void infoBox(String infoMessage, String headerText, String title)
       {
           Alert alert=new Alert(Alert.AlertType.WARNING);
           alert.setContentText(infoMessage);
           alert.setTitle(title);
           alert.setHeaderText(headerText);
           alert.showAndWait();
           
           
       }
    
}
