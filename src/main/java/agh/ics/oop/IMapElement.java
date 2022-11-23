package agh.ics.oop;

public interface IMapElement {
    Vector2d getPosition();
    ObjectType getObjectType();

    int compareTo(GrassFieldObject otherObject, Axis ax);
}
