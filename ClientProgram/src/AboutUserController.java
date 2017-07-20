/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author RAKA
 */

public class AboutUserController implements Initializable {

    @FXML
    private Text genderAns;
    @FXML
    private ImageView profilePic;
    @FXML
    private Text fullNameText;
    @FXML
    private Text statusText;
    String p;
    String f;
    String l;
    String g;
    
String Status;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
          //  Connection con = DriverManager.getConnection("jdbc:mysql://172.26.47.153:3306/login_chat", "admin", "12345678");
            
            
            
            PreparedStatement tt = IpController.con.prepareStatement("select * from login_test WHERE Screenname = ?");
            tt.setString(1,ChatRoomController.newUser);
            
            ResultSet zz=tt.executeQuery();
            if(zz.next()){
                 p = zz.getString("ProfilePic");
              
                  f = zz.getString("First Name"); 
                  l = zz.getString("Last Name");
               Status = zz.getString("Status");
               g = zz.getString("gender");
                  
            
            }
            fullNameText.setText(f+" "+l);
            Image i=new Image(p,true);
            profilePic.setImage(i);
            statusText.setText(Status);
            genderAns.setText(g);
            
            
            
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AboutUserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AboutUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
