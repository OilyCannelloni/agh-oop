package agh.ics.oop;

import java.util.ArrayList;
import java.lang.Math;
import java.util.Random;

public class GrassField implements IWorldMap {
    private final Vector2d origin = new Vector2d(0, 0);
    private final ArrayList<Animal> animals;
    private ArrayList<Grass> grassTiles;
    private int nGrassTiles;

    public GrassField(int nGrassTiles){
        this.animals = new ArrayList<>();
        this.nGrassTiles = nGrassTiles;
        this.grassTiles = new ArrayList<>();
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
            this.grassTiles.add(new Grass(tilePosition));
        }
    }

    public boolean canMoveTo(Vector2d position) {
        if (!position.follows(this.origin)) return false;
        for (Animal animal : this.animals) {
            if (animal.getPosition().equals(position)) return false;
        }
        return true;
    }

    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (this.isOccupied(position)) return false;
        this.animals.add(animal);
        return true;
    }

    public boolean isOccupied(Vector2d position) {
        for (Animal animal : this.animals) {
            Vector2d aniPos = animal.getPosition();
            if (aniPos.equals(position)) return true;
        }

        for (Grass grass : this.grassTiles) {
            Vector2d grassPos = grass.getPosition();
            if (grassPos.equals(position)) return true;
        }
        return false;
    }

    public Object objectAt(Vector2d position) {
        for (Animal animal : this.animals) {
            Vector2d animalPosition = animal.getPosition();
            if (animalPosition.equals(position)) return animal;
        }

        for (Grass grass : this.grassTiles) {
            Vector2d grassPosition = grass.getPosition();
            if(grassPosition.equals(position)) return grass;
        }

        return null;
    }

    public String toString() {
        int max_x = 0, max_y = 0;
        for (Grass grass : this.grassTiles) {
            Vector2d position = grass.getPosition();
            max_x = Math.max(max_x, position.x);
            max_y = Math.max(max_y, position.y);
        }
        for (Animal animal : this.animals) {
            Vector2d position = animal.getPosition();
            max_x = Math.max(max_x, position.x);
            max_y = Math.max(max_y, position.y);
        }

        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(this.origin, new Vector2d(max_x, max_y));
    }
}
