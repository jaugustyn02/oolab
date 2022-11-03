package agh.ics.oop;

import java.util.LinkedList;

public class RectangularMap implements IWorldMap{
    private final static Vector2d lowerLeft = new Vector2d(0, 0);
    private final Vector2d upperRight;
    private final LinkedList<Animal> animals = new LinkedList<>();
    private final MapVisualizer mapVisualizer = new MapVisualizer(this);

    public RectangularMap(int width, int height){
        this.upperRight = new Vector2d(width-1, height-1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.precedes(upperRight) && position.follows(lowerLeft)
                && !this.isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (this.isOccupied(animal.getPosition())) return false;
        this.animals.add(animal);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal: this.animals)
            if (animal.isAt(position)) return true;
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal: this.animals)
            if (animal.isAt(position)) return animal;
        return null;
    }

    @Override
    public String toString(){
        return this.mapVisualizer.draw(lowerLeft, this.upperRight);
    }

    @Override
    public LinkedList<Animal> getAnimals(){
        return animals;
    }
}
