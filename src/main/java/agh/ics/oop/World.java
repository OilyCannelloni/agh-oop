package agh.ics.oop;


public class World {
    public static void main(String[] args) {
        MoveDirection[] directions = OptionsParser.parse(args).toArray(new MoveDirection[0]);
        IWorldMap map = new RectangularMap(5, 5);
        Vector2d[] positions = {
                new Vector2d(2,2),
                new Vector2d(3,4)
        };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        MapVisualizer visualizer = new MapVisualizer(map);
        String frame = visualizer.draw(new Vector2d(0, 0), new Vector2d(4, 4));
        System.out.println(frame);

        BetterMapVisualizer bettervis = new BetterMapVisualizer(map);
        bettervis.draw(new Vector2d(0, 0), new Vector2d(4, 4));
    }
}