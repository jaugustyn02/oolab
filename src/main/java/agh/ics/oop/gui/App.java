package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class App extends Application {
//    // temporary
//    private MoveDirection[] directions;
//
//    public void init(){
//        try {
//            String[] args = getParameters().getRaw().toArray(new String[0]);
//            directions = new OptionsParser().parse(args);
//        } catch (IllegalArgumentException e){
//            System.out.println("Exception: " + e.getMessage());
//        }
//    }

    @Override
    public void start(Stage primaryStage){
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);

        Button startButton = new Button();
        startButton.setText("Start");

        final TextField textField = new TextField();
        textField.setMinWidth(200);

        HBox hBox = new HBox(10);
        hBox.getChildren().add(textField);
        hBox.getChildren().add(startButton);
        VBox vBox = new VBox(1);
        vBox.getChildren().add(gridPane);
        vBox.getChildren().add(hBox);

        Scene scene = new Scene(vBox, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        try {
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            MapBoundary mapBoundary = new MapBoundary();
            IWorldMap map = new GrassField(10, mapBoundary);
            MapRenderer renderer = new MapRenderer(gridPane, map);

            final SimulationEngine engine = new SimulationEngine(map, positions, renderer);
            engine.setMoveDelay(500);
            Thread startThread = new Thread(engine);
            startThread.start();

            startButton.setOnAction((event) -> {
                Thread engineThread = new Thread(engine);
                String textFieldText = textField.getText();
                String[] args = textFieldText.split(" ");
                MoveDirection[] directions;
                try {
                    directions = new OptionsParser().parse(args);
                } catch (IllegalArgumentException ex){
                    directions = new MoveDirection[]{};
                    System.out.println("Exception: " + ex.getMessage());
                }
                engine.setMoveDirections(directions);
                engineThread.start();

            });
            System.out.println(map);
        } catch (FileNotFoundException e){
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
