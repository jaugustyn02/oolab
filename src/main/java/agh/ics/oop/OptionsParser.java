package agh.ics.oop;

import java.util.Arrays;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args){
        return (Arrays.stream(args)
                .map(arg -> switch (arg) {
                    case "forward" -> "f";
                    case "backward" -> "b";
                    case "right" -> "r";
                    case "left" -> "l";
                    default -> arg;
                })
                .filter(Arrays.asList("f", "b", "r", "l")::contains)
                .map(arg -> switch (arg) {
                    case "f" -> MoveDirection.FORWARD;
                    case "b" -> MoveDirection.BACKWARD;
                    case "r" -> MoveDirection.RIGHT;
//                    case "l" -> MoveDirection.LEFT;
                    default -> MoveDirection.LEFT;      // fix to error: the switch expression does not cover all possible input values
                }).toList()).toArray(new MoveDirection[0]);
    }
}
