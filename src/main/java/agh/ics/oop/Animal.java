package agh.ics.oop;

import java.util.ArrayList;

public class Animal extends AbstractWorldMapElement {
    private MapDirection facing;
    private final IWorldMap map;
    protected ArrayList<IPositionChangeObserver> observers;

    public Animal(IWorldMap map, Vector2d initialPosition){
        super(initialPosition);
        this.map = map;
        this.facing = MapDirection.NORTH;
        this.priority = 100;
        this.observers = new ArrayList<>();
        this.addObserver((IPositionChangeObserver) map);
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
