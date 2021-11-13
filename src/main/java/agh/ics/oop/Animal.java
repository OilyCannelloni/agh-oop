package agh.ics.oop;

public class Animal {
    private MapDirection facing;
    private Vector2d position;
    private IWorldMap map;

    public Animal(){
        this.facing = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }

    public Animal(IWorldMap map){
        this.map = map;
        this.position = new Vector2d(0, 0);
        this.facing = MapDirection.NORTH;
        this.map.place(this);
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.position = initialPosition;
        this.facing = MapDirection.NORTH;
    }

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
            this.position = target;
        }
    }

    public Vector2d getPosition(){
        return this.position;
    }

    public MapDirection getFacing(){
        return this.facing;
    }
}
