package agh.ics.oop;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class BetterMapVisualizer {
    private final IWorldMap map;
    private final int fieldHeight = 60, fieldWidth = 60, gridWidth, gridHeight, optionsMargin = 100, delay;
    private final Vector2d bottomLeft, topRight;

    private final JFrame frame;
    private JTable grid;

    public BetterMapVisualizer(IWorldMap map, Vector2d bottomLeft, Vector2d topRight, int delay) {
        this.map = map;
        this.gridWidth = topRight.x - bottomLeft.x + 2;
        this.gridHeight = topRight.y - bottomLeft.y + 2;
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
        this.delay = delay;

        this.frame = new JFrame();
        this.frame.setSize(
                (this.fieldWidth + 5)*this.gridWidth,
                (this.fieldHeight + 5)*this.gridHeight + this.optionsMargin
        );
        this.frame.setLayout(null);

        this.grid = null;
    }

    public void draw() {
        String[][] contents = new String[this.gridHeight][this.gridWidth];
        contents[0][0] = "";
        for (int j = 1; j < this.gridHeight; j++) contents[j][0] = String.valueOf(this.topRight.y - (j - 1));
        for (int i = 1; i < this.gridWidth; i++) contents[0][i] = String.valueOf(i + this.bottomLeft.x - 1);

        for (int j = 1; j < this.gridHeight; j++) {
            for (int i = 1; i < this.gridWidth; i++) {
                int map_x = i + this.bottomLeft.x - 1;
                int map_y = this.topRight.y - (j - 1);
                Object obj = this.map.objectAt(new Vector2d(map_x, map_y));
                if (!(obj instanceof IMapElement)) contents[j][i] = " ";
                else contents[j][i] = obj.toString();
            }
        }

        String[] columnNames = new String[this.gridWidth];
        Arrays.fill(columnNames, "");

        if (this.grid != null) this.frame.remove(this.grid);

        this.grid = new JTable(contents, columnNames);
        grid.setBounds(0, 0, this.fieldWidth*this.gridWidth, this.fieldHeight*this.gridHeight);
        grid.setRowHeight(this.fieldHeight);
        grid.setFont(new Font("Ubuntu Mono", Font.PLAIN, (int) (this.fieldHeight*0.8)));

        this.frame.add(grid);
        this.frame.setVisible(true);

        try {
            TimeUnit.MILLISECONDS.sleep(this.delay);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
