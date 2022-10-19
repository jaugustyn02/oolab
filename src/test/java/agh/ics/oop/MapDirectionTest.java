package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapDirectionTest {
    @Test public void testNext(){
        for(MapDirection md: MapDirection.values()){
            Assertions.assertEquals(md.next(), switch (md){
                case NORTH -> MapDirection.EAST;
                case EAST -> MapDirection.SOUTH;
                case SOUTH -> MapDirection.WEST;
                case WEST -> MapDirection.NORTH;
            });
        }
    }
    @Test public void testPrevious() {
        for (MapDirection md : MapDirection.values()) {
            Assertions.assertEquals(md.previous(), switch (md) {
                case NORTH -> MapDirection.WEST;
                case WEST -> MapDirection.SOUTH;
                case SOUTH -> MapDirection.EAST;
                case EAST -> MapDirection.NORTH;
            });
        }
    }
}