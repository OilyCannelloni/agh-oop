package agh.ics.oop;
import javax.swing.*;

public class BetterMapVisualizer {
    private final IWorldMap map;
    public BetterMapVisualizer(IWorldMap map) {
        this.map = map;
    }


    public void draw(Vector2d bottomLeft, Vector2d topRight) {


        String[] columnNames = new String[topRight.x - bottomLeft.x + 1];
        columnNames[0] = "x/y";
        for (int i = 1; i < columnNames.length; i++) columnNames[i] = String.valueOf(i + bottomLeft.x - 1);


        String[][] contents = new String[topRight.y - bottomLeft.y + 1][topRight.x - bottomLeft.x + 2];
        for (int j = 0; j < contents.length; j++) contents[j][0] = String.valueOf(topRight.y - j);

        for (int j = 0; j < contents.length; j++) {
            for (int i = 1; i < contents[0].length; i++) {
                int map_x = i + bottomLeft.x - 1;
                int map_y = topRight.y - j;
                Object obj = this.map.objectAt(new Vector2d(map_x, map_y));
                if (!(obj instanceof Animal)) contents[j][i] = " ";
                else contents[j][i] = obj.toString();
            }
        }

        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setLayout(null);

        JTable grid = new JTable(contents, columnNames);
        grid.setBounds(100, 100, 100, 100);

        frame.add(grid);
        frame.setVisible(true);
    }
}
