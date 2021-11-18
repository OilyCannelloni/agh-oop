package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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

    public Vector2d getRandomEmptyTile(Rect2D area){
        int width = area.getDimensions().x;
        int height = area.getDimensions().y;
        assert width > 0 && height > 0;
        int flatAreaLength = width*height;

        int nObjects = mapElements.size();
        int[] occupiedPositions = new int[nObjects + 1];
        int oP = 0;
        for (IMapElement element : this.mapElements){
            Vector2d position = element.getPosition();
            if (!area.contains(position)) continue;
            occupiedPositions[oP++] = position.toLinear(area);
        }
        occupiedPositions[nObjects] = -1;
        Arrays.sort(occupiedPositions);

        int[] availablePositions = new int[flatAreaLength];
        int aP = 0; oP = 0;
        for (int i = 0; i < flatAreaLength; i++) {
            if (i != occupiedPositions[oP]) availablePositions[aP++] = i;
            else oP++;
        }

        shuffleArray(availablePositions, 1);
        return new Vector2d(availablePositions[0], area);
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

    public boolean placeElement(IMapElement element){
        Vector2d position = element.getPosition();
        if (this.isOccupied(position)) return false;
        this.mapElements.add(element);
        return true;
    }

    public void removeElement(IMapElement element) {
        if (!this.mapElements.contains(element))
            System.out.printf("Cannot remove grass from %s: it does not exist!", element.getPosition());
        this.mapElements.remove(element);
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
