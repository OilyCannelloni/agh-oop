package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MapBoundaryTest {
    @Test
    public void insertTest(){
        MapBoundary mapBoundary = new MapBoundary();
        IWorldMap map = new RectangularMap(10, 10);
        Animal[] animals = {
                new Animal(map, new Vector2d(2, 2)),
                new Animal(map, new Vector2d(6, 1)),
                new Animal(map, new Vector2d(3, 2)),
                new Animal(map, new Vector2d(3, 4)),
                new Animal(map, new Vector2d(5, 3))
        };
        for (Animal animal : animals) mapBoundary.insert(animal);

        Rect2D boundingBox = mapBoundary.getBoundingBox();

        Assertions.assertEquals(new Rect2D(
                new Vector2d(2, 1),
                new Vector2d(6, 4)
        ), boundingBox);
    }

    @Test
    public void positionChangedTest() {
        MapBoundary mapBoundary = new MapBoundary();
        IWorldMap map = new RectangularMap(10, 10);
        Animal[] animals = {
                new Animal(map, new Vector2d(3, 6)),
                new Animal(map, new Vector2d(6, 1)),
                new Animal(map, new Vector2d(2, 2)),
                new Animal(map, new Vector2d(3, 4)),
                new Animal(map, new Vector2d(5, 6))
        };

        for (Animal animal : animals) {
            map.place(animal);
            mapBoundary.insert(animal);
        }

        animals[3].move(MoveDirection.FORWARD);
        animals[4].move(MoveDirection.FORWARD);
        animals[2].move(MoveDirection.LEFT);
        animals[2].move(MoveDirection.FORWARD);

        Rect2D boundingBox = mapBoundary.getBoundingBox();
        Assertions.assertEquals(new Rect2D(
                new Vector2d(1, 1),
                new Vector2d(6, 7)
        ), boundingBox);

        animals[2].move(MoveDirection.BACKWARD);
        animals[1].move(MoveDirection.LEFT);
        animals[1].move(MoveDirection.FORWARD);

        boundingBox = mapBoundary.getBoundingBox();
        Assertions.assertEquals(new Rect2D(
                new Vector2d(2, 1),
                new Vector2d(5, 7)
        ), boundingBox);
    }
}
