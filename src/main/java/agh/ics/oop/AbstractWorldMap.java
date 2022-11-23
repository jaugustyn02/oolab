package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    protected final MapVisualizer mapVisualizer = new MapVisualizer(this);

    @Override
    abstract public boolean canMoveTo(Vector2d position);

    @Override
    public String toString(){
        return mapVisualizer.draw(this.lowerLeft(), this.upperRight());
    }

    abstract public Vector2d lowerLeft();
    abstract public Vector2d upperRight();

    @Override
    public void place(Animal animal) throws IllegalArgumentException{
        if (!this.canMoveTo(animal.getPosition()))
            throw new IllegalArgumentException(animal.getPosition()+" - is not legal position to place animal on");
        this.animals.put(animal.getPosition(), animal);
        animal.addObserver(this);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.objectAt(position) != null;
    }

    @Override
    public List<Animal> getAnimals(){
        return new ArrayList<>(animals.values());
    }

    @Override
    public Object objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        animals.put(newPosition, animals.remove(oldPosition));
    }
}
