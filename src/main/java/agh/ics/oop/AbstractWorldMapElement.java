package agh.ics.oop;


public abstract class AbstractWorldMapElement implements IMapElement {
    protected Vector2d position;
    protected int priority;
    protected IWorldMap map;

    public AbstractWorldMapElement(IWorldMap map, Vector2d position) {
        this.position = position;
        this.priority = 0;
        this.map = map;
    }

    public AbstractWorldMapElement(Vector2d position) {
        this.position = position;
        this.priority = 0;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public int getPriority() {
        return this.priority;
    }

    public void onInteraction(IMapElement interactingElement) {}

    public String toString() {
        return ".";
    }
}
