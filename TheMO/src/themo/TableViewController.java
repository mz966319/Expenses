/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themo;

import java.io.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import static javafx.scene.control.ScrollPane.ScrollBarPolicy.AS_NEEDED;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author moaaz mahmoud
 */
public class TableViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private TableView<Expense> tableView;
    @FXML private TableColumn<Expense,String> typeColumn;
    @FXML private TableColumn<Expense,Double> priceColumn;
    //@FXML private TableColumn<Expense,String> dateColumn;
    @FXML private TableColumn<Expense,String> monthColumn;
    @FXML private TableColumn<Expense,String> dayColumn;
    @FXML private TableColumn<Expense,String> yearColumn;
    //@FXML private TableColumn<Expense,String> notesColumn;  
    @FXML private Label statsLabel;
    @FXML private ComboBox searchByComboBox;
    //@FXML private T
    
    @FXML private void getSearchByCmd(ActionEvent event){
        
    
    
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        monthColumn.setCellValueFactory(new PropertyValueFactory<>("month"));
        dayColumn.setCellValueFactory(new PropertyValueFactory<>("day"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("item"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        //notesColumn.setCellValueFactory(new PropertyValueFactory<Expense,String>("notes"));
        searchByComboBox.getItems().clear();
        searchByComboBox.getItems().add("Type");
        String cmp="";
        try{
            Scanner inputFile = new Scanner( new File("viewTableCmd.txt"));
            cmp=inputFile.nextLine();
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(cmp.equals("all"))
            tableView.setItems(getAllExpenses());
    }  
    
    public ObservableList<Expense> getAllExpenses(){
        ObservableList<Expense> expenses = FXCollections.observableArrayList();
        try{
            Scanner inputFile = new Scanner( new File("Expenses.txt"));
            ExpenseList sepexpenses = new ExpenseList();
            while (inputFile.hasNext())
            {
                sepexpenses.add(new Expense(inputFile.nextLine(),inputFile.nextLine(),inputFile.nextLine(),inputFile.nextLine(), Double.parseDouble(inputFile.nextLine())));

            }
            inputFile.close();

            expenses= sepexpenses.getObservableList();
            statsLabel.setText("The max expense was: $" + sepexpenses.maxExpense()+"\nThe min expense was: $" + sepexpenses.minExpense()+"\nThe avg expense was: $" + sepexpenses.avgExpense());
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return expenses;
    }
    public ObservableList<Expense> getByType(String type){
        ObservableList<Expense> expenses = FXCollections.observableArrayList();
        try{
            Scanner inputFile = new Scanner( new File("Expenses.txt"));
            ExpenseList sepexpenses = new ExpenseList();
            while (inputFile.hasNext())
            {
                sepexpenses.add(new Expense(inputFile.nextLine(),inputFile.nextLine(),inputFile.nextLine(),inputFile.nextLine(), Double.parseDouble(inputFile.nextLine())));

            }
            inputFile.close();

            expenses= sepexpenses.getExpensesByType(type);
            //statsLabel.setText("The max expense was: $" + sepexpenses.maxExpense()+"\nThe min expense was: $" + sepexpenses.minExpense()+"\nThe avg expense was: $" + sepexpenses.avgExpense());
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return expenses;
    }
    
    
    
    
    
  
    @FXML private void GoBackToHomeButtonPushed(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }
    
}
