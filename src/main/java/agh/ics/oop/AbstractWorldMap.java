package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap{
    protected List<Animal> animals = new ArrayList<>();

    @Override
    abstract public boolean canMoveTo(Vector2d position);
    @Override
    abstract public String toString();

    @Override
    public boolean place(Animal animal) {
        if (!this.canMoveTo(animal.getPosition())) return false;
        this.animals.add(animal);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.objectAt(position) != null;
    }

    @Override
    public List<Animal> getAnimals(){
        return this.animals;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal: this.animals)
            if (animal.isAt(position)) return animal;
        return null;
    }
}
