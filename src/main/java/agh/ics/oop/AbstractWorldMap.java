package agh.ics.oop;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Map<Vector2d, IMapElement> mapElements;
    protected MapBoundary mapBoundary;

    public AbstractWorldMap(){
        this.mapElements = new LinkedHashMap<>();
        this.mapBoundary = new MapBoundary();
    }

    protected boolean isWithinBounds(Vector2d position) {
        return true;
    }

    protected static void shuffleArray(int[] array, int firstN) {
        if (firstN >= array.length) return;
        int index, temp;
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < firstN; i++) {
            index = i + random.nextInt(array.length - i);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        IMapElement element =  this.removeElement(oldPosition);
        this.placeElement(element, newPosition);
    }

    public Vector2d getRandomEmptyTile(Rect2D area){
        Vector2d position;
        int trials = 1000;
        do {
            position = getRandomTile(area);
            if (--trials == 0) {
                return null;
            }
        } while (this.isOccupied(position));
        return position;
    }

    public static Vector2d getRandomTile(Rect2D region) {
        Random random = new Random(System.currentTimeMillis());
        return new Vector2d(
                region.lowerLeft.x + random.nextInt(region.getDimensions().x),
                region.lowerLeft.y + random.nextInt(region.getDimensions().y)
        );
    }

    public boolean canMoveTo(Vector2d position) {
        if (!this.isWithinBounds(position)) return false;
        for (Map.Entry<Vector2d, IMapElement> entry : this.mapElements.entrySet()) {
            if (entry.getValue() instanceof Animal && entry.getKey().equals(position)) return false;
        }
        return true;
    }

    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (!this.canMoveTo(position))
            throw new IllegalArgumentException("Cannot place animal at " + position);
        this.mapElements.put(position, animal);
        this.mapBoundary.insert(animal);
        return true;
    }

    public boolean placeElement(IMapElement element){
        Vector2d position = element.getPosition();
        if (this.isOccupied(position))
            throw new IllegalArgumentException("Cannot place element at " + position);
        this.mapElements.put(position, element);
        this.mapBoundary.insert(element);
        return true;
    }

    public void placeElement(IMapElement element, Vector2d position){
        if (
                this.isOccupied(position)
                && this.mapElements.get(position).getPriority() <= element.getPriority()
        ) throw new IllegalArgumentException("Cannot place element at " + position);
        this.mapElements.put(position, element);
        this.mapBoundary.insert(element);
    }

    public IMapElement removeElement(Vector2d position) {
        IMapElement element = this.mapElements.remove(position);
        if (element == null)
            throw new IllegalArgumentException("No element to remove at " + position);
        this.mapBoundary.remove(element);
        return element;
    }

    public boolean isOccupied(Vector2d position) {
        return this.mapElements.get(position) != null;
    }

    public Object objectAt(Vector2d position) {
        return this.mapElements.get(position);
    }

    public String toString(){
        Rect2D boundingBox = this.mapBoundary.getBoundingBox();
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(boundingBox.lowerLeft, boundingBox.upperRight);
    }

    public Rect2D getBoundingBox() {
        return this.mapBoundary.getBoundingBox();
    }

    public void positionUpdated(Vector2d position) {}
}
