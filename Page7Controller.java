/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwayproject;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author melro
 */
public class Page7Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    String trainid;
    String pnr;
    String stationid;
    int no_of_seats;
    String non_ac_sleeper;
    String ac1_fare;
    String ac2_fare;
    String total;
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image1a;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image2a;
    @FXML
    private ImageView sleepera;
    @FXML
    private ImageView sleeper;
    String payid;
    int seat;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
   @FXML
    private void offf(MouseEvent event) {
          image2.setVisible(false);
          image2a.setVisible(false);
         
    }

    @FXML
    private void onnn(MouseEvent event) {
          image2.setVisible(true);
        image2a.setVisible(true);
       
    }

    @FXML
    private void of1ac(MouseEvent event) {
        image1.setVisible(false);
          image1a.setVisible(false);
    }

    @FXML
    private void on1ac(MouseEvent event) {
        image1.setVisible(true);
        image1a.setVisible(true);
    }

    @FXML
    private void ofsleep(MouseEvent event) {
        sleeper.setVisible(false);
        sleepera.setVisible(false);
    }

    @FXML
    private void onsleep(MouseEvent event) {
        sleeper.setVisible(true);
        sleepera.setVisible(true);
    }
       
    @FXML
    private void A(ActionEvent event) throws SQLException, IOException {
        try {
            Random rand=new Random();
            int seatno=rand.nextInt(100);
            String s1="AC";
            String s2=Integer.toString(seatno);
            String s3=s1+s2;
            String seattype="1AC";
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","RAILWAY","railway");
            String query="select ac1_seats from avail_seats where train_id=?";
            PreparedStatement pre=con.prepareStatement(query);
            pre.setString(1,trainid);
            ResultSet rs=pre.executeQuery();
            while(rs.next())
            {
                String ac1seat=rs.getString("ac1_seats");
                int s=Integer.parseInt(ac1seat);
                s=s-no_of_seats;
                String ac1app=Integer.toString(s);
                String query1="update avail_seats set ac1_seats=? where train_id=?";
                PreparedStatement prep=con.prepareStatement(query1);
                prep.setString(1,ac1app);
                prep.setString(2,trainid);
                prep.executeQuery();
                String seats=Integer.toString(no_of_seats);
                String query2="update passenger set seat_type=?, seat_no=?,no_of_seats=? where pnr=?";
                PreparedStatement prepare=con.prepareStatement(query2);
                prepare.setString(1, seattype);
                prepare.setString(2,s3);
                prepare.setString(3,seats);
                prepare.setString(4,pnr);
                prepare.executeQuery();
                
            }
            String sql="select ac1_fare from route where train_id=? and station_id=?";
            PreparedStatement prepare=con.prepareStatement(sql);
            prepare.setString(1, trainid);
            prepare.setString(2,stationid);
            ResultSet rs2=prepare.executeQuery();
            while(rs2.next())
            {
                ac1_fare=rs2.getString("ac1_fare");
            }
                int price=Integer.parseInt(ac1_fare);
                int pe=Integer.parseInt(pnr);
                String sql1="insert into success_payment (pnr,price,no_of_seats) values(?,?,?)";
                PreparedStatement prep=con.prepareStatement(sql1);
                prep.setInt(1,pe);
                prep.setInt(2,price);
                prep.setInt(3,no_of_seats);
                prep.executeQuery();
                String sql2="select pay_id from success_payment where pnr=?";
                PreparedStatement prepa=con.prepareStatement(sql2);
                prepa.setString(1,pnr);
                ResultSet rs1=prepa.executeQuery();
                while(rs1.next())
                {
                    
                    payid=rs1.getString("pay_id");
                    
                }
                CallableStatement call=con.prepareCall("{call tot}");
                call.execute();
                String sql3="select total_price from success_payment where pnr=?";
                PreparedStatement prepare1=con.prepareStatement(sql3);
                prepare1.setString(1,pnr);
                ResultSet rs3=prepare1.executeQuery();
                while(rs3.next())
                {
                    total=rs3.getString("total_price");
                }
                FXMLLoader Loader=new FXMLLoader();
            Loader.setLocation(getClass().getResource("Page9.fxml"));
            Loader.load();
            Page9Controller display=Loader.getController();
            display.SetText13(payid,pnr,no_of_seats,ac1_fare,total);
            Parent p=Loader.getRoot();
            Scene c=new Scene(p);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(c);
            window.show();
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Page7Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void B(ActionEvent event) throws SQLException, IOException {
       
        try {
            Random rand=new Random();
            int seatno=rand.nextInt(100);
            String s1="SC";
            String s2=Integer.toString(seatno);
            String s3=s1+s2;
            String seattype="2AC";
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","RAILWAY","railway");
            String query="select ac2_seats from avail_seats where train_id=?";
            PreparedStatement pre=con.prepareStatement(query);
            pre.setString(1,trainid);
            ResultSet rs=pre.executeQuery();
            while(rs.next())
            {
                String ac2seat=rs.getString("ac2_seats");
                int s=Integer.parseInt(ac2seat);
                s=s-no_of_seats;
                String ac2app=Integer.toString(s);
                String query1="update avail_seats set ac2_seats=? where train_id=?";
                PreparedStatement prep=con.prepareStatement(query1);
                prep.setString(1,ac2app);
                prep.setString(2,trainid);
                prep.executeQuery();
                String seats=Integer.toString(no_of_seats);
                String query2="update passenger set seat_type=?, seat_no=?,no_of_seats=? where pnr=?";
                PreparedStatement prepare=con.prepareStatement(query2);
                prepare.setString(1, seattype);
                prepare.setString(2,s3);
                prepare.setString(3,seats);
                prepare.setString(4,pnr);
                prepare.executeQuery();
                
            }
            String sql="select ac2_fare from route where train_id=? and station_id=?";
            PreparedStatement prepare=con.prepareStatement(sql);
            prepare.setString(1, trainid);
            prepare.setString(2,stationid);
            ResultSet rs2=prepare.executeQuery();
            while(rs2.next())
            {
                ac2_fare=rs2.getString("ac2_fare");
            }
            int price=Integer.parseInt(ac2_fare);
            int pe=Integer.parseInt(pnr);
            String sql1="insert into success_payment (pnr,price,no_of_seats) values(?,?,?)";
            PreparedStatement prep=con.prepareStatement(sql1);
            prep.setInt(1,pe);
            prep.setInt(2,price);
            prep.setInt(3,no_of_seats);
            prep.executeQuery();
            String sql2="select pay_id from success_payment where pnr=?";
            PreparedStatement prepa=con.prepareStatement(sql2);
            prepa.setString(1,pnr);
            ResultSet rs1=prepa.executeQuery();
            while(rs1.next())
            {
                
                payid=rs1.getString("pay_id");
                
            }
            CallableStatement call=con.prepareCall("{call tot}");
            call.execute();
            String sql3="select total_price from success_payment where pnr=?";
            PreparedStatement prepare1=con.prepareStatement(sql3);
            prepare1.setString(1,pnr);
            ResultSet rs3=prepare1.executeQuery();
            while(rs3.next())
            {
                    total=rs3.getString("total_price");
            }
            FXMLLoader Loader=new FXMLLoader();
            Loader.setLocation(getClass().getResource("Page9.fxml"));
            Loader.load();
            Page9Controller display=Loader.getController();
            display.SetText13(payid,pnr,no_of_seats,ac2_fare,total);
            Parent p=Loader.getRoot();
            Scene c=new Scene(p);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(c);
            window.show();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Page7Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void C(ActionEvent event) throws SQLException, IOException {
        try {
            Random rand=new Random();
            int seatno=rand.nextInt(100);
            String s1="NAC";
            String s2=Integer.toString(seatno);
            String s3=s1+s2;
            String seattype="NAC";
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","RAILWAY","railway");
            String query="select non_ac_seats from avail_seats where train_id=?";
            PreparedStatement pre=con.prepareStatement(query);
            pre.setString(1,trainid);
            ResultSet rs=pre.executeQuery();
            while(rs.next())
            {
                String nonacseat=rs.getString("non_ac_seats");
                int s=Integer.parseInt(nonacseat);
                s=s-no_of_seats;
                String nonacapp=Integer.toString(s);
                String query1="update avail_seats set non_ac_seats=? where train_id=?";
                PreparedStatement prep=con.prepareStatement(query1);
                prep.setString(1,nonacapp);
                prep.setString(2,trainid);
                prep.executeQuery();
                String seats=Integer.toString(no_of_seats);
                String query2="update passenger set seat_type=?, seat_no=?,no_of_seats=? where pnr=?";
                PreparedStatement prepare=con.prepareStatement(query2);
                prepare.setString(1, seattype);
                prepare.setString(2,s3);
                prepare.setString(3,seats);
                prepare.setString(4,pnr);
                prepare.executeQuery();
                
            }
            String sql="select non_ac_sleeper_fare from route where train_id=? and station_id=?";
            PreparedStatement prepare=con.prepareStatement(sql);
            prepare.setString(1, trainid);
            prepare.setString(2,stationid);
            ResultSet rs2=prepare.executeQuery();
            while(rs2.next())
            {
                non_ac_sleeper=rs2.getString("non_ac_sleeper_fare");
            }
                int price=Integer.parseInt(non_ac_sleeper);
                int pe=Integer.parseInt(pnr);
                String sql1="insert into success_payment (pnr,price,no_of_seats) values(?,?,?)";
                PreparedStatement prep=con.prepareStatement(sql1);
                prep.setInt(1,pe);
                prep.setInt(2,price);
                prep.setInt(3,no_of_seats);
                prep.executeQuery();
                String sql2="select pay_id from success_payment where pnr=?";
                PreparedStatement prepa=con.prepareStatement(sql2);
                prepa.setString(1,pnr);
                ResultSet rs1=prepa.executeQuery();
                while(rs1.next())
                {
                    
                    payid=rs1.getString("pay_id");
                    
                }
                CallableStatement call=con.prepareCall("{call tot}");
                call.execute();
                String sql3="select total_price from success_payment where pnr=?";
                PreparedStatement prepare1=con.prepareStatement(sql3);
                prepare1.setString(1,pnr);
                ResultSet rs3=prepare1.executeQuery();
                while(rs3.next())
                {
                    total=rs3.getString("total_price");
                }
                FXMLLoader Loader=new FXMLLoader();
                Loader.setLocation(getClass().getResource("Page9.fxml"));
                Loader.load();
                Page9Controller display=Loader.getController();
                display.SetText13(payid,pnr,no_of_seats,non_ac_sleeper,total);
                Parent p=Loader.getRoot();
                Scene c=new Scene(p);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(c);
                window.show();
                
                
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Page7Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void SetText2(String trainid,String pnr,int no_of_seats,String stationid)
    {
        this.trainid=trainid;
        this.pnr=pnr;
        this.no_of_seats=no_of_seats;
        this.stationid=stationid;
    
    }  
    
}
