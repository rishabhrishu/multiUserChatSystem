/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author RAKA
 */
public class test extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            Image icon = new Image(getClass().getResourceAsStream("ico.png"));
            primaryStage.getIcons().add(new Image("ico.png"));
            primaryStage.setTitle("Swift Chat");
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Ip.fxml"));
            root.setId("paneIpWindow");
            primaryStage.resizableProperty().setValue(Boolean.FALSE);
            Scene scene = new Scene(root);
            scene.getStylesheets().addAll(this.getClass().getResource("styleIpWindow.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
