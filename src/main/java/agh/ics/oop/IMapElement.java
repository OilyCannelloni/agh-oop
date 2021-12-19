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
     * Processes actions when this element got in contact with another element
     * @param interactingElement the second element
     */
    void onInteraction(IMapElement interactingElement);

    /**
     * Return the symbol of the object as a string of length 1.
     */
    String toString();

    String getImagePath();
}
