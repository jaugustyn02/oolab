package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class SimulationEngineTest {
    @Test
    public void IntegrationTest1(){
        String[] args = new String[]{"f", "b" ,"r" , "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        List<Animal> animals = map.getAnimals();

        Assertions.assertTrue(animals.get(1).isAt(new Vector2d(2, 0)));
        Assertions.assertTrue(animals.get(1).isFacing(MapDirection.SOUTH));

        Assertions.assertTrue(animals.get(0).isAt(new Vector2d(3, 4)));
        Assertions.assertTrue(animals.get(0).isFacing(MapDirection.NORTH));

        System.out.println(map);
    }
    @Test
    public void IntegrationTest2(){
        String[] args = new String[]{"r", "l" ,"b" , "l", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map1 = new RectangularMap(6, 6);
        Vector2d[] positions = { new Vector2d(0,0), new Vector2d(5,5),new Vector2d(0,0) };

        // place exception test
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            IEngine engine = new SimulationEngine(directions, map1, positions);
        });
        String expectedMessage = positions[2]+" - is not legal position to place animal on";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
        // end of place exception test

        IWorldMap map2 = new RectangularMap(6, 6);
        IEngine engine = new SimulationEngine(directions, map2, Arrays.copyOf(positions,2));
        engine.run();

        List<Animal> animals = map2.getAnimals();

        Assertions.assertTrue(animals.get(0).isAt(new Vector2d(5, 0)));
        Assertions.assertTrue(animals.get(0).isFacing(MapDirection.EAST));

        Assertions.assertTrue(animals.get(1).isAt(new Vector2d(5, 1)));
        Assertions.assertTrue(animals.get(1).isFacing(MapDirection.SOUTH));

        System.out.println(map2);
    }
}
