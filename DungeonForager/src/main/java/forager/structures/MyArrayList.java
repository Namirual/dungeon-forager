package forager.structures;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Partial implementation of a dynamic array.
 *
 * @author lmantyla
 * @param <Obj> Generic object type.
 */
public class MyArrayList<Obj> implements Iterable<Obj> {

    private Object[] objects;
    private int size;

    /**
     * Creates a new ArrayList.
     */
    public MyArrayList() {
        this.objects = new Object[5];
        this.size = 0;
    }

    /**
     * Adds objects to the ArrayList and dynamically increases the size of the
     * list as needed.
     *
     * @param object object to be added
     * @return true when successful
     */
    public boolean add(Object object) {
        if (size >= objects.length) {
            Object[] newArray = new Object[objects.length * 2];
            copyToArray(newArray, objects);
            objects = newArray;
        }
        objects[size] = object;
        size++;
        return true;
    }

    /**
     * Returns an object from given index of the array
     *
     * @param index index being searched
     * @return object found
     */
    public Obj get(int index) {
        if (index < size) {
            return (Obj) objects[index];
        }
        return null;
    }

    /**
     * Returns an object from given index of the array. System.arraycopy is used
     * for the shift.
     *
     * @param index index being searched
     * @return object found
     */
    private void copyToArray(Object[] array, Object[] copied) {
        System.arraycopy(copied, 0, array, 0, copied.length);
        /*for (int val = 0; val < copied.length; val++) {
            array[val] = copied[val];
        }*/
    }

    /**
     * Removes and returns the object at a given index from the list. Shifts the
     * following indices to replace the removed index. System.ArrayCopy is used
     * for the shift.
     *
     * @param index index to be removed
     * @return object that was removed.
     */
    public Obj remove(int index) {
        Object removed;

        if (index < 0) {
            return null;
        }
        if (index > size) {
            System.out.println("No such object!");
            return null;
        }
        removed = objects[index];
        if (index == size - 1) {
            size--;
        } else {
            int amount = size - index;
            System.arraycopy(objects, index + 1, objects, index, amount);

            /*while (index + 1 < size) {
                objects[index] = objects[index + 1];
                index++;

            }*/
            size--;
        }

        return (Obj) removed;
    }

    /**
     * Creates an iterator.
     */
    @Override
    public Iterator<Obj> iterator() {
        return new Iterator<Obj>() {
            private int pos = 0;

            @Override
            public boolean hasNext() {
                return size > pos;
            }

            @Override
            public Obj next() {
                if (hasNext()) {
                    return (Obj) objects[pos++];
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove an element of an array.");
            }
        };
    }

    /**
     * Returns the number of objects in the array.
     *
     * @return number of objects
     */
    public int size() {
        return size;
    }

}
