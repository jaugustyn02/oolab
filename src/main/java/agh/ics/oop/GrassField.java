package agh.ics.oop;

import java.util.*;

import static java.lang.Math.floor;
import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap{
    private Vector2d lowerLeft = new Vector2d(0, 0);
    private Vector2d upperRight = new Vector2d(0, 0);
    private final int grassUpperBound;
    private final List<Grass> grasses = new ArrayList<>();
    private final MapVisualizer mapVisualizer = new MapVisualizer(this);
    private final Random rand = new Random();

    public GrassField(int numOfGrass) {
        this.grassUpperBound = (int) floor(sqrt(numOfGrass * 10));
        Vector2d newGrassPosition;
        for (int i=0; i<numOfGrass; i++) {
            do {
                newGrassPosition = this.randGrassPosition();
            } while (isOccupied(newGrassPosition));
            grasses.add(new Grass(newGrassPosition));
        }
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
    public String toString(){
        for (Animal animal: super.animals) {
            this.upperRight = this.upperRight.upperRight(animal.position);
            this.lowerLeft = this.lowerLeft.lowerLeft(animal.position);
        }
        return this.mapVisualizer.draw(lowerLeft, this.upperRight);
    }

    private Vector2d randGrassPosition(){
        return new Vector2d(rand.nextInt(this.grassUpperBound)+1, rand.nextInt(this.grassUpperBound)+1);
    }
}
