/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwayproject;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author melro
 */
public class Page6Controller implements Initializable {

    @FXML
    private JFXComboBox<String> destinationselect;
    @FXML
    private JFXDatePicker dateselect;
    @FXML
    private TableView<Page6a> table;
    @FXML
    private TableColumn<Page6a,String> tno;
    @FXML
    private TableColumn<Page6a, String> trainname;
    @FXML
    private TableColumn<Page6a, String> traintype;
    @FXML
    private TableColumn<Page6a, String> stationname;
    @FXML
    private TableColumn<Page6a, String> ac1fare;
    @FXML
    private TableColumn<Page6a, String> ac2fare;
    @FXML
    private TableColumn<Page6a, String> nonacsleeper;
    @FXML
    private TableColumn<Page6a, Button> action;
    @FXML
    private JFXTextField pnr;
    @FXML
    private JFXComboBox<String> seats;
    
    String stationid;
    
    private void A(ActionEvent event) throws IOException {
        Parent b = FXMLLoader.load(getClass().getResource("Page8.fxml"));
        Scene c = new Scene(b);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(c);
        window.show();
    }
    

    /**
     * Initializes the controller class.
     */
    ObservableList<Page6a> oblist=FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        destinationselect.getItems().addAll("Bangalore","Bhatkal","Byndoor","Chennai Central","Coimbatore Junction","Davangere","Hubli Junction","Jammu Tawi","Kacheguda","Kannur","Karwar","Kasaragod","Kottayam","Kozhikode","Kumta","Kundapura","Madgaon","Murdeshwar","Mysore","Nagpur","New Delhi","Panvel","Puducherry","Sakleshpur","Surathkal","Thane","Tiruchchirapali","Trivandrum Cntl"

        ,"Udupi");
        seats.getItems().addAll("1","2","3","4","5","6","7","8","9","10");
        
        tno.setCellValueFactory(new PropertyValueFactory<>("trainid"));
        trainname.setCellValueFactory(new PropertyValueFactory<>("tname"));
        traintype.setCellValueFactory(new PropertyValueFactory<>("availclass"));
        stationname.setCellValueFactory(new PropertyValueFactory<>("stationname"));
        ac1fare.setCellValueFactory(new PropertyValueFactory<>("ac1fare"));
        ac2fare.setCellValueFactory(new PropertyValueFactory<>("ac2fare"));
        nonacsleeper.setCellValueFactory(new PropertyValueFactory<>("nonac"));
        action.setCellValueFactory(new PropertyValueFactory<>("action"));
        table.setItems(oblist);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.getSelectionModel().setCellSelectionEnabled(true);
        
        
    }  

    
    @FXML
    private void B(Event event) throws SQLException, IOException {
             oblist.clear();
        try {
            String destination=destinationselect.getValue();
            LocalDate date=dateselect.getValue();
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","RAILWAY","railway");
            String sql2="select station_id from station where station_name=?";
            PreparedStatement prepare=conn.prepareStatement(sql2);
            prepare.setString(1,destination);
            ResultSet rs1=prepare.executeQuery();
            while(rs1.next())
            {
                stationid=rs1.getString("station_id");
            
            }
            String sql="select A.train_id,A.t_name,A.avail_class,C.station_name,B.ac1_fare,B.ac2_fare,B.non_ac_sleeper_fare from train1 A,route B,station C where A.train_id=B.train_id AND B.station_id=C.station_id AND C.station_name=?";
            PreparedStatement prepareStatement=conn.prepareStatement(sql);
            prepareStatement.setString(1,destination);
            ResultSet rs=prepareStatement.executeQuery();
            while(rs.next())
            {
                 oblist.add(new Page6a(rs.getString("train_id"),rs.getString("t_name"),rs.getString("avail_class"),rs.getString("station_name"),rs.getString("ac1_fare"),rs.getString("ac2_fare"),rs.getString("non_ac_sleeper_fare")));  
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Page6Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    @FXML
    private void C(MouseEvent event) throws IOException, SQLException  
    {
        String pnrd=pnr.getText();
        LocalDate date=dateselect.getValue();
        String date1=date.toString();
        String seat=seats.getValue();
        int no_of_seats=Integer.parseInt(seat);
        String destination=destinationselect.getValue();
        TablePosition tableposition=table.getSelectionModel().getSelectedCells().get(0);
        int row = tableposition.getRow();
        Page6a item=table.getItems().get(row);
        TableColumn tablecolumn=tableposition.getTableColumn();
        String data=(String) tablecolumn.getCellObservableValue(item).getValue();
        int len=data.length();
        if(len==5)
        {
            try {
                Class.forName("oracle.jdbc.OracleDriver");
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","RAILWAY","railway");
                String query="update passenger set train_id=?, travel_date=?, destination=? where pnr=?";
                PreparedStatement pre=con.prepareStatement(query);
                pre.setString(1,data);
                pre.setString(2,date1);
                pre.setString(3,destination);
                pre.setString(4,pnrd);
                pre.executeQuery();
                infoBox1("Booking Successfull",null,"Success");
                FXMLLoader Loader=new FXMLLoader();
                Loader.setLocation(getClass().getResource("Page7.fxml"));
                Loader.load();
                Page7Controller display=Loader.getController();
                display.SetText2(data,pnrd,no_of_seats,stationid);
                Parent p=Loader.getRoot();
                Scene c = new Scene(p);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(c);
                window.show();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Page6Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            infoBox1("Please click on the train id",null,"Failed");
        }   
    }
    public static void infoBox1(String infoMessage, String headerText, String title)
       {
           Alert alert=new Alert(Alert.AlertType.WARNING);
           alert.setContentText(infoMessage);
           alert.setTitle(title);
           alert.setHeaderText(headerText);
           alert.showAndWait();
           
           
       }
    public void SetText(String pnr)
    {
        this.pnr.setText(pnr);
    
    }
    
    
    
}
