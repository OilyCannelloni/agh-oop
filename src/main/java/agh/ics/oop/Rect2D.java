package agh.ics.oop;

public class Rect2D {
    public Vector2d lowerLeft;
    public Vector2d upperRight;

    public Rect2D(Vector2d ll, Vector2d ur){
        this.lowerLeft = ll;
        this.upperRight = ur;
    }

    public boolean contains(Vector2d point) {
        return point.follows(this.lowerLeft) && point.precedes(this.upperRight);
    }

    public Vector2d getDimensions() {
        return new Vector2d(upperRight.x - lowerLeft.x + 1, upperRight.y - lowerLeft.y + 1);
    }
}
