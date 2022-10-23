package agh.ics.oop;

// 10.
//import java.util.HashSet;
//import java.util.Set;
// 10. end

public class Animal {
    private Vector2d position = new Vector2d(2,2);
    private MapDirection direction = MapDirection.NORTH;
    private Vector2d new_position = null;
    private static final Vector2d mapLowerLeft = new Vector2d(0, 0);
    private static final Vector2d mapUpperRight = new Vector2d(4, 4);

    // 10. - Animals cannot stand in the same place
//    private static Set<Vector2d> fieldsOccupation = new HashSet<Vector2d>();
    // 10. end

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

            // 10.
//            if (fieldsOccupation.contains(new_position)){
//                System.out.println("Na polu: "+new_position.toString()+" znajduję się już inne zwierzę");
//            }
//            else{
//                fieldsOccupation.add(new_position);
//                fieldsOccupation.remove(this.position);
//                this.position = new_position;
//                new_position = null;
//            }
            // 10. end
        }
    }
}
