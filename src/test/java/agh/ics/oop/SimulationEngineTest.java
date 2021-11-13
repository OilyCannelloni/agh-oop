package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimulationEngineTest {
    @Test
    public void runTest(){
        String[][] moveSequences = {
                {"f", "r", "f", "f", "f", "f", "f", "r", "r", "f", "b", "f", "b", "f", "r", "f"}, // map boundaries
                {"r", "l", "f", "f", "f", "l", "f", "f", "f", "f"}, // collisions and move priority
                {"f", "f", "f", "f", "r", "f", "l", "l", "f", "b", "f", "b", "f", "f", "f", "b"} // everything
        };

        int[] animalCount = {2, 2, 4};

        Vector2d[][] startingPositions = {
                {new Vector2d(1, 1), new Vector2d(3, 3)},
                {new Vector2d(1, 3), new Vector2d(3, 3)},
                {new Vector2d(2, 2), new Vector2d(2, 3), new Vector2d(3, 3), new Vector2d(3, 2)}
        };

        Vector2d[][] finalPositions = {
                {new Vector2d(0, 4), new Vector2d(4, 0)},
                {new Vector2d(3, 3), new Vector2d(3, 1)},
                {new Vector2d(4, 2), new Vector2d(2, 3), new Vector2d(1, 4), new Vector2d(4, 3)}
        };

        MapDirection[][] finalFacings = {
                {MapDirection.SOUTH, MapDirection.SOUTH},
                {MapDirection.EAST, MapDirection.SOUTH},
                {MapDirection.EAST, MapDirection.NORTH, MapDirection.WEST, MapDirection.WEST}
        };


        for (int i = 0; i < moveSequences.length; i++){
            MoveDirection[] directions = OptionsParser.parse(moveSequences[i]).toArray(new MoveDirection[0]);
            IWorldMap map = new RectangularMap(5, 5);
            IEngine engine = new SimulationEngine(directions, map, startingPositions[i]);

            engine.run();

            MapVisualizer visualizer = new MapVisualizer(map);
            String frame = visualizer.draw(new Vector2d(0, 0), new Vector2d(4, 4));
            System.out.println(frame);

            for (int a = 0; a < animalCount[i]; a++) {
                Object checkedObject = map.objectAt(finalPositions[i][a]);
                Assertions.assertTrue(checkedObject instanceof Animal);
                Animal animal = (Animal) checkedObject;
                Assertions.assertEquals(finalFacings[i][a], animal.getFacing());
            }
        }





    }
}
