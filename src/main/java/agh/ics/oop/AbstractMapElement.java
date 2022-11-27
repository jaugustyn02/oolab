package agh.ics.oop;

abstract public class AbstractMapElement implements IMapElement {
    protected final static String resourcesPath = "src/main/resources/";
    @Override
    abstract public Vector2d getPosition();

    @Override
    abstract public String toString();

    @Override
    abstract public String getImagePath();

    @Override
    abstract public String getLabelName();
}
