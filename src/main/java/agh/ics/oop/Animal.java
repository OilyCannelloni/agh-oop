package agh.ics.oop;

import agh.ics.oop.gui.App;

import java.util.ArrayList;

public class Animal extends AbstractWorldMapElement {
    private MapDirection facing;
    private final IWorldMap map;
    protected ArrayList<IPositionChangeObserver> observers;

    public Animal(App app, IWorldMap map, Vector2d initialPosition){
        super(initialPosition);
        this.map = map;
        this.facing = MapDirection.NORTH;
        this.priority = 100;
        this.observers = new ArrayList<>();
        this.addObserver((IPositionChangeObserver) map);
        if (app != null) this.addObserver(app);
    }

    public void addObserver(IPositionChangeObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        this.observers.remove(observer);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver observer : this.observers) {
            observer.positionChanged(oldPosition, newPosition);
        }
    }

    @Override
    public String toString(){
        return this.facing.toString();
    }

    @Override
    public String getImagePath() {
        String dirChar = "";
        switch (this.facing) {
            case NORTH: dirChar = "n"; break;
            case WEST: dirChar = "w"; break;
            case SOUTH: dirChar = "s"; break;
            case EAST: dirChar = "e"; break;
        }

        return "file:.\\src\\main\\resources\\animal_" + dirChar + ".png";
    }

    public void move(MoveDirection direction){
        Vector2d target = new Vector2d(this.position.x, this.position.y);
        switch (direction) {
            case LEFT:
                this.facing = this.facing.previous();
                break;
            case RIGHT:
                this.facing = this.facing.next();
                break;
            case FORWARD:
                target = this.position.add(this.facing.toUnitVector());
                break;
            case BACKWARD:
                target = this.position.subtract(this.facing.toUnitVector());
                break;
        }
        if (target.equals(this.position)) {
            for (IPositionChangeObserver observer : this.observers) {
                observer.positionUpdated(this.position);
            }
        }

        if (this.map.canMoveTo(target)) {
            Object object = this.map.objectAt(target);
            if (object instanceof IMapElement) {
                IMapElement element = (IMapElement) object;
                element.onInteraction(this);
            }
            this.positionChanged(this.position, target);
            this.position = target;
        }
    }

    public MapDirection getFacing(){
        return this.facing;
    }
}
