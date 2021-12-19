package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;

public class App extends Application implements IPositionChangeObserver {
    private AbstractWorldMap map;
    private List<String> args;
    private GridPane grid;
    private Thread engineThread;
    private SimulationEngine engine;

    @Override
    public void start(Stage primaryStage) {
        this.initialize();
        this.grid = new GridPane();
        this.grid.setPrefSize(600, 600);
        grid.setGridLinesVisible(true);

        TextField textField = new TextField();

        Button button = new Button();
        button.setText("Start");
        button.setPrefSize(200, 100);
        button.setOnAction((e) -> {
            String value = textField.getText();
            this.engine.setMoves(OptionsParser.parse(List.of(value.split("\\s* \\s*"))));
            this.engineThread = new Thread(this.engine);
            this.engineThread.start();
        });



        HBox controlBox = new HBox(button, textField);

        VBox box = new VBox(grid, controlBox);

        Scene scene = new Scene(box, 600, 1000);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawGrid() {
        Rect2D boundingBox = this.map.getBoundingBox();
        grid.setGridLinesVisible(true);

        int fieldSize = 50;
        for (int i = 1; i <= boundingBox.getDimensions().x; i++) {
            Label label = new Label(Integer.toString(i + boundingBox.lowerLeft.x - 1));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.getColumnConstraints().add(new ColumnConstraints(fieldSize));
            grid.getRowConstraints().add(new RowConstraints(fieldSize));
            grid.add(label, i, 0, 1, 1);
        }

        for (int i = 1; i <= boundingBox.getDimensions().y; i++) {
            Label label = new Label(Integer.toString(boundingBox.upperRight.y - i + 1));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.getColumnConstraints().add(new ColumnConstraints(fieldSize));
            grid.getRowConstraints().add(new RowConstraints(fieldSize));
            grid.add(label, 0, i, 1, 1);
        }
        for (int i = boundingBox.lowerLeft.x; i <= boundingBox.upperRight.x; i++) {
            for (int j = boundingBox.lowerLeft.y; j <= boundingBox.upperRight.y; j++) {
                Vector2d gridXY = this.toGridXY(new Vector2d(i, j));
                IMapElement e = (IMapElement) map.objectAt(new Vector2d(i, j));
                GuiElementBox box = new GuiElementBox(e);
                GridPane.setHalignment(box, HPos.CENTER);
                grid.add(box, gridXY.x, gridXY.y, 1, 1);
            }
        }

    }

    private Vector2d toGridXY(Vector2d mapXY) {
        Rect2D bb = this.map.getBoundingBox();
        Vector2d rel = bb.relative(mapXY);
        return new Vector2d(rel.x + 1, bb.getDimensions().y - rel.y);
    }

    public void initialize(){
        try {
            this.args = getParameters().getRaw();
            MoveDirection[] directions = OptionsParser.parse(this.args).toArray(new MoveDirection[0]);
            this.map = new GrassField(10);
            Vector2d[] positions = {
                    new Vector2d(2,2),
                    new Vector2d(3,4)
            };

            this.engine = new SimulationEngine(this, directions, map, positions, 500);

        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void clearGrid() {
        this.grid.getChildren().clear();
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Platform.runLater(this::clearGrid);
        Platform.runLater(this::drawGrid);
    }

    @Override
    public void positionUpdated(Vector2d position) {
        Platform.runLater(this::clearGrid);
        Platform.runLater(this::drawGrid);
    }
}
