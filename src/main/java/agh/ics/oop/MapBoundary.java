package agh.ics.oop;

import java.util.ListIterator;

public class MapBoundary implements IPositionChangeObserver {
    private final SortedList<IMapElement> xElementList, yElementList;

    public MapBoundary() {
        this.xElementList = new SortedList<>((e1, e2) -> {
            Vector2d p1 = e1.getPosition();
            Vector2d p2 = e2.getPosition();
            if (p1.x > p2.x) return 1;
            else if (p1.x == p2.x && p1.y > p2.y) return 1;
            else if (p1.y == p2.y) return (e1.getPriority() - e2.getPriority());
            else return -1;
        });

        this.yElementList = new SortedList<>((e1, e2) -> {
            Vector2d p1 = e1.getPosition();
            Vector2d p2 = e2.getPosition();
            if (p1.y > p2.y) return 1;
            else if (p1.y == p2.y && p1.x > p2.x) return 1;
            else if (p1.x == p2.x) return (e1.getPriority() - e2.getPriority());
            else return -1;
        });
    }

    public void insert(IMapElement element) {
        this.xElementList.insert(element);
        this.yElementList.insert(element);
    }

    public void remove(IMapElement element) {
        this.xElementList.remove(element);
        this.yElementList.remove(element);
    }

    private void update(SortedList<IMapElement> list, Vector2d position) {
        ListIterator<IMapElement> iter = list.listIterator();
        IMapElement target = null;
        while (iter.hasNext()) {
            target = iter.next();
            if (target.getPosition().equals(position)) {
                list.remove(iter.previousIndex());
                break;
            }
        }
        list.insert(target);
    }

    public Rect2D getBoundingBox() {
        return new Rect2D(
                new Vector2d(
                        this.xElementList.getFirst().getPosition().x,
                        this.yElementList.getFirst().getPosition().y
                ),
                new Vector2d(
                        this.xElementList.getLast().getPosition().x,
                        this.yElementList.getLast().getPosition().y
                )
        );
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        this.update(this.xElementList, newPosition);
        this.update(this.yElementList, newPosition);
    }
}
