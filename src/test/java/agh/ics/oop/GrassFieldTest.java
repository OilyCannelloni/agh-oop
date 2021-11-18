package agh.ics.oop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class GrassFieldTest {
    @Test
    public void generateGrassTest(){
        IWorldMap map = new GrassField(8);
        int bound = (int) Math.floor(Math.sqrt(10*8)) + 1;
        int counter = 0;
        for (int x = 0; x < bound; x++) {
            for (int y = 0; y < bound; y++){
                if (map.objectAt(new Vector2d(x, y)) instanceof Grass) counter++;
            }
        }
        Assertions.assertEquals(8, counter);
    }

    @Test
    public void placeTest(){
        IWorldMap map = new GrassField(10);
        Animal a1 = new Animal(map, new Vector2d(1, 1));
        Animal a2 = new Animal(map, new Vector2d(2, 3));
        Animal a3 = new Animal(map, new Vector2d(1, 1));
        map.place(a1);
        map.place(a2);
        map.place(a3);
        Assertions.assertEquals(a1, map.objectAt(new Vector2d(1, 1)));
        Assertions.assertEquals(a2, map.objectAt(new Vector2d(2, 3)));
    }

    @Test
    public void canMoveToTest(){
        IWorldMap map = new GrassField(10);
        map.place(new Animal(map, new Vector2d(2, 2)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(2, 2)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(3, 2)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(-1, 2)));
    }

    @Test
    public void isOccupiedTest(){
        IWorldMap map = new GrassField(8);
        int bound = (int) Math.floor(Math.sqrt(10*8)) + 2;
        int counter = 0;
        for (int x = 0; x < bound; x++) {
            for (int y = 0; y < bound; y++){
                if (map.isOccupied(new Vector2d(x, y))) counter++;
            }
        }
        Assertions.assertEquals(8, counter);
        map.place(new Animal(map, new Vector2d(12, 12)));
        Assertions.assertTrue(map.isOccupied(new Vector2d(12, 12)));
        Assertions.assertFalse(map.isOccupied(new Vector2d(13, 13)));
        Assertions.assertFalse(map.isOccupied(new Vector2d(-1, 2)));
    }

    @Test
    public void objectAtTest(){
        IWorldMap map = new GrassField(10);
        int bound = (int) Math.floor(Math.sqrt(10*8)) + 1;
        Animal a1 = new Animal(map, new Vector2d(12, 12));
        map.place(a1);
        Assertions.assertEquals(a1, map.objectAt(new Vector2d(12, 12)));

        for (int f = 0; f < bound*bound; f++) {
            Vector2d pos = new Vector2d(f / bound, f % bound);
            if (map.isOccupied(pos)) {
                Assertions.assertTrue(map.objectAt(pos) instanceof Grass);
            }
        }

        Assertions.assertNull(map.objectAt(new Vector2d(12, 13)));
    }
}
