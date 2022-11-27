package agh.ics.oop;

import java.util.*;

import static java.lang.Math.floor;
import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap{
    protected final int grassUpperBound;
    private final List<Grass> grasses;
    private final Random rand = new Random();
    private final MapBoundary mapBoundary;

    public GrassField(int numOfGrass, MapBoundary mapBoundary){
        this.grassUpperBound = (int) floor(sqrt(numOfGrass * 10));
        this.grasses = new ArrayList<>(10);
        this.mapBoundary = mapBoundary;
        Vector2d randPosition;
        for (int i=0; i<numOfGrass; i++) {
            randPosition = randomValidGrassPosition();
            grasses.add(new Grass(randPosition, this));
            mapBoundary.addObject(randPosition, ObjectType.Grass);
        }
    }

    public GrassField(int numOfGrass){
        this(numOfGrass, new MapBoundary());
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        Object object = this.objectAt(position);
        return !(object instanceof Animal);
    }

    @Override
    public Object objectAt(Vector2d position) {
        Object object = super.objectAt(position);
        if (object != null) return object;
        for (Grass grass: this.grasses)
            if (grass.getPosition().equals(position)) return grass;
        return null;
    }

    @Override
    public Vector2d lowerLeft(){
        return mapBoundary.lowerLeft();
    }

    @Override
    public Vector2d upperRight(){
        return mapBoundary.upperRight();
    }

    protected Vector2d randomValidGrassPosition(){
        Vector2d randomGrassPosition;
        do {
            randomGrassPosition = new Vector2d(rand.nextInt(this.grassUpperBound)+1, rand.nextInt(this.grassUpperBound)+1);
        } while (isOccupied(randomGrassPosition));
        return randomGrassPosition;
    }

    @Override
    public void place(Animal animal) throws IllegalArgumentException{
        if (!this.canMoveTo(animal.getPosition()))
            throw new IllegalArgumentException(animal.getPosition()+" - is not legal position to place animal on");
        this.animals.put(animal.getPosition(), animal);
        animal.addObserver(this);
        animal.addObserver(mapBoundary);
        mapBoundary.addObject(animal.getPosition(), ObjectType.Animal);
    }
}
