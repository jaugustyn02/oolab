package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RectangularMapTest {
    private IWorldMap map;
    private static Animal animal1, animal2;
    private static Vector2d initPosition1, initPosition2, offTheMap, validPosition1, validPosition2;
    private List<Animal> animals;

    @BeforeAll
    public static void positionsInit(){
        initPosition1 = new Vector2d(0, 0);
        initPosition2 = new Vector2d(4, 4);
        offTheMap = new Vector2d(-1, -1);
        validPosition1 = new Vector2d(1, 1);
        validPosition2 = new Vector2d(3, 4);
    }
    @BeforeEach
    public void mapInit(){
        map = new RectangularMap(5,5);
        animal1 = new Animal(map, initPosition1);
        animal2 = new Animal(map, initPosition2);
    }

    @Test
    public void getAnimalsTest(){
        map.place(animal1);
        map.place(animal2);

        animals = map.getAnimals();
        Assertions.assertNotNull(animals);
        Assertions.assertSame(animal1, animals.get(0));
        Assertions.assertSame(animal2, animals.get(1));
    }
    @Test
    public void placeTest(){
        Animal animalTest1 = new Animal(map, initPosition1);
        Animal animalTest2 = new Animal(map, offTheMap);

        map.place(animal1);
        map.place(animal2);

        Exception exception1 = Assertions.assertThrows(IllegalArgumentException.class, () -> map.place(animalTest1));
        Exception exception2 = Assertions.assertThrows(IllegalArgumentException.class, () -> map.place(animalTest2));
        String expectedMessage1 = initPosition1+" - is not legal position to place animal on";
        String expectedMessage2 = offTheMap+" - is not legal position to place animal on";
        String actualMessage1 = exception1.getMessage();
        String actualMessage2 = exception2.getMessage();
        Assertions.assertEquals(expectedMessage1, actualMessage1);
        Assertions.assertEquals(expectedMessage2, actualMessage2);
//        map.place(animalTest1); FALSE
//        map.place(animalTest2); FALSE

        animals = map.getAnimals();
        Assertions.assertSame(animal1, animals.get(0));
        Assertions.assertSame(animal2, animals.get(1));
    }
    @Test
    public void canMoveToTest(){
        map.place(animal1);
        map.place(animal2);

        assertFalse(map.canMoveTo(initPosition1));
        assertFalse(map.canMoveTo(initPosition2));
        assertFalse(map.canMoveTo(offTheMap));
        assertTrue(map.canMoveTo(validPosition1));
        assertTrue(map.canMoveTo(validPosition2));
    }
    @Test
    public void isOccupiedTest(){
        map.place(animal1);
        map.place(animal2);

        assertTrue(map.isOccupied(initPosition1));
        assertTrue(map.isOccupied(initPosition1));
        assertFalse(map.isOccupied(validPosition1));
        assertFalse(map.isOccupied(validPosition2));
    }
    @Test
    public void objectAtTest(){
        Assertions.assertNull(map.objectAt(initPosition1));
        Assertions.assertNull(map.objectAt(initPosition2));
        map.place(animal1);
        map.place(animal2);
        Assertions.assertNotNull(map.objectAt(initPosition1));
        Assertions.assertNotNull(map.objectAt(initPosition2));
        assertTrue(map.objectAt(initPosition1) instanceof Animal);
        assertTrue(map.objectAt(initPosition2) instanceof Animal);
        Assertions.assertSame(animal1, map.objectAt(initPosition1));
        Assertions.assertSame(animal2, map.objectAt(initPosition2));
    }
    @Test
    public void toStringTest(){
        String emptyMapString = """
                 y\\x  0 1 2 3 4
                  5: -----------
                  4: | | | | | |
                  3: | | | | | |
                  2: | | | | | |
                  1: | | | | | |
                  0: | | | | | |
                 -1: -----------
                """;
        String filledMapString = """
                 y\\x  0 1 2 3 4
                  5: -----------
                  4: | | | | |N|
                  3: | | | | | |
                  2: | | | | | |
                  1: | | | | | |
                  0: |N| | | | |
                 -1: -----------
                """;
        Assertions.assertEquals(emptyMapString, map.toString());
        map.place(animal1);
        map.place(animal2);
        Assertions.assertEquals(filledMapString, map.toString());
    }

    @Test void integrationTest(){
        // place
        map.place(animal1);
        map.place(animal2);

        // isOccupied
        assertTrue(map.isOccupied(initPosition1));
        assertTrue(map.isOccupied(initPosition2));

        // getAnimals
        animals = map.getAnimals();
        Assertions.assertNotNull(animals);
        Assertions.assertEquals(2, animals.size());
        Assertions.assertSame(animal1, animals.get(0));
        Assertions.assertSame(animal2, animals.get(1));

        // objectAt
        Assertions.assertNotNull(map.objectAt(initPosition1));
        Assertions.assertNotNull(map.objectAt(initPosition2));
        assertTrue(map.objectAt(initPosition1) instanceof Animal);
        assertTrue(map.objectAt(initPosition2) instanceof Animal);
        Assertions.assertSame(animal1, map.objectAt(initPosition1));
        Assertions.assertSame(animal2, map.objectAt(initPosition2));

        // canMoveTo
        assertFalse(map.canMoveTo(initPosition1));
        assertFalse(map.canMoveTo(initPosition2));
        assertFalse(map.canMoveTo(offTheMap));
        assertTrue(map.canMoveTo(validPosition1));
        assertTrue(map.canMoveTo(validPosition2));

        // toString
        System.out.println(map);
    }
}
