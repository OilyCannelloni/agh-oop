package agh.ics.oop;

public class Animal {
    private MapDirection facing;
    private Vector2d position;
    public Animal(){
        this.facing = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }

    public String toString(){
        return String.format("Pozycja: %s\tOrientacja: %s", this.position, this.facing);
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
        if (target.precedes(new Vector2d(4, 4)) && target.follows(new Vector2d(0, 0))) {
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
