package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Vector2dTest {
    @Test public void testEquals(){
        Assertions.assertNotEquals((new Vector2d(320, 720)), new Vector2d(660, 235));
        Assertions.assertEquals((new Vector2d(2137, 420)), new Vector2d(2137, 420));
    }

    @Test public void testToString(){
        Assertions.assertEquals("(21,37)", new Vector2d(21, 37).toString());
        Assertions.assertEquals("(11,23)", new Vector2d(11, 23).toString());
    }

    @Test public void testPrecedes(){
        Assertions.assertFalse((new Vector2d(1, 2).precedes((new Vector2d(-2, 1)))));
        Assertions.assertTrue((new Vector2d(-3, 5).precedes((new Vector2d(10, 7)))));
        Assertions.assertTrue((new Vector2d(2, 1).precedes((new Vector2d(2, 1)))));
    }

    @Test public void testFollows(){
        Assertions.assertTrue((new Vector2d(1, 2).follows((new Vector2d(-2, 1)))));
        Assertions.assertFalse((new Vector2d(-3, 5).follows((new Vector2d(10, 7)))));
        Assertions.assertTrue((new Vector2d(2, 1).follows((new Vector2d(2, 1)))));
    }

    @Test public void testUpperRight(){
        Assertions.assertEquals((new Vector2d(2, 2)), (new Vector2d(-1, 2)).upperRight(new Vector2d(2, -5)));
        Assertions.assertEquals((new Vector2d(10, 3)), (new Vector2d(0, 3)).upperRight(new Vector2d(10, -10)));
        Assertions.assertEquals((new Vector2d(21, 37)), (new Vector2d(21, 37)).upperRight(new Vector2d(0, 0)));
    }

    @Test public void testLowerLeft(){
        Assertions.assertEquals((new Vector2d(-1, -5)), (new Vector2d(-1, 2)).lowerLeft(new Vector2d(2, -5)));
        Assertions.assertEquals((new Vector2d(-7, -3)), (new Vector2d(0, -3)).lowerLeft(new Vector2d(-7, 10)));
        Assertions.assertEquals((new Vector2d(0, 0)), (new Vector2d(21, 37)).lowerLeft(new Vector2d(0, 0)));
    }

    @Test public void testAdd(){
        Assertions.assertEquals((new Vector2d(15, 15)), (new Vector2d(5, 10)).add(new Vector2d(10, 5)));
    }

    @Test public void testSubtract(){
        Assertions.assertEquals((new Vector2d(-3, 2)), (new Vector2d(5, 10)).subtract(new Vector2d(8, 8)));
    }

    @Test public void testOpposite(){
        Assertions.assertEquals((new Vector2d(4, -5)), (new Vector2d(-4, 5)).opposite());
    }
}
