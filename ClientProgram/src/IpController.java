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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author RAKA
 */
public class IpController implements Initializable {

    @FXML
    private Text ipAddressText;

    @FXML
    private TextField ipAddressEntry;

    @FXML
    private Button newPortButton;
    String s;
    int flag = 0;
    ResultSet r1;
    PreparedStatement st;
    public static String ipAddressData;
    public static Connection con;
    String insertTableSQL = "INSERT INTO login_test" + " VALUES" + "(?,?,?,?,?,?,?)";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        ipAddressEntry.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        flag = 0;
                        ipAddressData = ipAddressEntry.getText();

                        try {

                            Class.forName("com.mysql.jdbc.Driver");
                            con = DriverManager.getConnection("jdbc:mysql://" + ipAddressData + "/login_chat", "admin", "12345678");

                            st = con.prepareStatement(insertTableSQL);

                            PreparedStatement stt = con.prepareStatement("UPDATE login_test SET IP= ? WHERE Username = ?");
                            stt.setString(1, ipAddressData);
                            stt.setString(2, LoginWindowController.userNameData);
                            stt.executeUpdate();
   
                        } catch (Exception e) {
                            flag = 1;
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ip.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            root1.setId("paneIpWindow");
                            Stage stage = new Stage();
                            
                            stage.resizableProperty().setValue(Boolean.FALSE);
                            stage.getIcons().add(new Image("ico.png"));
                            stage.setTitle("Swift Chat");
                            Scene scene = new Scene(root1);
                            scene.getStylesheets().addAll(this.getClass().getResource("styleIpWindow.css").toExternalForm());
                            stage.setScene(scene);
                            stage.show();
                            Stage stage2;
                            stage2 = (Stage) newPortButton.getScene().getWindow();
                            stage2.close();
                        }

                        if (flag == 0) {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginWindow.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            root1.setId("paneLoginWindow");
                            Stage stage = new Stage();
                            stage.resizableProperty().setValue(Boolean.FALSE);
                            stage.getIcons().add(new Image("ico.png"));
                            stage.setTitle("Swift Chat");
                            Scene scene = new Scene(root1);
                            scene.getStylesheets().addAll(this.getClass().getResource("styleLoginWindow.css").toExternalForm());
                            stage.setScene(scene);
                            stage.show();
                            Stage stage2;
                            stage2 = (Stage) newPortButton.getScene().getWindow();
                            stage2.close();
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(IpController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        newPortButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                try {
                    flag = 0;
                    ipAddressData = ipAddressEntry.getText();

                    try {

                        Class.forName("com.mysql.jdbc.Driver");
                        con = DriverManager.getConnection("jdbc:mysql://" + ipAddressData + "/login_chat", "admin", "12345678");

                        st = con.prepareStatement(insertTableSQL);

                        PreparedStatement stt = con.prepareStatement("UPDATE login_test SET IP= ? WHERE Username = ?");
                        stt.setString(1, ipAddressData);
                        stt.setString(2, LoginWindowController.userNameData);
                        stt.executeUpdate();

                    } catch (Exception e) {
                        flag = 1;
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ip.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        root1.setId("paneIpWindow");
                        Stage stage = new Stage();
                        stage.resizableProperty().setValue(Boolean.FALSE);
                        stage.getIcons().add(new Image("ico.png"));
                        stage.setTitle("Swift Chat");
                        Scene scene = new Scene(root1);
                        scene.getStylesheets().addAll(this.getClass().getResource("styleIpWindow.css").toExternalForm());
                        stage.setScene(scene);
                        stage.show();
                        Stage stage2;
                        stage2 = (Stage) newPortButton.getScene().getWindow();
                        stage2.close();
                    }

                    if (flag == 0) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginWindow.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        root1.setId("paneLoginWindow");
                        Stage stage = new Stage();
                        
                        stage.resizableProperty().setValue(Boolean.FALSE);
                        stage.getIcons().add(new Image("ico.png"));
                        stage.setTitle("Swift Chat");
                        Scene scene = new Scene(root1);
                        scene.getStylesheets().addAll(this.getClass().getResource("styleLoginWindow.css").toExternalForm());
                        stage.setScene(scene);
                        stage.show();
                        Stage stage2;
                        stage2 = (Stage) newPortButton.getScene().getWindow();
                        stage2.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(IpController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }

}
