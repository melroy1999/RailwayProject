package railwayproject;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import static javafx.fxml.FXMLLoader.load;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author melro
 */
public class Page8Controller implements Initializable {

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXTextField age;
    @FXML
    private JFXButton B;
    @FXML
    private JFXRadioButton male;
    @FXML
    private JFXRadioButton female;
    DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy/MM/dd");
    @FXML
    private ToggleGroup gend;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void B(ActionEvent event) throws IOException, SQLException {
            String pname=name.getText();
            String phoneno=phone.getText();
            String p_age=age.getText();
            String gender;
            String val="(0/91)?[7-9][0-9]{9}$";
            Pattern pat=Pattern.compile(val);
            if(pname.isEmpty()&& phoneno.isEmpty() && p_age.isEmpty())
            {
                infoBox("Fill the details of the passenger",null,"Failed");
                
            }
            else if((!(pat.matcher(phoneno).matches())))
            {
                 infoBox("Enter valid phone number",null,"Failed");
            }
//            else if(!p_age.matches("[0-100]"))
//            {
//                infoBox("Enter valid age",null,"Failed");
//            }
            else if(!(male.isSelected())&&(female.isSelected()))
            {
                infoBox("Please Select your gender",null,"Failed");
            }
            else
            {
                if(male.isSelected())
                {
                gender="M";
                }
                else
                {
                gender="F";
                }
            try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","RAILWAY","railway");
            String query="insert into passenger(p_name,age,gender,phone_no) values(?,?,?,?)";
            PreparedStatement pre=con.prepareStatement(query);
            pre.setString(1,pname);
            pre.setString(2,p_age);
            pre.setString(3,gender);
            pre.setString(4,phoneno);
            pre.executeQuery();
            String query1="select pnr from passenger where p_name=? and phone_no=?";
            PreparedStatement prepareStatement=con.prepareStatement(query1);
            prepareStatement.setString(1, pname);
            prepareStatement.setString(2, phoneno);
            ResultSet rs=prepareStatement.executeQuery();
            while(rs.next())
            {
                String p_pnr=rs.getString("pnr");
                infoBox("Passenger added successfully Your PNR is"+" "+p_pnr,null,"Success");
                FXMLLoader Loader=new FXMLLoader();
                Loader.setLocation(getClass().getResource("Page6.fxml"));
                Loader.load();
                Page6Controller display=Loader.getController();
                display.SetText(p_pnr);
                Parent p=Loader.getRoot();
                Scene c=new Scene(p);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(c);
                window.show();     
            }
            }    
         catch (ClassNotFoundException ex) {
            Logger.getLogger(Page8Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }

    @FXML
    private void A(ActionEvent event) throws IOException {
        Parent b = FXMLLoader.load(getClass().getResource("Page4.fxml"));
        Scene c = new Scene(b);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(c);
        window.show();
    }
    public static void infoBox(String infoMessage, String headerText, String title)
       {
           Alert alert=new Alert(Alert.AlertType.WARNING);
           alert.setContentText(infoMessage);
           alert.setTitle(title);
           alert.setHeaderText(headerText);
           alert.showAndWait();
       }
    
}
