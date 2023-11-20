package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private boolean clearScreen = true;
    private double firstNum = 0;
    private double secondNum = 0;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
        stage.setTitle("Calculator");
        stage.getIcons().add(new Image("file:calc.png"));
        
        VBox display = new VBox(10);

        VBox labelContainer = new VBox();
        labelContainer.setAlignment(Pos.CENTER_RIGHT);

        Label labelProgress = new Label("0");
        labelProgress.setMinSize(50, 50);
        labelProgress.setStyle("-fx-font-size: 15px;");
        labelProgress.setAlignment(Pos.CENTER_RIGHT);

        Label labelResult = new Label("0");
        labelResult.setMinSize(50, 50);
        labelResult.setStyle("-fx-font-size: 25px;");
        labelResult.setAlignment(Pos.CENTER_RIGHT);

        VBox.setMargin(labelProgress, new Insets(0, 18, 0, 0));
        VBox.setMargin(labelResult, new Insets(0, 18, 0, 0));
        labelContainer.getChildren().addAll(labelProgress, labelResult);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        display.getChildren().addAll(labelContainer,grid);

        

        scene = new Scene(display, 330, 510);
        stage.setScene(scene);
        stage.show();

        ColumnConstraints col1 = new ColumnConstraints(70);
        ColumnConstraints col2 = new ColumnConstraints(70);
        ColumnConstraints col3 = new ColumnConstraints(70);
        ColumnConstraints col4 = new ColumnConstraints(70);

        RowConstraints row1 = new RowConstraints(70);
        RowConstraints row2 = new RowConstraints(70);
        RowConstraints row3 = new RowConstraints(70);
        RowConstraints row4 = new RowConstraints(70);
        RowConstraints row5 = new RowConstraints(70);

        grid.getColumnConstraints().addAll(col1, col2, col3, col4);
        grid.getRowConstraints().addAll(row1, row2, row3, row4, row5);

        Button[] buttons = new Button[10];

        for (int i = 0; i < buttons.length; i++){
            buttons[i] = createNumberStyle(String.valueOf(i));
        }

        Button comma = createNumberStyle(",");
        Button plus = createOtherButtonStyle("+");
        Button minus = createOtherButtonStyle("-");
        Button divi = createOtherButtonStyle("/");
        Button multi = createOtherButtonStyle("*");
        Button clear = createOtherButtonStyle("c");
        Button equals = createOtherButtonStyle("=");


        grid.add(clear, 2, 0);
        grid.add(plus, 3, 0);
        grid.add(buttons[7], 0, 1);
        grid.add(buttons[8], 1, 1);
        grid.add(buttons[9], 2, 1);
        grid.add(buttons[4], 0, 2);
        grid.add(buttons[5], 1, 2);
        grid.add(buttons[6], 2, 2);
        grid.add(buttons[1], 0, 3);
        grid.add(buttons[2], 1, 3);
        grid.add(buttons[3], 2, 3);
        grid.add(buttons[0], 0, 4, 2, 1);
        grid.add(multi, 3, 1);
        grid.add(minus, 3, 2);
        grid.add(divi, 3, 3);
        grid.add(equals, 3, 4);
        grid.add(comma, 2, 4);

        
        buttons[1].setOnAction(event -> addToResult(labelResult, "1"));
        buttons[2].setOnAction(event -> addToResult(labelResult, "2"));
        buttons[3].setOnAction(event -> addToResult(labelResult, "3"));
        buttons[4].setOnAction(event -> addToResult(labelResult, "4"));
        buttons[5].setOnAction(event -> addToResult(labelResult, "5"));
        buttons[6].setOnAction(event -> addToResult(labelResult, "6"));
        buttons[7].setOnAction(event -> addToResult(labelResult, "7"));
        buttons[8].setOnAction(event -> addToResult(labelResult, "8"));
        buttons[9].setOnAction(event -> addToResult(labelResult, "9"));
        buttons[0].setOnAction(event -> addToResult(labelResult, "0"));

        plus.setOnAction(event -> addOperator(labelResult, labelProgress, "+"));
    }

    private void addToResult(Label labelResult, String number){
        String currentText = labelResult.getText();

        if(clearScreen){
            currentText = "";
            clearScreen = false;

            currentText = currentText + number;
            labelResult.setText(currentText);
        }
        else{
            currentText = currentText + number;
            labelResult.setText(currentText);
        }
    }

    private void addOperator(Label labelResult, Label labelProgress, String operator){
        String currentText = labelResult.getText();
        labelProgress.setText(currentText + " " + operator);
        firstNum = Double.parseDouble(labelResult.getText());

        labelResult.setText(String.valueOf(getResult(operator)));
        clearScreen = true;
    }

    private double getResult(String operator){
        switch (operator) {
            case "+":
                return firstNum + secondNum;
            case "-":
                return firstNum - secondNum;
            case "/":
                return firstNum / secondNum;
            case "*":
                return firstNum * secondNum;
            default:
                return Double.NaN;
        }
    }

    private Button createNumberStyle(String text){
        Button button = new Button(text);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button.setStyle("-fx-font-size: 20px; -fx-background-color: #808080; -fx-border-color: black;");

        return button;
    }
    private Button createOtherButtonStyle(String text){
        Button button = new Button(text);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button.setStyle("-fx-font-size: 20px;");

        return button;
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}