package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.List;

public class App extends Application {
    private Label[][] field;
    private AbstractWorldMap map;
    private List<String> args;
    private final int fieldSize = 30;

    @Override
    public void start(Stage primaryStage) {
        this.init();
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        Rect2D boundingBox = this.map.getBoundingBox();

        for (int i = 1; i <= boundingBox.getDimensions().x; i++) {
            Label label = new Label(Integer.toString(i + boundingBox.lowerLeft.x - 1));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.getColumnConstraints().add(new ColumnConstraints(this.fieldSize));
            grid.getRowConstraints().add(new RowConstraints(this.fieldSize));
            grid.add(label, i, 0, 1, 1);
        }

        for (int i = 1; i <= boundingBox.getDimensions().y; i++) {
            Label label = new Label(Integer.toString(boundingBox.upperRight.y - i + 1));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.getColumnConstraints().add(new ColumnConstraints(this.fieldSize));
            grid.getRowConstraints().add(new RowConstraints(this.fieldSize));
            grid.add(label, 0, i, 1, 1);
        }

        for (int i = boundingBox.lowerLeft.x; i <= boundingBox.upperRight.x; i++) {
            for (int j = boundingBox.lowerLeft.y; j <= boundingBox.upperRight.y; j++) {
                Vector2d gridXY = this.toGridXY(new Vector2d(i, j));
                Object o = map.objectAt(new Vector2d(i, j));
                String s;
                if (o == null) s = ""; else s = o.toString();
                Label label = new Label(s);
                GridPane.setHalignment(label, HPos.CENTER);
                grid.add(label, gridXY.x, gridXY.y, 1, 1);
            }
        }


        Scene scene = new Scene(grid,
                (boundingBox.getDimensions().x + 1) * this.fieldSize,
                (boundingBox.getDimensions().y + 1) * this.fieldSize
        );
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Vector2d toGridXY(Vector2d mapXY) {
        Rect2D bb = this.map.getBoundingBox();
        Vector2d rel = bb.relative(mapXY);
        return new Vector2d(rel.x + 1, bb.getDimensions().y - rel.y);
    }

    @Override
    public void init(){
        try {
            this.args = getParameters().getRaw();
            MoveDirection[] directions = OptionsParser.parse(this.args).toArray(new MoveDirection[0]);
            this.map = new GrassField(10);
            Vector2d[] positions = {
                    new Vector2d(2,2),
                    new Vector2d(3,4)
            };

            SimulationEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();

            System.out.println(map);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
