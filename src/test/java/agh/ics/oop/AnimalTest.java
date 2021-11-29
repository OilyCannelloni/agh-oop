package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AnimalTest {
    @Test
    public void moveTest(){
        String[][] sequences = {
                {"f", "l", "f"},
                {"f", "f", "f", "f", "l", "b", "b", "b", "l"},
                {"r", "r", "r", "f"},
                {"forward", "right", "backward", "left"},
                {"b", "l", "forward", "stop", "forward", "s"}
        };

        Vector2d[] finalPositions = {
                new Vector2d(1, 3),
                new Vector2d(4, 4),
                new Vector2d(1, 2),
                new Vector2d(1, 3),
                new Vector2d(0, 1)
        };

        MapDirection[] finalFacings = {
                MapDirection.WEST,
                MapDirection.SOUTH,
                MapDirection.WEST,
                MapDirection.NORTH,
                MapDirection.WEST
        };

        for (int i = 0; i < 4; i++){
            ArrayList<MoveDirection> dirs = OptionsParser.parse(sequences[i]);
            IWorldMap map = new RectangularMap(5, 5);
            Animal myAnimal = new Animal(map, new Vector2d(2, 2));
            map.place(myAnimal);
            for (MoveDirection dir : dirs) {
                myAnimal.move(dir);
            }
            Assertions.assertEquals(finalPositions[i], myAnimal.getPosition());
            Assertions.assertEquals(finalFacings[i], myAnimal.getFacing());
        }

        try {
            ArrayList<MoveDirection> dirs = OptionsParser.parse(sequences[4]);
            IWorldMap map = new RectangularMap(5, 5);
            Animal myAnimal = new Animal(map, new Vector2d(2, 2));
            for (MoveDirection dir : dirs) {
                myAnimal.move(dir);
            }
            Assertions.fail();
        } catch (IllegalArgumentException ignored) {}
    }
}
