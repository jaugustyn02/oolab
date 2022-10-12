package agh.ics.oop;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Advanced version
public class World {
    public static void main(String[] args) {
        System.out.println("system wystartował");
        List<Direction> directions = convert(args);
        run(directions);
        System.out.println("system zakończył działanie");
    }

    static List<Direction> convert(String[] args){
        return Arrays.stream(args)
                .filter(Arrays.asList("f", "b", "r", "l")::contains)
                .map(arg -> switch(arg){
                    case "f" -> Direction.FORWARD;
                    case "b" -> Direction.BACKWARD;
                    case "r" -> Direction.RIGHT;
                    case "l" -> Direction.LEFT;
                    default -> null;
                })
                .collect(Collectors.toList());
    }

    static void run(List<Direction> directions){
        System.out.println("Start");
        System.out.println(directions.stream()
                .map(dir -> switch(dir){
                    case FORWARD -> "Zwierzak idzie do przodu";
                    case BACKWARD -> "Zwierzak idzie do tyłu";
                    case RIGHT -> "Zwierzak skręca w prawo";
                    case LEFT -> "Zwierzak skręca w lewo";
                })
                .collect(Collectors.joining("\n")));
        System.out.println("Stop");
        System.out.println(directions.stream().map(Enum::name).collect(Collectors.joining(", ")));
    }
}


// Standard version
//public class World {
//    public static void main(String[] args) {
//        System.out.println("system wystartował");
//        Direction[] direction_arr = new Direction[args.length];
//        int no_valid_args = convert(args, direction_arr);
//        run(Arrays.copyOfRange(direction_arr, 0, no_valid_args));
//        System.out.println("system zakończył działanie");
//    }
//    // static int function that assigns Direction values in passed empty array according to initial String arguments
//    // and returns number of valid arguments
//    static int convert(String[] args, Direction[] dir_arr){
//        int i=0;
//        for (String arg: args){
//            dir_arr[i] = switch(arg){
//                case "f" -> Direction.FORWARD;
//                case "b" -> Direction.BACKWARD;
//                case "r" -> Direction.RIGHT;
//                case "l" -> Direction.LEFT;
//                default -> null;
//            };
//            if (dir_arr[i] != null) i++;
//        }
//        return i;
//    }
//    // static void function that prints text according to Direction values in passed array and prints all elements of
//    // this array with separator ','
//    static void run(Direction[] dir_arr){
//        System.out.println("Start");
//        for (Direction direction: dir_arr) {
//            System.out.print(switch (direction){
//                case FORWARD -> "Zwierzak idzie do przodu\n";
//                case BACKWARD -> "Zwierzak idzie do tyłu\n";
//                case RIGHT -> "Zwierzak skręca w prawo\n";
//                case LEFT -> "Zwierzak skręca w lewo\n";
//            });
//        }
//        System.out.println("Stop");
//
//        for(Direction dir: Arrays.copyOfRange(dir_arr, 0, dir_arr.length-1))
//            System.out.print(dir + ", ");
//        System.out.println(dir_arr[dir_arr.length-1]);
//    }
//}
