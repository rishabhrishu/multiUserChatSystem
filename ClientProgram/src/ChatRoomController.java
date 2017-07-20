
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
import javafx.scene.input.KeyEvent;
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
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.*;
import java.io.*;
import java.sql.SQLException;
import java.util.StringTokenizer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.WindowEvent;
import static javax.management.Query.value;

/**
 * FXML Controller class
 *
 * @author RAKA
 */
public class ChatRoomController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea chatTextArea;

    @FXML
    private MenuBar menuBar3;

    @FXML
    private Text screenNameShow;

    @FXML
    private ImageView profileImage;

    @FXML
    private Text nameShow;

    @FXML
    private TextField messageTextField;

    @FXML
    private Button sendButton;

    @FXML
    private Button changepassButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Text userOnlineText;

    @FXML
    private ListView<Text> userOnlineList;

    @FXML
    private Button statusChangeButton;

    @FXML
    private TextField StatusTextField;
    Stage stage;

    private ObservableList<Text> buttons = FXCollections.observableArrayList();
    public static int vips;

    @FXML
    Scene sc;
    Image i;
    String s;
    String presentName;
    String presentScreenName;
    String s1, ip, s2;
    String fin = "a.png";
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;
    String msgGot;
    Thread t;
    Image x;
    Thread checkPicThread;
    String Status;
    public static String newUser;
    // MediaPlayer mediaPlayer;

    public void addUser(String s) {
        Text b = new Text(s);
        buttons.add(b);

        b.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newUser = b.getText();

                try {

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AboutUser.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    root1.setId("paneAboutUser");
                    stage = new Stage();
                    stage.resizableProperty().setValue(Boolean.FALSE);
                    stage.getIcons().add(new Image("ico.png"));
                    stage.setTitle("Swift Chat");
                    Scene scene = new Scene(root1);
                    scene.getStylesheets().addAll(this.getClass().getResource("styleAboutUser.css").toExternalForm());
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        b.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public class IncomingReader implements Runnable {

        String listOfUsers = "a";

        public void run() {
            try {
                while ((msgGot = reader.readLine()) != null) {
                    if (msgGot.startsWith("#ListOfOnlineUsers ")) {
                        if (!listOfUsers.equals(msgGot)) {
                            listOfUsers = msgGot;
                            Platform.runLater(new Runnable() {
                                public void run() {
                                    buttons.clear();
                                    StringTokenizer st = new StringTokenizer(msgGot.substring(18), " ");
                                    while (st.hasMoreTokens()) {
                                        addUser(st.nextToken());
                                    }
                                }
                            });
                        }

                    } else {
                        chatTextArea.appendText(msgGot);
                        chatTextArea.appendText("\n");
                        URL resource = getClass().getResource("come.mp3");
                        Media media = new Media(resource.toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(media);
                        mediaPlayer.play();
                    }
                }
            } catch (Exception ex) {
                //ex.printStackTrace();
            }
        }

    }

    public class CheckProPic implements Runnable {

        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);

                    Class.forName("com.mysql.jdbc.Driver");
                    PreparedStatement tt = IpController.con.prepareStatement("select * from login_test WHERE Username = ?");
                    tt.setString(1, LoginWindowController.userNameData);

                    ResultSet zz = tt.executeQuery();
                    if (zz.next()) {
                        s2 = zz.getString("ProfilePic");
                    }
                    if (!s2.equals(fin)) {
                        x = new Image(s2, true);

                        profileImage.setImage(x);
                        fin = s2;
                    }

                } catch (InterruptedException ex) {
                    Logger.getLogger(ChatRoomController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ChatRoomController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ChatRoomController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        buttons.clear();
        userOnlineList.setItems(buttons);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            PreparedStatement stt = IpController.con.prepareStatement("select * from login_test where Username = ? ");
            stt.setString(1, LoginWindowController.userNameData);
            ResultSet r1 = stt.executeQuery();
            if (r1.next()) {

                presentName = r1.getString("First Name");//fn
                presentScreenName = r1.getString("Screenname");//sn
                ip = r1.getString("IP");
                Status = r1.getString("Status");
                StatusTextField.setText(Status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setUpNetwork();
        t = new Thread(new IncomingReader());
        t.start();
        checkPicThread = new Thread(new CheckProPic());
        checkPicThread.start();
        chatTextArea.setText("Welcome to Swift Chat . Type /help for list of commands\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        profileImage.setImage(i);

        profileImage.setOnMouseClicked(event -> {
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProfileSelect.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                root1.setId("paneProfileSelect");
                Stage stage = new Stage();
                stage.resizableProperty().setValue(Boolean.FALSE);
                stage.getIcons().add(new Image("ico.png"));
                stage.setTitle("Swift Chat");
                Scene scene = new Scene(root1);
                scene.getStylesheets().addAll(this.getClass().getResource("styleProfileSelect.css").toExternalForm());
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        nameShow.setText(presentName);
        screenNameShow.setText(presentScreenName);
        writer.println(presentScreenName + " :" + "/ServerCheck");
        writer.flush();

        statusChangeButton.setOnAction(new EventHandler<ActionEvent>() {//send button action listener
            public void handle(ActionEvent event) {

                try {
                    String status = StatusTextField.getText();
                    Class.forName("com.mysql.jdbc.Driver");
                    PreparedStatement stt = IpController.con.prepareStatement("UPDATE login_test SET STATUS= ? WHERE Username = ?");
                    stt.setString(1, status);
                    stt.setString(2, LoginWindowController.userNameData);
                    stt.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(ChatRoomController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ChatRoomController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    logOut(1);
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
                    stage2 = (Stage) logoutButton.getScene().getWindow();
                    stage2.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        changepassButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangePassword.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    root1.setId("paneChangePassword");
                    Stage stage3 = new Stage();
                    stage3.resizableProperty().setValue(Boolean.FALSE);
                    stage3.getIcons().add(new Image("ico.png"));
                    stage3.setTitle("Swift Chat");
                    Scene scene = new Scene(root1);
                    scene.getStylesheets().addAll(this.getClass().getResource("styleChangePassword.css").toExternalForm());
                    stage3.setScene(scene);
                    stage3.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        sendButton.setOnAction(new EventHandler<ActionEvent>() {//send button action listener
            public void handle(ActionEvent event) {
                if (messageTextField.getText() != null && !messageTextField.getText().trim().isEmpty()) {
                    String msg = messageTextField.getText();
                    messageTextField.setText("");
                    try {
                        writer.println(presentScreenName + " :" + msg);
                        writer.flush();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        if (msg.equals("/exit")) {
                            logOut();
                            System.exit(0);
                        }
                    }
                }

            }
        });

        messageTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    if (messageTextField.getText() != null && !messageTextField.getText().trim().isEmpty()) {
                        String msg = messageTextField.getText();
                        messageTextField.setText("");
                        try {
                            writer.println(presentScreenName + " :" + msg);
                            writer.flush();

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        } finally {
                            if (msg.equals("/exit")) {
                                logOut();
                                System.exit(0);
                            }
                        }
                    }

                }
            }
        });
    }

    public void setUpNetwork() {
        try {
            socket = new Socket(IpController.ipAddressData, 5000);
            InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(socket.getOutputStream());
            writer.println(presentScreenName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void logOut() {
        try {
            Thread thd = Thread.currentThread();
            chatTextArea.appendText("Good Bye " + presentScreenName);
            socket.close();
            Thread.sleep(1000);
            thd.interrupt();
        } catch (Exception ex) {
            System.out.println("Good Bye " + presentScreenName);
        }
    }

    public void logOut(int i) {
        try {
            writer.println(presentScreenName + " :/exit");
            writer.flush();
            Thread thd = Thread.currentThread();
            chatTextArea.appendText("Good Bye " + presentScreenName);
            socket.close();
            Thread.sleep(1000);
            thd.interrupt();
        } catch (Exception ex) {
            System.out.println("Good Bye " + presentScreenName);
        }
    }
    


}
