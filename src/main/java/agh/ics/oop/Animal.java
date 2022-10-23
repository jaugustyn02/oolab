package agh.ics.oop;

public class Animal {
    private Vector2d position = new Vector2d(2,2);
    private MapDirection direction = MapDirection.NORTH;
    private Vector2d new_position = null;
    private static final Vector2d mapLowerLeft = new Vector2d(0, 0);
    private static final Vector2d mapUpperRight = new Vector2d(4, 4);

    @Override
    public String toString(){
        return position.toString() + " " + direction.toString();
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
        if (new_position != null && new_position.precedes(mapUpperRight) && new_position.follows(mapLowerLeft)){
            this.position = new_position;
            new_position = null;
        }
    }
}
