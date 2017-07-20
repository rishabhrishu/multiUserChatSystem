/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileWriter;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author RAKA
 */
public class SignUpWindowController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private MenuBar menubar2;

    @FXML
    private CheckBox termsAndCondition;

    @FXML
    private TextField usernameTextField2;

    @FXML
    private PasswordField cpasswordTextField;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private PasswordField passwordTextField2;

    @FXML
    private TextField firstnameTextField;

    @FXML
    private Button signupButton;

    @FXML
    private TextField screennameTextField;

    @FXML
    private ComboBox genderCombo;

    @FXML
    private Text warningText;

    ArrayList<String> gender_list = new ArrayList<String>();
    ResultSet r1;
    String gender;
    PreparedStatement st;
    String insertTableSQL = "INSERT INTO login_test" + " VALUES" + "(?,?,?,?,?,?,?,?,?)";
    ObservableList<String> gender_options = FXCollections.observableArrayList("male", "female");

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        genderCombo.setOnAction((event) -> {

            gender = (String) genderCombo.getSelectionModel().getSelectedItem();
            System.out.println("ComboBox Action (selected: " + gender + ")");
        });
        // 
        genderCombo.setItems(gender_options);
        signupButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String usernameData2 = usernameTextField2.getText();
                String cpasswordData = cpasswordTextField.getText();
                String lastNameData = lastnameTextField.getText();
                String passwordData = passwordTextField2.getText();
                String firstNameData = firstnameTextField.getText();
                String screennameData = screennameTextField.getText();
                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    // Connection con = DriverManager.getConnection("jdbc:mysql://172.26.45.180:3306/login_chat", "admin", "12345678");

                    st = IpController.con.prepareStatement(insertTableSQL);

                    PreparedStatement stt = IpController.con.prepareStatement("select * from login_test where Username = ? ");
                    stt.setString(1, usernameData2);
                    r1 = stt.executeQuery();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    if (!r1.next() && passwordData.equals(cpasswordData) && !screennameData.contains(" ") && !passwordData.equals("") && !firstNameData.equals("") && !screennameData.equals("") && !passwordData.equals("") && !cpasswordData.equals("") && !lastNameData.equals("") && termsAndCondition.isSelected()) {
                        passwordData = Encyption(passwordData);

                        try {
                            st.setString(1, firstNameData);
                            st.setString(2, lastNameData);
                            st.setString(3, usernameData2);
                            st.setString(4, screennameData);
                            st.setString(5, passwordData);
                            st.setString(6, "a.png");
                            st.setString(7, IpController.ipAddressData);
                            st.setString(8, "HI!!!");
                            st.setString(9, gender );

                            st.executeUpdate();

                            FileWriter f = new FileWriter("user.txt");
                            f.write(usernameData2);
                            f.close();

                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginWindow.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            root1.setId("paneLoginWindow");
                            Stage stage = new Stage();

                            stage.resizableProperty().setValue(Boolean.FALSE);
                            Image icon = new Image(getClass().getResourceAsStream("ico.png"));
                            stage.getIcons().add(new Image("ico.png"));
                            stage.setTitle("Swift Chat");
                            Scene scene = new Scene(root1);
                            scene.getStylesheets().addAll(this.getClass().getResource("styleLoginWindow.css").toExternalForm());
                            stage.setScene(scene);
                            stage.show();
                            Stage stage2;
                            stage2 = (Stage) signupButton.getScene().getWindow();
                            stage2.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {
                        usernameTextField2.setText("");
                        cpasswordTextField.setText("");
                        lastnameTextField.setText("");
                        passwordTextField2.setText("");
                        firstnameTextField.setText("");
                        screennameTextField.setText("");
                        warningText.setText("Username not available or password not matched!!");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SignUpWindowController.class.getName()).log(Level.SEVERE, null, ex);
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
