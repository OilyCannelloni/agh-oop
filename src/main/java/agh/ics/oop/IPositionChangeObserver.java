package agh.ics.oop;

public interface IPositionChangeObserver {
    void positionChanged(Vector2d oldPosition, Vector2d newPosition);
    void positionUpdated(Vector2d position);
}
