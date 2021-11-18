package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap implements IWorldMap {
    private final Vector2d origin = new Vector2d(0, 0);
    private final Vector2d boundingBox;
    private final ArrayList<IMapElement> mapElements;

    public RectangularMap(int width, int height){
        assert width > 0 && height > 0;
        this.boundingBox = new Vector2d(width - 1, height - 1);
        this.mapElements = new ArrayList<>();
    }

    public boolean canMoveTo(Vector2d position) {
        if (!position.follows(this.origin) || !position.precedes(this.boundingBox)) return false;
        for (IMapElement element : this.mapElements) {
            if (element.getPosition().equals(position)) return false;
        }
        return true;
    }

    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (this.isOccupied(position)) return false;
        this.mapElements.add(animal);
        return true;
    }

    public boolean isOccupied(Vector2d position) {
        for (IMapElement element : this.mapElements) {
            Vector2d aniPos = element.getPosition();
            if (aniPos.equals(position)) return true;
        }
        return false;
    }

    public Object objectAt(Vector2d position) {
        for (IMapElement element : this.mapElements) {
            Vector2d elementPosition = element.getPosition();
            if (elementPosition.equals(position)) return element;
        }
        return null;
    }

    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(this.origin, this.boundingBox);
    }
}
