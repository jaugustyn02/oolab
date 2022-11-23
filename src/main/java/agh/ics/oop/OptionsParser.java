package agh.ics.oop;

import java.util.Arrays;
import java.util.function.Predicate;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) throws IllegalArgumentException{
        return (Arrays.stream(args)
                .map(arg -> switch (arg) {
                    case "forward" -> "f";
                    case "backward" -> "b";
                    case "right" -> "r";
                    case "left" -> "l";
                    default -> arg.trim();
                })
//                .filter(Arrays.asList("f", "b", "r", "l")::contains)
                .filter(Predicate.not(String::isEmpty))
                .map(arg -> switch (arg) {
                    case "f" -> MoveDirection.FORWARD;
                    case "b" -> MoveDirection.BACKWARD;
                    case "r" -> MoveDirection.RIGHT;
                    case "l" -> MoveDirection.LEFT;
                    default -> throw new IllegalArgumentException(arg + " is not legal move specification");
                }).toList()).toArray(new MoveDirection[0]);
    }
}
