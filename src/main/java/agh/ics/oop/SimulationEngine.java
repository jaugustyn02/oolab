package agh.ics.oop;

import java.util.LinkedList;
import java.util.ListIterator;

public class SimulationEngine implements IEngine{
    private final IWorldMap map;
    private final MoveDirection[] directions;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] initial_positions){
        this.directions = directions;
        this.map = map;
        for (Vector2d position: initial_positions){
            this.map.place(new Animal(this.map, position));
        }
    }

    @Override
    public void run() {
        LinkedList<Animal> animals = map.getAnimals();
        ListIterator<Animal> it = animals.listIterator(0);
        for (MoveDirection direction: this.directions) {
            if (!it.hasNext())
                it = animals.listIterator(0);
            it.next().move(direction);
        }
    }
}
