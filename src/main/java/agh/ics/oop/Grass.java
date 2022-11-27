package agh.ics.oop;

public class Grass extends AbstractMapElement{
    private Vector2d position;
    private final GrassField map;
    private final static String filename = "grass.png";
    public Grass(Vector2d position, GrassField map){
        this.position = position;
        this.map = map;
    }

    public Vector2d getPosition(){
        return this.position;
    }

    public String toString(){
        return "*";
    }

    protected void setNewRandomPosition(){
        this.position = map.randomValidGrassPosition();
    }

    @Override
    public String getImagePath() {
        return resourcesPath + filename;
    }

    @Override
    public String getLabelName(){
        return "Trawa";
    }
}
