package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AnimalTest {
    Animal testAnimal;
    @BeforeEach
    public void init(){
        testAnimal = new Animal(new RectangularMap(5,5), new Vector2d(2, 2));
    }

    @Test
    public void testIntegration(){
        String[] stringArgs = new String[6];
        Arrays.fill(stringArgs," ");

        Assertions.assertTrue(testAnimal.isAt(new Vector2d(2, 2)));
        Assertions.assertTrue(testAnimal.isFacing(MapDirection.NORTH));

        for (int i=1; i<6; ++i){                        // num of moves forward in a sequence
            for(int j=0; j<2; j++) {                    // repeat sequence 2 times
                Arrays.fill(stringArgs, 0, i, "f");
                for (MoveDirection move: OptionsParser.parse(stringArgs)){
                    testAnimal.move(move);
                }
                testAnimal.move(MoveDirection.RIGHT);   // turn right at the end of sequence
            }
        }

        Assertions.assertTrue(testAnimal.isAt(new Vector2d(4, 4)));
        Assertions.assertTrue(testAnimal.isFacing(MapDirection.SOUTH));
    }
    @Test
    public void testIntegration2(){
        for (MoveDirection move: OptionsParser.parse(new String[]{
                "right", "forward", "left", "f", "r", "r", "backward", "l", "b"})){
            testAnimal.move(move);
        }

        Assertions.assertTrue(testAnimal.isAt(new Vector2d(2, 4)));
        Assertions.assertTrue(testAnimal.isFacing(MapDirection.EAST));
    }
}
