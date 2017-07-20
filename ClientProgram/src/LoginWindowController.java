/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.KeyAdapter;
import java.io.FileWriter;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
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
public class LoginWindowController implements Initializable {

    @FXML
    Text usernameText;

    @FXML
    PasswordField passwordTextField;

    @FXML
    public TextField usernameTextField;

    @FXML
    Button loginButton;

    @FXML
    Button signupnowButton;

    @FXML
    Text promptText;

    @FXML
    Text passwordText;

    @FXML
    MenuBar menubar_1;

    @FXML
    private CheckBox termsAndCondition;

    @FXML
    private Text warningText;
    ResultSet r1;
    PreparedStatement st;
    public static String userNameData;

    //private Text username;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /* usernameTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
           // @Override
            //public void keyPressed(KeyEvent e) {
                
           // }

             @Override
             public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.TAB) {
                   
                       // usernameTextField.transferFocus();
                    
                    e.consume();
                } //To change body of generated methods, choose Tools | Templates.
             }
        });*/
        //signup button
        passwordTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {

                    String passwordData = passwordTextField.getText();
                    userNameData = usernameTextField.getText();
                    passwordData = Encyption(passwordData);

                    boolean log = false;
                    try {

                        Class.forName("com.mysql.jdbc.Driver");
                        //Connection con = DriverManager.getConnection("jdbc:mysql://172.26.47.153:3306/login_chat", "admin", "12345678");

                        PreparedStatement stt = IpController.con.prepareStatement("select password from login_test where Username = ?  ");

                        stt.setString(1, userNameData);

                        r1 = stt.executeQuery();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        if (r1.next()) {
                            String s = r1.getString("Password");
                            if (s.equals(passwordData)) {
                                log = true;
                            }

                        }

                        if (log == true) {
                            try {

                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChatRoom.fxml"));
                                Parent root1 = (Parent) fxmlLoader.load();
                                root1.setId("paneChatRoom");
                                Stage stage4 = new Stage();
                                stage4.resizableProperty().setValue(Boolean.FALSE);
                                stage4.getIcons().add(new Image("ico.png"));
                                stage4.setTitle("Swift Chat");
                                Scene scene = new Scene(root1);
                               // scene.getStylesheets().addAll(this.getClass().getResource("styleChatRoom.css").toExternalForm());
                                stage4.setScene(scene);
                                stage4.show();
                                Stage stage5;
                                stage5 = (Stage) signupnowButton.getScene().getWindow();
                                stage5.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            warningText.setText("*Username or Password is incorrect");
                            passwordTextField.setText("");
                            usernameTextField.setText("");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(LoginWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    /*String msg = messageTextField.getText();
                        messageTextField.setText("");
                        try {
                            writer.println(presentScreenName + " :" + msg);
                            writer.flush();

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        } finally {
                            if (msg.equals("/exit")) {
                                logOut();
                            }
                        }*/
                }
            }

            private String Encyption(String password) {
                //ools | Templates.
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

                return sb.toString();
            }
        });

        signupnowButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignUpWindow.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    root1.setId("paneSignupWindow");
                    Stage stage = new Stage();
                    stage.resizableProperty().setValue(Boolean.FALSE);
                    stage.getIcons().add(new Image("ico.png"));
                    stage.setTitle("Swift Chat");
                    Scene scene = new Scene(root1);
                    scene.getStylesheets().addAll(this.getClass().getResource("styleSignupWindow.css").toExternalForm());
                    stage.setScene(scene);
                    stage.show();
                    Stage stage2;
                    stage2 = (Stage) signupnowButton.getScene().getWindow();
                    stage2.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //loginButton

        loginButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                String passwordData = passwordTextField.getText();
                userNameData = usernameTextField.getText();
                passwordData = Encyption(passwordData);

                boolean log = false;
                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    //Connection con = DriverManager.getConnection("jdbc:mysql://172.26.47.153:3306/login_chat", "admin", "12345678");

                    PreparedStatement stt = IpController.con.prepareStatement("select password from login_test where Username = ?  ");

                    stt.setString(1, userNameData);

                    r1 = stt.executeQuery();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (r1.next()) {
                        String s = r1.getString("Password");
                        if (s.equals(passwordData)) {
                            log = true;
                        }

                    }

                    if (log == true) {
                        try {

                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChatRoom.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            root1.setId("paneChatRoom");
                            Stage stage4 = new Stage();
                            stage4.resizableProperty().setValue(Boolean.FALSE);
                            stage4.getIcons().add(new Image("ico.png"));
                            stage4.setTitle("Swift Chat");
                            Scene scene = new Scene(root1);
                            //scene.getStylesheets().addAll(this.getClass().getResource("styleChatRoom.css").toExternalForm());
                            stage4.setScene(scene);
                            stage4.show();
                            Stage stage5;
                            stage5 = (Stage) signupnowButton.getScene().getWindow();
                            stage5.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        warningText.setText("*Username or Password is incorrect");
                        passwordTextField.setText("");
                        usernameTextField.setText("");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginWindowController.class.getName()).log(Level.SEVERE, null, ex);
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
    }

}
