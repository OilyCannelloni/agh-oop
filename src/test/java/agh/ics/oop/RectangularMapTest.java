package agh.ics.oop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangularMapTest {
    @Test
    public void placeTest(){
        IWorldMap map = new RectangularMap(5, 5);
        Animal a1 = new Animal(map, new Vector2d(1, 1));
        Animal a2 = new Animal(map, new Vector2d(2, 3));
        Animal a3 = new Animal(map, new Vector2d(1, 1));
        map.place(a1);
        map.place(a2);
        try {
            map.place(a3);
        } catch (IllegalArgumentException ignore) {}
        Assertions.assertEquals(a1, map.objectAt(new Vector2d(1, 1)));
        Assertions.assertEquals(a2, map.objectAt(new Vector2d(2, 3)));
    }

    @Test
    public void canMoveToTest(){
        IWorldMap map = new RectangularMap(5, 5);
        map.place(new Animal(map, new Vector2d(2, 2)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(2, 2)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(3, 2)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(5, 3)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(-1, 2)));
    }

    @Test
    public void isOccupiedTest(){
        IWorldMap map = new RectangularMap(5, 5);
        map.place(new Animal(map, new Vector2d(3, 4)));
        Assertions.assertTrue(map.isOccupied(new Vector2d(3, 4)));
        Assertions.assertFalse(map.isOccupied(new Vector2d(3, 2)));
        Assertions.assertFalse(map.isOccupied(new Vector2d(5, 3)));
        Assertions.assertFalse(map.isOccupied(new Vector2d(-1, 2)));
    }

    @Test
    public void objectAtTest(){
        IWorldMap map = new RectangularMap(5, 5);
        Animal a1 = new Animal(map, new Vector2d(2, 2));
        map.place(a1);
        Assertions.assertEquals(a1, map.objectAt(new Vector2d(2, 2)));
        Assertions.assertNull(map.objectAt(new Vector2d(2, 3)));
    }
}
