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
        System.out.println(lowerLeft+", "+upperRight);

        rootPane.getColumnConstraints().add(new ColumnConstraints(FIELD_GROW));
        Label label = new Label("y/x");
        rootPane.add(label, 0,0);
        GridPane.setHalignment(label, HPos.CENTER);
        GridPane.setValignment(label, VPos.CENTER);
        renderHeader();

        for (int row = upperRight.y; row >= lowerLeft.y; row--) {
            label = new Label(String.valueOf(upperRight.y-row+lowerLeft.y));
            rootPane.add(label, 0,row-lowerLeft.y+1);
            GridPane.setHalignment(label, HPos.CENTER);
            GridPane.setValignment(label, VPos.CENTER);
            renderRow(row);
        }
    }

    private void renderHeader(){
        rootPane.getRowConstraints().add(new RowConstraints(FIELD_GROW));
        for (int col = lowerLeft.x; col <= upperRight.x; col++) {
            rootPane.getColumnConstraints().add(new ColumnConstraints(FIELD_GROW));
            Label label = new Label(String.valueOf(col));
            GridPane.setHalignment(label, HPos.CENTER);
            rootPane.add(label, col-lowerLeft.x+1, 0);
        }
    }

    private void renderRow(int row){
        rootPane.getRowConstraints().add(new RowConstraints(FIELD_GROW));
        for (int col = lowerLeft.x; col <= upperRight.x; col++){
            Object object = this.map.objectAt(new Vector2d(col, row));
//            String txt = (object == null ? " " : object.toString());
            Label label = new Label(object == null ? " " : object.toString());
            GridPane.setHalignment(label, HPos.CENTER);
            GridPane.setValignment(label, VPos.CENTER);
            rootPane.add(label, col-lowerLeft.x+1, upperRight.y-row+1);
        }
    }
}