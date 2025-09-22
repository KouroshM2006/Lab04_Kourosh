/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
//github link: https://github.com/KouroshM2006/Lab04_Kourosh
package lab04_kourosh;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author 2440557
 */
public class Lab04_Kourosh extends Application {

    //final values coveref by the company
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
     * method to calculate Incurred and Allowed expenses
     *
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
    private double[] CalcExpenses(int numberOfDays, double airfare, double rentalFees, double milesDriven, double parkingFees, double taxiFees, double registration, double lodging) {

        //calculating total allowed
        double mealCost = numberOfDays * Meal_Per_Day;
        double parkingCost = Math.min(parkingFees, numberOfDays * Parking_Per_Day);
        double TaxiCost = Math.min(taxiFees, numberOfDays * Taxi_Per_Day);
        double lodgingCost = Math.min(lodging, Lodging_Per_Day) * numberOfDays;
        double mileageCost = milesDriven * Per_Mile_Driven;

        double allowed = mealCost + parkingCost + TaxiCost + lodgingCost + mileageCost;
        
        //calculating total incurred cost
        double incurred = airfare + rentalFees + (milesDriven * Per_Mile_Driven) + parkingFees + taxiFees + registration + (lodging * numberOfDays);
        
        return new double[]{incurred, allowed};
    }

    /**
     * method to show an Alert with a specified error message
     *
     * @param errorMessage String
     */
    private void showAlert(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

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
        TextField airfareField = new TextField();

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

        //arrays of labels, text fields
        Label[] labels = {nbDays, airfare, rental, miles, parking, taxi, registration, lodging};
        TextField[] textFields = {nbDaysField, airfareField, rentalField, milesField, parkingField, taxiField, registrationField, lodgingField};

        //inserting labels and text fields into grid pane
        for (int i = 0; i < labels.length; i++) {
            centerGrid.add(labels[i], 0, i * 2);
            centerGrid.add(textFields[i], 0, i * 2 + 1);
        }

        //buttons and message Labels
        Button calculate = new Button("calculate");
        calculate.setDisable(true);
        Button clear = new Button("clear");
        Label expensesIncurred = new Label();
        Label expensesAllowed = new Label();
        Label excessPaid = new Label();
        Label amountSaved = new Label();

        //adding buttons and message labels to bottom grid
        bottomGrid.add(calculate, 0, 0);
        bottomGrid.add(clear, 1, 0);
        bottomGrid.add(expensesIncurred, 0, 1);
        bottomGrid.add(expensesAllowed, 0, 2);
        bottomGrid.add(excessPaid, 0, 3);
        bottomGrid.add(amountSaved, 0, 4);

        //event handler for textfields
        for (TextField field : textFields) {
            field.setOnKeyReleased(e -> {
                if (field.equals(rentalField)) {
                    if (!field.getText().isEmpty()) {
                        milesField.setDisable(true);
                    } else {
                        milesField.setDisable(false);
                    }
                } else if (field.equals(milesField)) {
                    if (!field.getText().isEmpty()) {
                        rentalField.setDisable(true);
                    } else {
                        rentalField.setDisable(false);
                    }
                }

                if (nbDaysField.getText().isEmpty() || lodgingField.getText().isEmpty()) {
                    calculate.setDisable(true);
                } else {
                    calculate.setDisable(false);
                }
            });
        }

        //calculate button event
        calculate.setOnAction(event -> {
            for (TextField field : textFields) {
                if (!field.getText().isEmpty()) {

                }
            }

            try {
                
                
                int days = Integer.parseInt(nbDaysField.getText());
                double airfareAmount = airfareField.getText().isEmpty() ? 0 : Double.parseDouble(airfareField.getText());
                double rentalAmount = rentalField.getText().isEmpty() ? 0 : Double.parseDouble(rentalField.getText());
                double milesAmount = milesField.getText().isEmpty() ? 0 : Double.parseDouble(milesField.getText());
                double parkingAmount = parkingField.getText().isEmpty() ? 0 : Double.parseDouble(parkingField.getText());
                double taxiAmount = taxiField.getText().isEmpty() ? 0 : Double.parseDouble(taxiField.getText());
                double registrationAmount = registrationField.getText().isEmpty() ? 0 : Double.parseDouble(registrationField.getText());
                double lodgingAmount = Double.parseDouble(lodgingField.getText());

                if (days < 0 || airfareAmount < 0 || rentalAmount < 0 || milesAmount < 0 || parkingAmount < 0 || taxiAmount < 0 || registrationAmount < 0 || lodgingAmount < 0) {
                    showAlert("amount invalid");
                    return;
                }

                double[] expenses = CalcExpenses(days, airfareAmount, rentalAmount, milesAmount, parkingAmount, taxiAmount, registrationAmount, lodgingAmount);

                double incurred = expenses[0];
                double allowed = expenses[1];
                double excessAmount = Math.max(0, incurred - allowed);
                double savedAmount = Math.max(0, allowed - incurred);

                expensesIncurred.setText(String.format("Total expenses incurred: %s$", incurred));
                expensesAllowed.setText(String.format("Total allowable expenses: %s$", allowed));
                excessPaid.setText(String.format("Excess to be paid by business person: %s$", excessAmount));
                amountSaved.setText(String.format("Amount saved by business person: %s$", savedAmount));

            } catch (NumberFormatException e) {
                showAlert("please enter a number valuew");
            }
        });

        centerGrid.setAlignment(Pos.CENTER);
        bottomGrid.setAlignment(Pos.CENTER);

        bottomGrid.setHgap(5);

        root.setCenter(centerGrid);
        root.setBottom(bottomGrid);
        root.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

}
