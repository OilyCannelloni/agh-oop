package agh.ics.oop;

import java.util.ArrayList;

public abstract class AbstractWorldMap implements IWorldMap {
    protected ArrayList<IMapElement> mapElements;

    public AbstractWorldMap(){
        this.mapElements = new ArrayList<>();
    }

    protected boolean isWithinBounds(Vector2d position) {
        return true;
    }

    protected Rect2D getMinimalBoundingBox(){
        int maxX = 0, maxY = 0;
        for (IMapElement element : this.mapElements) {
            Vector2d position = element.getPosition();
            maxX = Math.max(maxX, position.x);
            maxY = Math.max(maxY, position.y);
        }
        return new Rect2D(
                new Vector2d(0, 0),
                new Vector2d(maxX, maxY)
        );
    }

    public boolean canMoveTo(Vector2d position) {
        if (!this.isWithinBounds(position)) return false;
        for (IMapElement element : this.mapElements) {
            if (element instanceof Animal && element.getPosition().equals(position)) return false;
        }
        return true;
    }

    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (!this.canMoveTo(position)) return false;
        this.mapElements.add(animal);
        return true;
    }

    public boolean isOccupied(Vector2d position) {
        for (IMapElement element : this.mapElements) {
            Vector2d elementPosition = element.getPosition();
            if (elementPosition.equals(position)) return true;
        }
        return false;
    }

    public Object objectAt(Vector2d position) {
        ArrayList<IMapElement> targets = new ArrayList<>();

        for (IMapElement element : this.mapElements) {
            Vector2d elementPosition = element.getPosition();
            if (elementPosition.equals(position)) targets.add(element);
        }

        int maxPriority = -1;
        IMapElement bestTarget = null;
        for (IMapElement target : targets) {
            int targetPriority = target.getPriority();
            if (targetPriority > maxPriority) {
                maxPriority = targetPriority;
                bestTarget = target;
            }
        }

        return bestTarget;
    }

    public String toString(){
        Rect2D boundingBox = this.getMinimalBoundingBox();
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(boundingBox.lowerLeft, boundingBox.upperRight);
    }
}
