package agh.ics.oop;

import agh.ics.oop.gui.MapRenderer;

import java.util.List;

public class SimulationEngine implements IEngine, Runnable{
    private final IWorldMap map;
    private MoveDirection[] directions;
    private final MapRenderer renderer;
    private int moveDelay = 100;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] initPositions, MapRenderer renderer){
        this.directions = directions;
        this.map = map;
        this.renderer = renderer;
        for (Vector2d position: initPositions){
            Animal animal = new Animal(this.map, position);
            if(renderer != null)
                animal.addObserver(renderer);
            this.map.place(animal);
        }

    }
    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] initPositions) {
        this(directions, map, initPositions, null);
    }
    public SimulationEngine(IWorldMap map, Vector2d[] initPositions, MapRenderer renderer) {
        this(new MoveDirection[]{}, map, initPositions, renderer);
    }

    @Override
    public void run() {
//        System.out.println("Thread started.");
        if(renderer != null) this.renderer.render();
        List<Animal> animals = map.getAnimals();
        int animalInd = 0;
        for (MoveDirection direction: this.directions) {
            try{
                Thread.sleep(moveDelay);
            }catch (InterruptedException e){
                System.out.println("Exception: The simulation has been aborted");
                throw new RuntimeException(e);
            }
            animals.get(animalInd).move(direction);
            animalInd = (animalInd + 1) % animals.size();
        }
    }

    public void setMoveDirections(MoveDirection[] directions){
        this.directions = directions;
    }

    public void setMoveDelay(int moveDelay){
        this.moveDelay = moveDelay;
    }
}
