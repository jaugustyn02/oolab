package agh.ics.oop.gui;

import agh.ics.oop.IWorldMap;
import agh.ics.oop.Vector2d;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;


public class MapRenderer {
    private final GridPane rootPane;
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;
    private final IWorldMap map;
    private final int FIELD_GROW = 25;

    public MapRenderer(GridPane rootPane, Vector2d lowerLeft, Vector2d upperRight, IWorldMap map) {
        this.rootPane = rootPane;
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
        this.map = map;
    }

    public void render(){

        System.out.println(,upperRight);
        for (int i = upperRight.y + 1; i >= lowerLeft.y; i--) {

            if (i == upperRight.y + 1) {
                rootPane.getColumnConstraints().add(new ColumnConstraints(FIELD_GROW));
                Label label = new Label("y/x");
                rootPane.add(label, 0,0);
                renderHeader();

            } else {
                Label label = new Label(String.valueOf(upperRight.y-i));
                rootPane.add(label, lowerLeft.y,i+1);

                GridPane.setHalignment(label, HPos.CENTER);
                GridPane.setValignment(label, VPos.CENTER);
                renderRow( i);
            }
        }
    }

    public void renderHeader(){
        rootPane.getRowConstraints().add(new RowConstraints(FIELD_GROW));
        for (int j = lowerLeft.x; j < upperRight.x + 1; j++) {
            rootPane.getColumnConstraints().add(new ColumnConstraints(FIELD_GROW));
            Label label = new Label(String.valueOf(j));
            GridPane.setHalignment(label, HPos.CENTER);
            rootPane.add(label, j+1, 0);
        }
    }
    public void renderRow( int i){
        rootPane.getRowConstraints().add(new RowConstraints(FIELD_GROW));
        for (int j = lowerLeft.x ; j < upperRight.x + 1; j++) {
            Object object = this.map.objectAt(new Vector2d(j, i));
            String txt = " ";
            if (object != null) {
                txt = object.toString();
            }
            Label label = new Label(txt);

            GridPane.setHalignment(label, HPos.CENTER);
            GridPane.setValignment(label, VPos.CENTER);
            rootPane.add(label, j+1, upperRight.y-i+1);
        }
    }
}