package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapDirectionTest {
    @Test
    public void testNext(){
//        Assertions.assertEquals(MapDirection.NORTH.next(), MapDirection.EAST);
//        Assertions.assertEquals(MapDirection.EAST.next(), MapDirection.SOUTH);
//        Assertions.assertEquals(MapDirection.SOUTH.next(), MapDirection.WEST);
//        Assertions.assertEquals(MapDirection.WEST.next(), MapDirection.NORTH);
        for(MapDirection md: MapDirection.values()){
            Assertions.assertEquals(md.next(), switch (md){
                case NORTH -> MapDirection.EAST;
                case EAST -> MapDirection.SOUTH;
                case SOUTH -> MapDirection.WEST;
                case WEST -> MapDirection.NORTH;
            });
        }
    }
}