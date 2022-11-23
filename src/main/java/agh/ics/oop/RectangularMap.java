package agh.ics.oop;

public class RectangularMap extends AbstractWorldMap{
    private final static Vector2d lowerLeft = new Vector2d(0, 0);
    private final Vector2d upperRight;

    public RectangularMap(int width, int height){
        this.upperRight = new Vector2d(width-1, height-1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.precedes(upperRight) && position.follows(lowerLeft)
                && !this.isOccupied(position);
    }

    @Override
    public Vector2d lowerLeft(){
        return lowerLeft;
    }

    @Override
    public Vector2d upperRight(){
        return upperRight;
    }
}
