package agh.ics.oop;

import java.util.*;

import static java.lang.Math.floor;
import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap{
    private Vector2d lowerLeft = new Vector2d(0, 0);
    private Vector2d upperRight = new Vector2d(0, 0);
    protected final int grassUpperBound;
    private final List<Grass> grasses;
    private final Random rand = new Random();

    public GrassField(int numOfGrass){
        this.grassUpperBound = (int) floor(sqrt(numOfGrass * 10));
        this.grasses = new ArrayList<>(10);
        for (int i=0; i<numOfGrass; i++)
            grasses.add(new Grass(randomValidGrassPosition(), this));
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
    protected Vector2d lowerLeft(){
        for (Animal animal: super.animals) {
            this.lowerLeft = this.lowerLeft.lowerLeft(animal.position);
        }
        return lowerLeft;
    }

    @Override
    protected Vector2d upperRight(){
        for (Animal animal: super.animals) {
            this.upperRight = this.upperRight.upperRight(animal.position);
        }
        for (Grass grass: grasses)
                this.upperRight = this.upperRight.upperRight(grass.getPosition());
        return upperRight;
    }

    protected Vector2d randomValidGrassPosition(){
        Vector2d randomGrassPosition;
        do {
            randomGrassPosition = new Vector2d(rand.nextInt(this.grassUpperBound)+1, rand.nextInt(this.grassUpperBound)+1);
        } while (isOccupied(randomGrassPosition));
        return randomGrassPosition;
    }
}
