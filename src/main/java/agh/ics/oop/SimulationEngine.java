package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine {
    private final MoveDirection[] moves;
    private final int animalCount;
    private final ArrayList<Animal> animals;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] animalPositions){
        this.moves = moves;
        this.animals = new ArrayList<>();
        for (Vector2d animalPosition : animalPositions) {
            Animal animal = new Animal(map, animalPosition);
            map.place(animal);
            this.animals.add(animal);
        }
        this.animalCount = animalPositions.length;
    }

    public void run() {
        for (int i = 0; i < this.moves.length; i++){
            int animalID = i % this.animalCount;
            Animal animal = this.animals.get(animalID);
            animal.move(this.moves[i]);
        }
    }
}
