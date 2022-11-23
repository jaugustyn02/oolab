package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class GrassFieldTest {
    private IWorldMap map;
    private static Animal animal1, animal2;
    private static Vector2d initPosition1, initPosition2, validPosition1, validPosition2, validPosition3;
    private List<Animal> animals;

    @BeforeAll
    public static void positionsInit(){
        initPosition1 = new Vector2d(0, 0);
        initPosition2 = new Vector2d(4, 4);
        validPosition1 = new Vector2d(-2, -2);
        validPosition2 = new Vector2d(10, 10);
        validPosition3 = new Vector2d(2, 3);
    }
    @BeforeEach
    public void mapInit(){
        map = new GrassField(10);
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
    public void placeTest() {
        Animal animalTest1 = new Animal(map, initPosition1);
        Animal animalTest2 = new Animal(map, validPosition3);

        map.place(animal1);
        map.place(animal2);
        map.place(animalTest2);

        Exception exception1 = Assertions.assertThrows(IllegalArgumentException.class, () -> map.place(animalTest1));
        String expectedMessage1 = initPosition1+" - is not legal position to place animal on";
        String actualMessage1 = exception1.getMessage();
        Assertions.assertEquals(expectedMessage1, actualMessage1);
//        map.place(animalTest1); FALSE

        animals = map.getAnimals();
        Assertions.assertSame(animal1, animals.get(0));
        Assertions.assertSame(animal2, animals.get(1));
    }
    @Test
    public void canMoveToTest(){
        map.place(animal1);
        map.place(animal2);

        Assertions.assertFalse(map.canMoveTo(initPosition1));
        Assertions.assertFalse(map.canMoveTo(initPosition2));
        Assertions.assertTrue(map.canMoveTo(validPosition1));
        Assertions.assertTrue(map.canMoveTo(validPosition2));
        Assertions.assertTrue(map.canMoveTo(validPosition3));
    }
    @Test
    public void objectAtTest(){
        Object objectTest1, objectTest2;
        objectTest1 = map.objectAt(initPosition1);
        objectTest2 = map.objectAt(initPosition2);
        Assertions.assertTrue(objectTest1 == null || objectTest1 instanceof Grass);
        Assertions.assertTrue(objectTest2 == null || objectTest2 instanceof Grass);
        map.place(animal1);
        map.place(animal2);
        Assertions.assertNotNull(map.objectAt(initPosition1));
        Assertions.assertNotNull(map.objectAt(initPosition2));
        Assertions.assertTrue(map.objectAt(initPosition1) instanceof Animal);
        Assertions.assertTrue(map.objectAt(initPosition2) instanceof Animal);
        Assertions.assertSame(animal1, map.objectAt(initPosition1));
        Assertions.assertSame(animal2, map.objectAt(initPosition2));
    }
    @Test
    public void isOccupiedTest(){
        map.place(animal1);
        map.place(animal2);

        Assertions.assertTrue(map.isOccupied(initPosition1));
        Assertions.assertTrue(map.isOccupied(initPosition1));

        if (map.objectAt(validPosition1) == null)
            Assertions.assertFalse(map.isOccupied(validPosition1));
        if (map.objectAt(validPosition2) == null)
            Assertions.assertFalse(map.isOccupied(validPosition2));
        if (map.objectAt(validPosition3) == null)
            Assertions.assertFalse(map.isOccupied(validPosition3));
    }

    @Test void integrationTest(){
        // place
        map.place(animal1);
        map.place(animal2);

        // isOccupied
        Assertions.assertTrue(map.isOccupied(initPosition1));
        Assertions.assertTrue(map.isOccupied(initPosition2));

        // getAnimals
        animals = map.getAnimals();
        Assertions.assertNotNull(animals);
        Assertions.assertEquals(2, animals.size());
        Assertions.assertSame(animal1, animals.get(0));
        Assertions.assertSame(animal2, animals.get(1));

        // objectAt
        Assertions.assertNotNull(map.objectAt(initPosition1));
        Assertions.assertNotNull(map.objectAt(initPosition2));
        Assertions.assertTrue(map.objectAt(initPosition1) instanceof Animal);
        Assertions.assertTrue(map.objectAt(initPosition2) instanceof Animal);
        Assertions.assertSame(animal1, map.objectAt(initPosition1));
        Assertions.assertSame(animal2, map.objectAt(initPosition2));

        // canMoveTo
        Assertions.assertFalse(map.canMoveTo(initPosition1));
        Assertions.assertFalse(map.canMoveTo(initPosition2));
        Assertions.assertTrue(map.canMoveTo(validPosition1));
        Assertions.assertTrue(map.canMoveTo(validPosition2));
        Assertions.assertTrue(map.canMoveTo(validPosition3));

        // toString
        System.out.println(map);
    }
}
