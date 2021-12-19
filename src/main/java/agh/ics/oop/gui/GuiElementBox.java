package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.Grass;
import agh.ics.oop.IMapElement;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class GuiElementBox extends VBox {
    public GuiElementBox(IMapElement element) {
        if (element == null) return;
        ImageView imageView = new ImageView();
        imageView.setImage(new Image(element.getImagePath()));
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);

        String labelText = "x";
        if (element instanceof Grass) labelText = "Trawa";
        else if (element instanceof Animal) labelText = "Z " + element.getPosition();

        Label label = new Label(labelText);

        this.getChildren().addAll(imageView, label);
    }
}
