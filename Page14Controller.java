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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author melro
 */
public class Page14Controller implements Initializable {

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
        // TODO
    }    

    @FXML
    private void A(ActionEvent event) throws IOException {
         Parent b = FXMLLoader.load(getClass().getResource("Page4.fxml"));
        Scene c = new Scene(b);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(c);
        window.show();
    }
    
}
