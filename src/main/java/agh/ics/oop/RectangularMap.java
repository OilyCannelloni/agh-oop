package agh.ics.oop;


public class RectangularMap extends AbstractWorldMap {
    private final Vector2d origin, boundingBox;

    public RectangularMap(int width, int height){
        super();
        assert width > 0 && height > 0;
        this.origin = new Vector2d(0, 0);
        this.boundingBox = new Vector2d(width - 1, height - 1);
    }

    @Override
    protected boolean isWithinBounds(Vector2d position) {
        return (position.follows(this.origin) && position.precedes(this.boundingBox));
    }

    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(this.origin, this.boundingBox);
    }
}
