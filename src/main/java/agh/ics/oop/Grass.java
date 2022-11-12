package agh.ics.oop;

public class Grass {
    private Vector2d position;
    private final GrassField map;
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
}
