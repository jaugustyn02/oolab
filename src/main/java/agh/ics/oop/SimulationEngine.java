package agh.ics.oop;

import java.util.LinkedList;
import java.util.ListIterator;

public class SimulationEngine implements IEngine{
    private final IWorldMap map;
    private final LinkedList<Animal> animals = new LinkedList<>();
    private final MoveDirection[] directions;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] initial_positions){
        this.directions = directions;
        this.map = map;
        for (Vector2d position: initial_positions){
            Animal newAnimal = new Animal(this.map, position);
            this.animals.add(newAnimal);
            this.map.place(newAnimal);
        }
    }

    @Override
    public void run() {
//        int nr=0;
        ListIterator<Animal> it = this.animals.listIterator(0);
        for (MoveDirection direction: this.directions){
            if(!it.hasNext())
                it = this.animals.listIterator(0);
            it.next().move(direction);
//            System.out.println(nr%2 + ": " + direction.toString());
//            System.out.println(this.map.toString());
//            nr++;
        }
        System.out.println(this.map.toString());
    }
}
