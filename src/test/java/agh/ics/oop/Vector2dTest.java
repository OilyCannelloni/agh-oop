package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Vector2dTest {
    @Test
    public void equalsTest(){
        Vector2d v = new Vector2d(1, 2);

        String obj1 = "abcd";
        Assertions.assertNotEquals(v, obj1);

        Vector2d obj2 = new Vector2d(2, 2);
        Assertions.assertNotEquals(v, obj2);

        ArrayList <Vector2d> obj3 = new ArrayList<>();
        Assertions.assertNotEquals(v, obj3);

        Vector2d obj4 = new Vector2d(1, 2);
        Assertions.assertEquals(v, obj4);
    }

    @Test
    public void toStringTest(){
        Assertions.assertEquals("(2,1)", new Vector2d(2, 1).toString());
        Assertions.assertEquals("(-2,-1)", new Vector2d(-2, -1).toString());
        Assertions.assertEquals("(10,-12)", new Vector2d(10, -12).toString());
    }

    @Test
    public void addTest(){
        Vector2d v1 = new Vector2d(3, 5);
        Vector2d v2 = new Vector2d(-5, 8);
        Assertions.assertEquals(v1.add(v2), new Vector2d(-2, 13));
    }

    @Test
    public void subtractTest(){
        Vector2d v = new Vector2d(2, 3);
        Assertions.assertEquals(v.subtract(new Vector2d(-2, 4)), new Vector2d(4, -1));
    }

    @Test
    public void precedesTest(){
        Vector2d v = new Vector2d(2, 3);
        Assertions.assertFalse(v.precedes(new Vector2d(2, 2)));
        Assertions.assertFalse(v.precedes(new Vector2d(1, 3)));
        Assertions.assertFalse(v.precedes(new Vector2d(1, 1)));
        Assertions.assertTrue(v.precedes(new Vector2d(2, 3)));
        Assertions.assertTrue(v.precedes(new Vector2d(3, 3)));
    }

    @Test
    public void followsTest(){
        Vector2d v = new Vector2d(2, 3);
        Assertions.assertFalse(v.follows(new Vector2d(3, 3)));
        Assertions.assertFalse(v.follows(new Vector2d(2, 4)));
        Assertions.assertFalse(v.follows(new Vector2d(6, 6)));
        Assertions.assertTrue(v.follows(new Vector2d(2, 3)));
        Assertions.assertTrue(v.follows(new Vector2d(1, 3)));
    }

    @Test
    public void upperRightTest(){
        Vector2d v = new Vector2d(2, 3);
        Assertions.assertEquals(v.upperRight(new Vector2d(1, 6)), new Vector2d(2, 6));
    }

    @Test
    public void lowerLeftTest(){
        Vector2d v = new Vector2d(2, 3);
        Assertions.assertEquals(v.lowerLeft(new Vector2d(5, 2)), new Vector2d(2, 2));
    }

    @Test
    public void oppositeTest() {
        Vector2d v = new Vector2d(2, -3);
        Assertions.assertEquals(v.opposite(), new Vector2d(-2, 3));
    }
}
