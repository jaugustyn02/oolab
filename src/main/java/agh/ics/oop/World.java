package agh.ics.oop;

//import java.util.Arrays;
//import java.lang.reflect.AnnotatedArrayType;
//import java.util.List;
//import java.util.stream.Collectors;


public class World {
    public static void main(String[] args) {
//        lab5
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new GrassField(5, true);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        System.out.println(map);
//        lab5 end

//        lab4
//        MoveDirection[] directions = new OptionsParser().parse(args);
//        IWorldMap map = new RectangularMap(10, 5);
//        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
//        IEngine engine = new SimulationEngine(directions, map, positions);
//        engine.run();

//        lab4 end
//        lab3
//        Animal animal1 = new Animal();
//        System.out.println(Arrays.toString(OptionsParser.parse(args)));
//        for (MoveDirection move: OptionsParser.parse(args)){
//            animal1.move(move);
//        }
//        System.out.println(animal1);
//
//        Animal animal2 = new Animal();
//        System.out.println(Arrays.toString(OptionsParser.parse(args)));
//        for (MoveDirection move: OptionsParser.parse(args)){
//            animal2.move(move);
//        }
//        System.out.println(animal2);
//        lab3 end

//        lab2
//        Vector2d position1 = new Vector2d(1,2);
//        System.out.println(position1);
//        Vector2d position2 = new Vector2d(-2,1);
//        System.out.println(position2);
//        System.out.println(position1.add(position2));
//        lab2 end
//
//        lab1
//        System.out.println("system wystartował");
//        List<Direction> directions = convert(args);
//        run(directions.toArray(new Direction[0]));
//        System.out.println("system zakończył działanie");
//        lab1 end
    }

    /*
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
     */
}