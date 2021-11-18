package agh.ics.oop;

import java.lang.Math;
import java.util.Random;

public class GrassField extends AbstractWorldMap {
    private final Vector2d origin;
    private final int nGrassTiles;

    public GrassField(int nGrassTiles){
        super();
        this.origin = new Vector2d(0, 0);
        this.nGrassTiles = nGrassTiles;
        this.generateGrass();
    }

    @Override
    protected boolean isWithinBounds(Vector2d position){
        return position.follows(this.origin);
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
