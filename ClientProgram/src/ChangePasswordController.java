/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RAKA
 */
public class ChangePasswordController implements Initializable {

    @FXML
    private PasswordField newPassEntry;
    @FXML
    private PasswordField confirmPassEntry;
    @FXML
    private Button changePassButton;
    @FXML
    private Text promptText;
    String s;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            FileReader f=null;
            File user=new File("user.txt");
            f = new FileReader(user);
            BufferedReader read=new BufferedReader(f);
            s=read.readLine();
            // TODO
            changePassButton.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    String passData = newPassEntry.getText();
                    String confirmPass = confirmPassEntry.getText();
                    if (passData.equals(confirmPass) && !passData.equals("")) {
                        try {
                            
                            
                            
                            try {

                        Class.forName("com.mysql.jdbc.Driver");
                       // Connection con = DriverManager.getConnection("jdbc:mysql://172.26.45.180:3306/login_chat", "admin", "12345678");

                

                        PreparedStatement stt = IpController.con.prepareStatement("UPDATE login_test SET Password= ? WHERE Username = ?");
                        stt.setString(1, Encyption(passData) );
                        stt.setString(2, LoginWindowController.userNameData);
                        stt.executeUpdate();
                        
                        
                         
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                            
                            
                            
                            Stage stage2;
                            stage2 = (Stage) changePassButton.getScene().getWindow();
                            stage2.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        newPassEntry.setText("");
                        confirmPassEntry.setText("");
                        promptText.setText("Password should be same!!");
                    }
                }

                 private String Encyption(String password) {
                String algorithm = "SHA";

        byte[] plainText = password.getBytes();

        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        md.reset();
        md.update(plainText);
        byte[] encodedPassword = md.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encodedPassword.length; i++) {
            if ((encodedPassword[i] & 0xff) < 0x10) {
                sb.append("0");
            }

            sb.append(Long.toString(encodedPassword[i] & 0xff, 16));
        }

        return sb.toString(); //To change body of generated methods, choose Tools | Templates.
            }
            });
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
