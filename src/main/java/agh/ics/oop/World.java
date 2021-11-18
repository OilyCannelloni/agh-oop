package agh.ics.oop;


public class World {
    public static void main(String[] args) {
//        MoveDirection[] directions = OptionsParser.parse(args).toArray(new MoveDirection[0]);
//        IWorldMap map = new RectangularMap(5, 5);
//        Vector2d[] positions = {
//                new Vector2d(2,2),
//                new Vector2d(3,4)
//        };
//
//        BetterMapVisualizer visualizer = new BetterMapVisualizer(
//                map,
//                new Vector2d(0, 0),
//                new Vector2d(4, 4),
//                500
//        );
//
//        IEngine engine = new SimulationEngine(directions, map, positions);
//        engine.run(visualizer);

//        MapVisualizer visualizer = new MapVisualizer(map);
//        String frame = visualizer.draw(new Vector2d(0, 0), new Vector2d(4, 4));
//        System.out.println(frame);

        MoveDirection[] directions = OptionsParser.parse(args).toArray(new MoveDirection[0]);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = {
                new Vector2d(2,2),
                new Vector2d(3,4)
        };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        System.out.println(map);
    }
}