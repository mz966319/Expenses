/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themo;
import java.io.*;
import java.util.Scanner;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.text.DecimalFormat;


/**
 *
 * @author moaaz mahmoud
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private TextField addToTypeListTextfield;
    @FXML private ComboBox typeListComboBox;
    @FXML private CheckBox showDateCheckBox;
    @FXML private HBox dateChoicesHBox;
    @FXML private ComboBox yearComboBox;
    @FXML private ComboBox monthComboBox;
    @FXML private ComboBox dayComboBox;
    @FXML private TextField priceTextfield;
    @FXML private TextArea notesTextArea;
    @FXML private Label ErrorMessageLabel;
    private static final DecimalFormat dollar = new DecimalFormat("0.00");

    



    
    
    @FXML private void AddToTypeListButtonPushed(ActionEvent event) throws IOException{
        try {
            String filename= "typesList.txt";
            FileWriter output = new FileWriter(filename,true); //the true will append the new data
            output.write(addToTypeListTextfield.getText()+"\n");//appends the string to the file
            output.close();
        }
        catch(IOException ioe){
            System.err.println("IOException: " + ioe.getMessage());
        }
        if(!addToTypeListTextfield.getText().equals(""))
            typeListComboBox.getItems().add(addToTypeListTextfield.getText());
        addToTypeListTextfield.clear();
        
    }
    
    @FXML private void showDateCheckBox(ActionEvent event){
        dateChoicesHBox.managedProperty().bind(dateChoicesHBox.visibleProperty());
        if((showDateCheckBox.isSelected()== true)) { //check state of check box
            dateChoicesHBox.setVisible(true);
            dateChoicesHBox.setDisable(false);

            //dateChoicesHBox.show();
        }
        if((showDateCheckBox.isSelected()== false)){
            dateChoicesHBox.setVisible(false);
            dateChoicesHBox.setDisable(true);
        }
    }

    /*this method will add the days based on month*/
    @FXML private void addDaysToDate(ActionEvent event){
        int i=0;
        if(monthComboBox.getValue().toString().equals("December")||monthComboBox.getValue().toString().equals("January")
                ||monthComboBox.getValue().toString().equals("March")||monthComboBox.getValue().toString().equals("May")
                ||monthComboBox.getValue().toString().equals("July")||monthComboBox.getValue().toString().equals("August")
                ||monthComboBox.getValue().toString().equals("October"))
            i=31;
        else if(monthComboBox.getValue().toString().equals("November")||monthComboBox.getValue().toString().equals("September")||
                monthComboBox.getValue().toString().equals("June")||monthComboBox.getValue().toString().equals("April"))
            i=30;
        else
            i=29;
        
        dayComboBox.getItems().clear();  
        for(int k = 1;k<=i;k++)
          dayComboBox.getItems().add(k);  
    }
    
    
    @FXML private void GoBackToHomeButtonPushed(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
        
    }
    
    
    @FXML private void AddToExpencesList(ActionEvent event) throws IOException{
        ErrorMessageLabel.setStyle("-fx-font-weight: unbold; -fx-text-fill: red");
        boolean errors = false;
        String exp = "";
        String errorMessage = "";
        this.ErrorMessageLabel.setText("");

        if(typeListComboBox.getValue()== null){
            errors=true;
            errorMessage+="Error: Choose a type\n";
        }
        //System.out.println(yearComboBox.getValue().toString());
        if((showDateCheckBox.isSelected()== true) &&(yearComboBox.getValue()== null&&monthComboBox.getValue()== null
                &&dayComboBox.getValue()== null)){
            errors=true;
            errorMessage+="Error: Add year, month or day or uncheck \"Add date\" option\n";
        }
        boolean priceTextfieldEntered = true;
        if(priceTextfield.getText().equals("")){
            errors=true;
            errorMessage+="Error: Add Amount of money spent\n";
            priceTextfieldEntered = false;
        }
        try{
            Double.parseDouble(priceTextfield.getText());
        }
        catch(NumberFormatException e){
            if(priceTextfieldEntered)
                errorMessage+="Error: Enter a numerical value in the \"Amount Spent\" field\n";
            errors = true;
        }      
                
            
        if(errors){
            this.ErrorMessageLabel.setText(errorMessage);
            return;
        }
        else{
            
            exp+= (monthComboBox.getValue()==null?"null":monthComboBox.getValue().toString())+"\n";
            exp+= (dayComboBox.getValue()==null?"null":dayComboBox.getValue().toString())+"\n";
            exp+= (yearComboBox.getValue()==null?"null":yearComboBox.getValue().toString())+"\n";
            exp+= typeListComboBox.getValue().toString()+"\n";
            //exp+= priceTextfield.getText().toString();
            exp+= ((String)dollar.format(Double.parseDouble(priceTextfield.getText())));
            try {
                String filename= "Expenses.txt";
                FileWriter output = new FileWriter(filename,true); //the true will append the new data
                output.write(exp+"\n");//appends the string to the file
                output.close();
                ErrorMessageLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: green");
                this.ErrorMessageLabel.setText("Added!");
            }
            catch(IOException ioe){
                System.err.println("IOException: " + ioe.getMessage());
                this.ErrorMessageLabel.setText("Error, Contact Moaaz");
            }
        }
        
    }

    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Scanner inputFile;
        typeListComboBox.getItems().clear();
        showDateCheckBox.setSelected(false);
        dateChoicesHBox.setVisible(false);
        addToTypeListTextfield.setPromptText("Add new types to list");
        typeListComboBox.setPromptText("Type List");
        try {
            inputFile = new Scanner(new File("typesList.txt"));
            while (inputFile.hasNextLine()) {//scan studnets from file
                typeListComboBox.getItems().add(inputFile.nextLine());
            }
            inputFile.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        yearComboBox.getItems().clear();
        monthComboBox.getItems().clear();
        dayComboBox.getItems().clear();
        yearComboBox.setPromptText("Year");
        monthComboBox.setPromptText("Month");
        dayComboBox.setPromptText("Day");

        yearComboBox.getItems().addAll("2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030","[null]");
        monthComboBox.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");
        
        priceTextfield.setPromptText("$$$");
        this.ErrorMessageLabel.setText("");
    }    
    
}
