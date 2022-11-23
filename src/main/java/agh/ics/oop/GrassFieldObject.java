package agh.ics.oop;

public class GrassFieldObject implements IMapElement{
    private Vector2d position;
    private final ObjectType objectType;

    public GrassFieldObject(Vector2d position, ObjectType objectType){
        this.position = position;
        this.objectType = objectType;
    }

    @Override
    public Vector2d getPosition() {
        return this.position.copy();
    }

    @Override
    public ObjectType getObjectType(){
        return this.objectType.getValue();
    }

    public Vector2d changePosition(Vector2d newPosition){
        Vector2d oldPosition = this.position;
        this.position = newPosition;
        return oldPosition;
    }

    @Override
    public int compareTo(GrassFieldObject otherObject, Axis ax){
        Vector2d pos1 = this.position.copy();
        Vector2d pos2 = otherObject.position.copy();
        int i = ax.toInt(), j = (ax.toInt()+1)%2;
        if(pos1.getByIndex(i) < pos2.getByIndex(i)) return -1;
        if(pos1.getByIndex(i) > pos2.getByIndex(i)) return 1;
        if(pos1.getByIndex(j) < pos2.getByIndex(j)) return -1;
        if(pos1.getByIndex(j) > pos2.getByIndex(j)) return 1;
        return this.objectType.compareTo(otherObject.objectType);
    }

    @Override
    public String toString(){
        return this.position.toString() + " - " + objectType.toString();
    }
}
