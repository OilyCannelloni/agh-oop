package agh.ics.oop;


public class World {
    public static void main(String[] args) {
        try {
            MoveDirection[] directions = OptionsParser.parse(args).toArray(new MoveDirection[0]);
            IWorldMap map = new GrassField(10);
            Vector2d[] positions = {
                    new Vector2d(2,2),
                    new Vector2d(3,4)
            };

            BetterMapVisualizer visualizer = new BetterMapVisualizer(
                    map,
                    new Vector2d(0, 0),
                    new Vector2d(11, 11),
                    50
            );

            SimulationEngine engine = new SimulationEngine(directions, map, positions);
            engine.run(visualizer);

            System.out.println(map);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }
}