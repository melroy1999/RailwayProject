/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwayproject;

import com.jfoenix.controls.JFXButton;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author melro
 */
public class Page15Controller implements Initializable {

    @FXML
    private JFXTextField pnrid;
    @FXML
    private TableColumn<Page15a, String> trainid;
    @FXML
    private TableColumn<Page15a, String> arrtime;
    @FXML
    private TableColumn<Page15a, String> departtime;
    @FXML
    private TableColumn<Page15a, String> ac1fare;
    @FXML
    private TableColumn<Page15a, String> ac1seats;
    @FXML
    private TableColumn<Page15a, String> ac2fare;
    @FXML
    private TableColumn<Page15a, String> ac2seats;
    @FXML
    private TableColumn<Page15a, String> nonacfare;
    @FXML
    private TableColumn<Page15a, String> nonacseats;
    @FXML
    private Button B;
    @FXML
    private JFXButton A;
    @FXML
    private ComboBox<String> dest;
    @FXML
    private TableView<Page15a> table;
    String trainno,arrivaltime,departuretime,ac1price,ac1seat,ac2price,ac2seat,nonacprice,nonacseat;
    

    /**
     * Initializes the controller class.
     */
    ObservableList<Page15a> oblist2=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dest.getItems().addAll("Bangalore","Bhatkal","Byndoor","Chennai Central","Coimbatore Junction","Davangere","Hubli Junction","Jammu Tawi","Kacheguda","Kannur","Karwar","Kasaragod","Kottayam","Kozhikode","Kumta","Kundapura","Madgaon","Murdeshwar","Mysore","Nagpur","New Delhi","Panvel","Puducherry","Sakleshpur","Surathkal","Thane","Tiruchchirapali","Trivandrum Cntl","Udupi");
        trainid.setCellValueFactory(new PropertyValueFactory<>("trainno"));
        arrtime.setCellValueFactory(new PropertyValueFactory<>("arrivaltime"));
        departtime.setCellValueFactory(new PropertyValueFactory<>("departuretime"));
        ac1fare.setCellValueFactory(new PropertyValueFactory<>("ac1price"));
        ac1seats.setCellValueFactory(new PropertyValueFactory<>("ac1seat"));
        ac2fare.setCellValueFactory(new PropertyValueFactory<>("ac2price"));
        ac2seats.setCellValueFactory(new PropertyValueFactory<>("ac2seat"));
        nonacfare.setCellValueFactory(new PropertyValueFactory<>("nonacprice"));
        nonacseats.setCellValueFactory(new PropertyValueFactory<>("nonacseat"));
        table.setItems(oblist2);
    }    

    @FXML
    private void A(ActionEvent event) throws IOException {
        Parent p=FXMLLoader.load(getClass().getResource("Page4.fxml"));
        Scene sc=new Scene(p);
        Stage w=(Stage)((Node)event.getSource()).getScene().getWindow();
        w.setScene(sc);
        w.show();
    }

    @FXML
    private void B(ActionEvent event) throws SQLException {
            oblist2.clear();
        try {
            String stationid="";
            String destination=dest.getValue();
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","RAILWAY","railway");
            String query="select station_id from station where station_name=?";
            PreparedStatement pre=con.prepareStatement(query);
            pre.setString(1,destination);
            ResultSet rs=pre.executeQuery();
            while(rs.next())
            {
                stationid=rs.getString("station_id");
            }
            String query1="select R.train_id,R.arr_time,R.depart_time,R.ac1_fare,A.ac1_seats,R.ac2_fare,A.ac2_seats,R.non_ac_sleeper_fare,A.non_ac_seats from route R, avail_seats A where A.train_id=R.train_id and R.station_id=?";
            PreparedStatement prep=con.prepareStatement(query1);
            prep.setString(1,stationid);
            ResultSet rs2=prep.executeQuery();
            while(rs2.next())
            {
                
                oblist2.add(new Page15a(rs2.getString("train_id"),rs2.getString("arr_time"),rs2.getString("depart_time"),rs2.getString("ac1_fare"),rs2.getString("ac1_seats"),rs2.getString("ac2_fare"),rs2.getString("ac2_seats"),rs2.getString("non_ac_sleeper_fare"),rs2.getString("non_ac_seats")));
            }
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Page15Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
