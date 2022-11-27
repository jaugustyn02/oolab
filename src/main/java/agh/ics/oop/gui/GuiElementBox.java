package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.IOException;

public class GuiElementBox {
    private final VBox box = new VBox();

    public GuiElementBox(IMapElement element) {
        if (element == null) {
            Label label = new Label(" ");
            box.getChildren().add(label);
        } else {
            try (FileInputStream fileInStr = new FileInputStream(element.getImagePath())){
                Image image = new Image(fileInStr);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(20);
                imageView.setFitHeight(20);
                if(element instanceof Animal) {
                    imageView.setFitHeight(23);
                    box.setPadding(new Insets(3,0,0,0));
                }
                box.getChildren().add(imageView);
            } catch (IOException e) {
                System.out.println("Exception: " + e.getMessage());
            }
            Label label = new Label(element.getLabelName());
            box.getChildren().add(label);
            label.setFont(new Font(10));
            box.setAlignment(Pos.CENTER);
        }
    }
    public void renderElement(GridPane rootElement, int x, int y){
        rootElement.add(box, x, y);
    }
}
