package agh.ics.oop;

import agh.ics.oop.gui.App;

import java.util.ArrayList;

public class SimulationEngine implements IEngine, Runnable {
    private MoveDirection[] moves;
    private final int animalCount, delay;
    private final ArrayList<Animal> animals;

    public SimulationEngine(App app, MoveDirection[] moves, IWorldMap map, Vector2d[] animalPositions, int delay){
        this.delay = delay;
        this.animals = new ArrayList<>();
        for (Vector2d animalPosition : animalPositions) {
            Animal animal = new Animal(app, map, animalPosition);
            map.place(animal);
            this.animals.add(animal);
        }
        this.animalCount = animalPositions.length;
    }

    public void setMoves(ArrayList<MoveDirection> moves) {
        this.moves = moves.toArray(new MoveDirection[0]);
    }

    public void run() {
        for (int i = 0; i < this.moves.length; i++){
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int animalID = i % this.animalCount;
            Animal animal = this.animals.get(animalID);
            animal.move(this.moves[i]);
        }
    }

    public void run(BetterMapVisualizer visualizer) {
        visualizer.draw();

        for (int i = 0; i < this.moves.length; i++){
            int animalID = i % this.animalCount;
            Animal animal = this.animals.get(animalID);
            animal.move(this.moves[i]);
            visualizer.draw();
        }
    }
}
