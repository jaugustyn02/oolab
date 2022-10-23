package agh.ics.oop;

//import java.lang.reflect.AnnotatedArrayType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class World {
    public static void main(String[] args) {
//        lab3
        Animal newAnimal = new Animal();
        System.out.println(Arrays.toString(OptionsParser.parse(args)));
        for (MoveDirection move: OptionsParser.parse(args)){
            newAnimal.move(move);
        }
        System.out.println(newAnimal);
//        lab3 end

//        lab2
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
//        lab2 end

//        lab1
        System.out.println("system wystartował");
        List<Direction> directions = convert(args);
        run(directions.toArray(new Direction[0]));
        System.out.println("system zakończył działanie");
//        lab1 end
    }

    static List<Direction> convert(String[] args) {
        return Arrays.stream(args)
                .filter(Arrays.asList("f", "b", "r", "l")::contains)
                .map(arg -> switch (arg) {
                    case "f" -> Direction.FORWARD;
                    case "b" -> Direction.BACKWARD;
                    case "r" -> Direction.RIGHT;
                    case "l" -> Direction.LEFT;
                    default -> null;
                })
                .collect(Collectors.toList());
    }

    static void run(Direction[] directions) {
        System.out.println("Start");
        System.out.println(Arrays.stream(directions)
                .map(dir -> switch (dir) {
                    case FORWARD -> "Zwierzak idzie do przodu";
                    case BACKWARD -> "Zwierzak idzie do tyłu";
                    case RIGHT -> "Zwierzak skręca w prawo";
                    case LEFT -> "Zwierzak skręca w lewo";
                })
                .collect(Collectors.joining("\n")));
        System.out.println(Arrays.stream(directions).map(Enum::name).collect(Collectors.joining(", ")));
        System.out.println("Stop");
    }
}