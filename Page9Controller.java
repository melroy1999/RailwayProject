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
import java.util.Properties;
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
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author melro
 */
public class Page9Controller implements Initializable {

    @FXML
    private ToggleGroup card;
    @FXML
    private TextField pnrid;
    @FXML
    private TextField payid;
    @FXML
    private JFXTextField fare;
    @FXML
    private JFXTextField seats;
    @FXML
    private JFXTextField discount;
    @FXML
    private JFXTextField total;
    String cost1;
    @FXML
    private JFXButton Pay;
    @FXML
    private RadioButton sbi;
    @FXML
    private RadioButton axis;
//    String gpnr=pnrid.getText();
//    String gpayid=payid.getText();
    String coupon,dis;
  static private String pnr,p_name,age,gender,seat_type,seat_no,phone_no,train_id,travel_date,destination,no_of_seats;
    @FXML
    private TextField cardid;
//    static String a="";
//static String b="";
//static String c="";
//static String d="";
//static String e1="";
//static String f="";
static String mymessage="";
static  int i=100;
static String em="";
static String m="";
    @FXML
    public TextField email;

    
    //static String em="dsilva.leroy10@gmail.com";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
  
    @FXML
    private void Discount50(ActionEvent event) throws SQLException {
        dis="50% Discount";
        String gpnr=pnrid.getText();
        String gpayid=payid.getText();
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","RAILWAY","railway");
            String query="select coupon_applied from success_payment where pnr=? and pay_id=?";
            PreparedStatement pre=con.prepareStatement(query);
            pre.setString(1,gpnr);
            pre.setString(2,gpayid);
            ResultSet rs=pre.executeQuery();
            while(rs.next())
            {
                coupon=rs.getString("coupon_applied");
            }
            if(coupon==null)
            {
                discount.setText(dis);      
                int cost=Integer.parseInt(cost1);
                double total1=cost*0.5;
                String tot=Double.toString(total1);
                total.setText(tot);
            }
            else
            {
                infoBox("Coupon already expired",null,"Failed");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Page9Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
       
//       
    }

    @FXML
    private void Discount20(ActionEvent event) {
        if(sbi.isSelected())
       {
        dis="20% Discount";
       discount.setText(dis);
       int cost=Integer.parseInt(cost1);
       double total1=cost*0.2;
        String tot=Double.toString(total1);
        total.setText(tot);
        }
       else
       {
           infoBox("Please select State Bank Card",null,"Failed");
      }
    }

    @FXML
    public void Payment(ActionEvent event) throws SQLException, IOException {
        String gpnr=pnrid.getText();
        String gpayid=payid.getText();
        String card=cardid.getText();
        if(card.isEmpty())
        {
            infoBox("Please Enter your Card Details",null,"Failed");
        }
        else
        {
            try {
                Class.forName("oracle.jdbc.OracleDriver");
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","RAILWAY","railway");
                String sql="update success_payment set coupon_applied=? where pnr=?";
                PreparedStatement prepare=con.prepareStatement(sql);
                prepare.setString(1,dis);
                prepare.setString(2,gpnr);
                prepare.executeQuery();
                infoBox("Please confirm your payment\n"+" "+"Your PNR is "+gpnr,null,"Confirmation");
                infoBox("Your payment is Successfully with payment ID"+" "+gpayid,null,"Success");
                String query="select p_name,age,gender,seat_type,seat_no,phone_no,train_id,travel_date,destination,no_of_seats from passenger where pnr=?";
                PreparedStatement pre=con.prepareStatement(query);
                pre.setString(1,gpnr);
                ResultSet rs=pre.executeQuery();
                while(rs.next())
                {
                    p_name=rs.getString("p_name");
                    age=rs.getString("age");
                    gender=rs.getString("gender");
                    seat_type=rs.getString("seat_type");
                    seat_no=rs.getString("seat_no");
                    phone_no=rs.getString("phone_no");
                    train_id=rs.getString("train_id");
                    travel_date=rs.getString("travel_date");
                    destination=rs.getString("destination");
                    no_of_seats=rs.getString("no_of_seats");   
                }
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Page9Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            pnr=pnrid.getText();
            String seat=seats.getText();
            FXMLLoader Loader=new FXMLLoader();
            Loader.setLocation(getClass().getResource("Page10.fxml"));
            Loader.load();
            Page10Controller display=Loader.getController();
            display.SetText5(pnr,p_name,age,gender,seat_type,seat_no,phone_no,train_id,travel_date,destination,seat);
            Parent p=Loader.getRoot();
            Scene c=new Scene(p);
            m=email.getText();
            mymessage="\n\nTRAIN TICKET\n\n  "+"NAME: "+p_name.toUpperCase()+"\n\n"+"  PNR: "+pnr+"\n\n"+"  Age: "+age+"\n\n"+"  Gender "+gender+"\n\n"+"  Seat type"+seat_type+"\n\n"+"  Seat no "+seat_no+"\n\n"+" Phone"+phone_no+"\n\n"+"train ID"+train_id+"\n\n"+"destination"+" "+destination+"\n\n";
            sendmail();
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            infoBox("Thank you for Booking with us",null,"Success");
            window.setScene(c);
            window.show();
        }
    }
    public static void sendmail()
    {
        //System.out.println(email.getText());
        em=m;
        System.out.println(em);
        final String username = "railwayproject6167@gmail.com";
        final String password = "melroy15";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); 
        prop.put("mail.debug", "true");
        prop.put("mail.from", "railwayproject6167@gmail.com");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
         Session session = Session.getInstance(prop,new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("railwayproject6167@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(em)
            );
            message.setSubject("TICKET DETAILS");
            message.setText(mymessage);

            Transport.send(message);

            //System.out.println("Done"+ema);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    
    }
    
    public static void infoBox(String infoMessage, String headerText, String title)
       {
           Alert alert=new Alert(Alert.AlertType.WARNING);
           alert.setContentText(infoMessage);
           alert.setTitle(title);
           alert.setHeaderText(headerText);
           alert.showAndWait();
           
           
       }

       public void SetText13(String payid,String pnr,int no_of_seats,String price,String total)
    {
         this.payid.setText(payid);
        this.pnrid.setText(pnr);
        String seat=Integer.toString(no_of_seats);
        this.seats.setText(seat);
        this.fare.setText(price);
        cost1=price;
        this.total.setText(total);   
    }

    @FXML
    private void Discount25(ActionEvent event) {
        if(axis.isSelected())
       {
        dis="25% Discount";
        discount.setText(dis);
        int cost=Integer.parseInt(cost1);
        double total1=cost*0.25;
        String tot=Double.toString(total1);
        total.setText(tot);
        }
       else
       {
           infoBox("Please select Axis Bank Card",null,"Failed");
      }
    }

    @FXML
    private void Cancel(ActionEvent event) throws SQLException, IOException {
            String gpnr=pnrid.getText();
        try {
            infoBox("Are you sure you want to cancel your payment",null,"Confirmation");
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","RAILWAY","railway");
            String query="delete from passenger where pnr=?";
            PreparedStatement pre=con.prepareStatement(query);
            pre.setString(1,gpnr);
            pre.executeQuery();
            Parent b = FXMLLoader.load(getClass().getResource("Page4.fxml"));
             Scene c = new Scene(b);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(c);
            window.show();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Page9Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    
}
