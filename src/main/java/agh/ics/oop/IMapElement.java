package agh.ics.oop;

public interface IMapElement {
    /**
     * Return position of the element
     */
    Vector2d getPosition();

    /**
     * Returns priority of the element to be considered occupant of a given field
     */
    int getPriority();

    /**
     * Return the symbol of the object as a string of length 1.
     */
    String toString();
}
