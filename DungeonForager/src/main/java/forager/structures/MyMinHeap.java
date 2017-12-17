package forager.structures;

import java.util.Comparator;
import java.util.Queue;

/**
 * Implementation of heap.
 *
 * @author lmantyla
 * @param <Obj> generic object.
 */
public class MyMinHeap<Obj> {

    private Object[] objects;
    private int size;
    private final Comparator<Obj> comparator;

    /**
     * Initialises a heap of size 0 and an array sized 2000001.
     *
     * @param comparator comparator for the object type.
     */
    public MyMinHeap(Comparator<Obj> comparator) {
        this.objects = new Object[2000001];
        this.size = 0;
        this.comparator = comparator;
    }

    /**
     * Adds a single object to the array and increases the size of the object.
     *
     * @param object Object to be added.
     * @return true when successful.
     */
    public boolean add(Obj object) {
        size++;
        int position = size;

        while (position > 1) {
            int parent = getParent(position);
            if (comparator.compare((Obj) objects[parent], (Obj) object) <= 0) {
                break;
            }
            objects[position] = objects[parent];
            position = getParent(position);
        }
        objects[position] = object;
        return true;
    }

    private int getLeft(int x) {
        return x * 2;
    }

    private int getRight(int x) {
        return (x * 2) + 1;
    }

    private int getParent(int x) {
        return x / 2;
    }

    /**
     * An iterative version of heapify. In the loop, the smaller child of the
     * position under scrutiny is moved to its parent until neither child is
     * smaller than the starting object.
     *
     * @param movedPos Position in the array where an object was moved,
     * requiring heapify.
     */
    private void heapify(int movedPos) {
        int position = movedPos;
        Obj movedObject = (Obj) objects[movedPos];

        while (true) {
            int left = getLeft(position);
            int right = left + 1;

            if (right <= size) {
                int smallerChild;
                Obj smallerChildObject = (Obj) objects[left];

                if (comparator.compare(smallerChildObject, (Obj) objects[right]) > 0) {
                    smallerChild = right;
                    smallerChildObject = (Obj) objects[right];
                } else {
                    smallerChild = left;
                }
                if (comparator.compare(movedObject, smallerChildObject) > 0) {
                    objects[position] = objects[smallerChild];
                    position = smallerChild;
                    continue;
                }
            } else if (left == size) {
                if (comparator.compare(movedObject, (Obj) objects[left]) > 0) {
                    objects[position] = objects[left];
                    objects[left] = movedObject;
                    break;
                }
            }
            objects[position] = movedObject;
            break;
        }
    }

    /**
     * Returns the number of objects in the heap.
     *
     * @return number of objects in the heap.
     */
    public int size() {
        return size;
    }

    /**
     * Returns the top of the heap without removing it.
     *
     * @return head of the heap.
     */
    public Object peek() {
        return objects[1];
    }

    /**
     * Returns the head of the heap while removing it.
     *
     * @return head of the heap
     */
    public Obj poll() {
        Object polled = objects[1];

        objects[1] = objects[size];
        size--;
        heapify(1);
        return (Obj) polled;
    }

    /**
     * Makes the heap appear empty by returning the value of size to 0.
     */
    public void empty() {
        size = 0;
    }

    /**
     * Checks if empty.
     *
     * @return true if empty
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
}
