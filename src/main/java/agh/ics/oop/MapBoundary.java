package agh.ics.oop;

import java.util.LinkedList;

final class IndicesPair {
    private final int xi;
    private final int yi;

    public IndicesPair(int xi, int yi) {
        this.xi = xi;
        this.yi = yi;
    }
    public int getXI() {
        return xi;
    }
    public int getYI() {
        return yi;
    }
}

public class MapBoundary implements IPositionChangeObserver{
    private final LinkedList<GrassFieldObject> objectsOX = new LinkedList<>();
    private final LinkedList<GrassFieldObject> objectsOY = new LinkedList<>();

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        removeObject(oldPosition, ObjectType.Animal);
        addObject(newPosition, ObjectType.Animal);
    }

    private IndicesPair getObjectIndices(GrassFieldObject objectToFind){
        int xi=objectsOX.size(), yi=objectsOY.size();
        int i = 0;
        for(GrassFieldObject object: objectsOX) {
            if (object.compareTo(objectToFind, Axis.OX) <= 0){
                xi = i;
                break;
            }
            i++;
        }
        i = 0;
        for(GrassFieldObject object: objectsOY){
            if(object.compareTo(objectToFind, Axis.OY) <= 0){
                yi = i;
                break;
            }
            i++;
        }
        return new IndicesPair(xi, yi);
    }

    protected void addObject(Vector2d position, ObjectType type){
        GrassFieldObject newObject = new GrassFieldObject(position, type);
        IndicesPair indices = getObjectIndices(newObject);
        this.objectsOX.add(indices.getXI(), newObject);
        this.objectsOY.add(indices.getYI(), newObject);
    }

    private void removeObject(Vector2d position, ObjectType type){
        IndicesPair indices = getObjectIndices(new GrassFieldObject(position, type));
        this.objectsOX.remove(indices.getXI());
        this.objectsOY.remove(indices.getYI());
    }

    public Vector2d lowerLeft(){
        if(this.objectsOX.isEmpty()) return new Vector2d(0, 0);
        return new Vector2d(this.objectsOX.getLast().getPosition().x, this.objectsOY.getLast().getPosition().y);
    }

    public Vector2d upperRight(){
        if(this.objectsOX.isEmpty()) return new Vector2d(0, 0);
        return new Vector2d(this.objectsOX.get(0).getPosition().x, this.objectsOY.get(0).getPosition().y);
    }

    public void printAll(){
        System.out.println(this.objectsOX);
        System.out.println(this.objectsOY);
        System.out.println();
    }
}
