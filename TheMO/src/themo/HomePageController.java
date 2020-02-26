/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themo;

import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.exp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author moaaz mahmoud
 */
public class HomePageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private Label timeLabel;
    @FXML private void GoToAddExpensesPageButtonPushed(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
        
    }
    @FXML private void GoToViewTableButtonPushed(ActionEvent event) throws IOException{
        try {
                String filename= "viewTableCmd.txt";
                FileWriter output = new FileWriter(filename); //the true will append the new data
                output.write("all");//appends the string to the file
                output.close();
            }
            catch(IOException ioe){
                System.err.println("IOException: " + ioe.getMessage());
            }
        Parent root = FXMLLoader.load(getClass().getResource("TableView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        System.out.println(dtf.format(now));
        timeLabel.setText(dtf.format(now));// TODO
    }    
    
}
