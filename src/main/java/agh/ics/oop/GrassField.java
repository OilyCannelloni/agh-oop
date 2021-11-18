package agh.ics.oop;

import java.util.ArrayList;
import java.lang.Math;
import java.util.Random;

public class GrassField implements IWorldMap {
    private final Vector2d origin = new Vector2d(0, 0);
    private final ArrayList<IMapElement> mapElements;
    private final int nGrassTiles;

    public GrassField(int nGrassTiles){
        this.nGrassTiles = nGrassTiles;
        this.mapElements = new ArrayList<>();
        this.generateGrass();
    }

    private static void shuffleArray(int[] array, int firstN) {
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

    private void generateGrass(){
        int bound = (int) Math.floor(Math.sqrt(10*this.nGrassTiles)) + 1;
        int[] range = new int[bound*bound];
        for (int i = 0; i < bound*bound; i++) range[i] = i;
        shuffleArray(range, this.nGrassTiles);
        for (int i = 0; i < nGrassTiles; i++) {
            Vector2d tilePosition = new Vector2d(range[i] % bound, range[i] / bound);
            this.mapElements.add(new Grass(tilePosition));
        }
    }

    public boolean canMoveTo(Vector2d position) {
        if (!position.follows(this.origin)) return false;
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

    public String toString() {
        int max_x = 0, max_y = 0;
        for (IMapElement element : this.mapElements) {
            Vector2d position = element.getPosition();
            max_x = Math.max(max_x, position.x);
            max_y = Math.max(max_y, position.y);
        }

        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(this.origin, new Vector2d(max_x, max_y));
    }
}
