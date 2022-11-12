package agh.ics.oop;


import java.util.ArrayList;
import java.util.List;

public class Animal {
    protected Vector2d position;
    private MapDirection direction = MapDirection.NORTH;
    private Vector2d new_position = null;
    private final IWorldMap map;
    private final List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.position = initialPosition;
    }
    public Animal(IWorldMap map){
        this(map, new Vector2d(2, 2));
    }
    public Animal(){
        this(new RectangularMap(5, 5));
    }

    @Override
    public String toString(){
        return direction.toString();
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    // Additional method, that checks if object is facing given direction
    public boolean isFacing(MapDirection direction){
        return this.direction.equals(direction);
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> this.direction = this.direction.next();
            case LEFT -> this.direction = this.direction.previous();
            case FORWARD -> new_position = this.position.add(this.direction.toUnitVector());
            case BACKWARD -> new_position = this.position.subtract(this.direction.toUnitVector());
        }
        if (new_position != null && map.canMoveTo(new_position)){
            positionChanged(new_position);
            if (map.objectAt(new_position) instanceof Grass grass){
                this.position = new_position;
                grass.setNewRandomPosition();
            }
            else this.position = new_position;
            new_position = null;
        }
    }

    public Vector2d getPosition() {
        return this.position;
    }

    protected void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    protected void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    private void positionChanged(Vector2d newPosition){
        for(IPositionChangeObserver observer: observers)
            observer.positionChanged(position, newPosition);
    }
}
