/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RAKA
 */
public class ProfileSelectController implements Initializable {

    @FXML
    private ImageView pic1;
    @FXML
    private ImageView pic2;
    @FXML
    private ImageView pic3;
    @FXML
    private ImageView pic4;
    @FXML
    private ImageView pic5;
    @FXML
    private ImageView pic6;
    @FXML
    private ImageView pic7;
    @FXML
    private ImageView pic8;
    @FXML
    private ImageView pic9;
    @FXML
    private ImageView pic10;
    @FXML
    private ImageView pic11;
    @FXML
    private ImageView pic12;
    @FXML
    private ImageView pic13;
    @FXML
    private ImageView pic14;
    @FXML
    private ImageView pic15;
    @FXML
    private ImageView pic16;
    @FXML
    private ImageView pic17;
    @FXML
    private ImageView pic18;
    @FXML
    private ImageView pic19;
    @FXML
    private ImageView pic20;
    @FXML
    private Text profilePrompt;
    
    String profilePic;
    String s;
    ResultSet r1;
       PreparedStatement st;
       String insertTableSQL = "INSERT INTO login_test" + " VALUES" + "(?,?,?,?,?,?,?)";

    /**
     * Initializes the controller class.
     *
     * @param url
     */

    public void select() {
        try {
            
            
            try {

                        Class.forName("com.mysql.jdbc.Driver");
                        //Connectio con = DriverManager.getConnection("jdbc:mysql://172.26.47.153:3306/login_chat", "admin", "12345678");

                        st = IpController.con.prepareStatement(insertTableSQL);

                        PreparedStatement stt = IpController.con.prepareStatement("UPDATE login_test SET ProfilePic= ? WHERE Username = ?");
                        stt.setString(1, profilePic );
                        stt.setString(2, LoginWindowController.userNameData);
                        stt.executeUpdate();   
                         
                        
                        
                        
                        
                         
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            
            
            
            
           
            Stage stage2;
            stage2 = (Stage) pic1.getScene().getWindow();
            stage2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // System.out.println(LoginWindowController.userNameData);
       
            // TODO
            pic1.setImage(new Image("a.png", true));
            pic2.setImage(new Image("/b.png", true));
            pic3.setImage(new Image("/c.png", true));
            pic4.setImage(new Image("/d.png", true));
            pic5.setImage(new Image("/e.png", true));
            pic6.setImage(new Image("/f.png", true));
            pic7.setImage(new Image("/g.png", true));
            pic8.setImage(new Image("/h.png", true));
            pic9.setImage(new Image("/i.png", true));
            pic10.setImage(new Image("/j.png", true));
            pic11.setImage(new Image("/k.png", true));
            pic12.setImage(new Image("/l.png", true));
            pic13.setImage(new Image("/m.png", true));
            pic14.setImage(new Image("/n.png", true));
            pic15.setImage(new Image("/o.png", true));
            pic16.setImage(new Image("/p.png", true));
            pic17.setImage(new Image("/q.png", true));
            pic18.setImage(new Image("/r.png", true));
            pic19.setImage(new Image("/s.png", true));
            pic20.setImage(new Image("/t.png", true));
            pic1.setOnMouseClicked(event -> {
                profilePic = "a.png";
                
            });
            pic2.setOnMouseClicked(event -> {
                profilePic = "b.png";
                this.select();
            });
            pic3.setOnMouseClicked(event -> {
                profilePic = "c.png";
                this.select();
            });
            pic4.setOnMouseClicked(event -> {
                profilePic = "d.png";
                this.select();
            });
            pic5.setOnMouseClicked(event -> {
                profilePic = "e.png";
                this.select();
            });
            pic6.setOnMouseClicked(event -> {
                profilePic = "f.png";
                this.select();
            });
            pic7.setOnMouseClicked(event -> {
                profilePic = "g.png";
                this.select();
            });
            pic8.setOnMouseClicked(event -> {
                profilePic = "h.png";
                this.select();
            });
            pic9.setOnMouseClicked(event -> {
                profilePic = "i.png";
                this.select();
            });
            pic10.setOnMouseClicked(event -> {
                profilePic = "j.png";
                this.select();
            });
            pic11.setOnMouseClicked(event -> {
                profilePic = "k.png";
                this.select();
            });
            pic12.setOnMouseClicked(event -> {
                profilePic = "l.png";
                this.select();
            });
            pic13.setOnMouseClicked(event -> {
                profilePic = "m.png";
                this.select();
            });
            pic14.setOnMouseClicked(event -> {
                profilePic = "n.png";
                this.select();
            });
            pic15.setOnMouseClicked(event -> {
                profilePic = "o.png";
                this.select();
            });
            pic16.setOnMouseClicked(event -> {
                profilePic = "p.png";
                this.select();
            });
            pic17.setOnMouseClicked(event -> {
                profilePic = "q.png";
                this.select();
            });
            pic18.setOnMouseClicked(event -> {
                profilePic = "r.png";
                this.select();
            });
            pic19.setOnMouseClicked(event -> {
                profilePic = "s.png";
                this.select();
            });
            pic20.setOnMouseClicked(event -> {
                profilePic = "t.png";
                this.select();
            });
        }

       
    }


