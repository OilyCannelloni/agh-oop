package agh.ics.oop;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;

public class SortedList<T> extends LinkedList<T> {
    private final Comparator<T> cmp;

    public SortedList(Comparator<T> cmp) {
        super();
        this.cmp = cmp;
    }

    public void insert(T element) {
        ListIterator<T> iter = listIterator();
        while (true) {
            if (!iter.hasNext()) {
                iter.add(element);
                return;
            }

            T nextListElem = iter.next();
            if (this.cmp.compare(nextListElem, element) > 0) {
                iter.previous();
                iter.add(element);
                return;
            }
        }
    }
}
