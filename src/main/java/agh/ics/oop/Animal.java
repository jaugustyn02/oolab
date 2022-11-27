package agh.ics.oop;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Animal extends AbstractMapElement{
    protected Vector2d position;
    private MapDirection direction = MapDirection.NORTH;
//    private Vector2d new_position = null;
//    private MoveDirection new_
    private final IWorldMap map;
    private final List<IPositionChangeObserver> observers = new ArrayList<>();

    private final static Map<MapDirection, String> directionFilename = new HashMap<>(){{
        put(MapDirection.NORTH, "up.png");
        put(MapDirection.SOUTH, "down.png");
        put(MapDirection.EAST, "right.png");
        put(MapDirection.WEST, "left.png");
    }};

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
        Vector2d newPosition = null;
        MapDirection newDirection = null;
        switch (direction) {
            case RIGHT -> newDirection = this.direction.next();
            case LEFT -> newDirection = this.direction.previous();
            case FORWARD -> newPosition = this.position.add(this.direction.toUnitVector());
            case BACKWARD -> newPosition = this.position.subtract(this.direction.toUnitVector());
        }
        if(newDirection != null){
            this.direction = newDirection;
            positionChanged(position);
        }
        else if (newPosition != null && map.canMoveTo(newPosition)){
            Vector2d oldPosition = position.copy();
//            if (map.objectAt(newPosition) instanceof Grass grass){
//                this.position = newPosition;
//                positionChanged(oldPosition);
//                grass.setNewRandomPosition();
//                System.out.println("Trawa wędruje z "+this.position.toString()+" do "+grass.getPosition());
//            }
//            else{
            this.position = newPosition;
            positionChanged(oldPosition);
//            }
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

    private void positionChanged(Vector2d oldPosition){
        for(IPositionChangeObserver observer: observers)
            observer.positionChanged(oldPosition, position);
    }

    @Override
    public String getImagePath() {
        return resourcesPath + directionFilename.get(this.direction);
    }

    @Override
    public String getLabelName(){
        return "Z " + this.position;
    }
}
