package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {
    private IWorldMap map;
    private MapBoundary mapBoundary;
    public void init(){
        try {
//            String[] args = getParameters().getRaw().toArray(new String[0]);
            String[] strArgs1 = new String[]{"f", "b" ,"lewo", "backward", "left", "right"};
            String[] strArgs2 = new String[]{"f", "b", "backward", "left", "b", "left", "b"};
            MoveDirection[] directions = new OptionsParser().parse(strArgs2);
            this.mapBoundary = new MapBoundary();
            this.map = new GrassField(10, mapBoundary);
            Vector2d[] positions1 = {new Vector2d(2, 2), new Vector2d(3, 4), new Vector2d(2, 2)};
            Vector2d[] positions2 = {new Vector2d(2, 2), new Vector2d(3, 4)};
            IEngine engine = new SimulationEngine(directions, map, positions2);
            System.out.println(map);
            engine.run();
            System.out.println(map);
        } catch (IllegalArgumentException e){
            System.out.println("Error: " + e.getMessage());
//            System.out.println(e);
//            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);

        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
        MapRenderer renderer = new MapRenderer(gridPane, mapBoundary.lowerLeft() ,mapBoundary.upperRight(), map);
        renderer.render();
    }
}
