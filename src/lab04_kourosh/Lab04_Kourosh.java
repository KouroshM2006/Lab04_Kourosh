/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab04_kourosh;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author 2440557
 */
public class Lab04_Kourosh extends Application{
    private final double Meal_Per_Day = 37;
    private final double Parking_Per_Day = 10;
    private final double Taxi_Per_Day = 20;
    private final double Lodging_Per_Day = 95;
    private final double Per_Mile_Driven = 0.27;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * method to calculate Incurred and Allowed  expenses 
     * @param numberOfDays
     * @param airfare
     * @param rentalFees
     * @param milesdriven
     * @param parkingFees
     * @param taxiFees
     * @param registration
     * @param lodging
     * @return an array of double containing both expenses
     */
    private double[] CalcExpenses(int numberOfDays, double airfare, double rentalFees, double milesdriven, double parkingFees, double taxiFees, double registration, double lodging) {
        double Incurred = (double) (airfare + rentalFees + (milesdriven * Per_Mile_Driven) + parkingFees + taxiFees + registration + (lodging * lodging));
        double Allowed = (double) ((numberOfDays * Meal_Per_Day) + (numberOfDays * Parking_Per_Day) + (numberOfDays * Taxi_Per_Day) + (numberOfDays * Lodging_Per_Day) + (milesdriven * Per_Mile_Driven));
        
        return new double[]{Incurred, Allowed};
    };
    
    
    @Override
    public void start(Stage stage) {
        //initializing GridPanes and BorderPane
        GridPane centerGrid = new GridPane();
        GridPane bottomGrid = new GridPane();
        BorderPane root = new BorderPane();
        
        //adding nodes to gridpane
        Label nbDays = new Label("Number of days on the trip");
        TextField nbDaysField = new TextField();
        
        Label airfare = new Label("Amount of airfare, if any");
        TextField airfareField  = new TextField();
        
        Label rental = new Label("Amount of car rental fees, if any");
        TextField rentalField = new TextField();
        
        Label miles = new Label("Number of miles driven, if a private vehicle was used");
        TextField milesField = new TextField();
        
        Label parking = new Label("Amount of parking fees, if any");
        TextField parkingField = new TextField();
        
        Label taxi = new Label("Amount of taxi charges, if any");
        TextField taxiField = new TextField();
        
        Label registration = new Label("Conference or seminar registration fees, if any");
        TextField registrationField = new TextField();
        
        Label lodging = new Label("Lodging charges, per night");
        TextField lodgingField = new TextField();
        
        //arrays of labels and text fields
        Label[] labels = {nbDays, airfare, rental, miles, parking, taxi, registration, lodging};
        TextField[] textFields = {nbDaysField, airfareField, rentalField, milesField, parkingField, taxiField, registrationField, lodgingField};
        
        
        //inserting labels and text fields into grid pane
        for (int i = 0; i < labels.length; i++) {
           centerGrid.add(labels[i], 0, i * 2);
           centerGrid.add(textFields[i], 0, i * 2 + 1);
        }
        
        
        
        root.setCenter(centerGrid);
        root.setPadding(new Insets(25, 25, 25, 25));
        
        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.show();
    }
    
}
