package agh.ics.oop;

import java.util.List;

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
        List<Animal> animals = map.getAnimals();
        int animalInd=0;
        for (MoveDirection direction: this.directions) {
            animals.get(animalInd).move(direction);
            animalInd = (animalInd + 1) % animals.size();
        }
    }
}
