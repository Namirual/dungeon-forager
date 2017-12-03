/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager.structures;

import java.util.Comparator;
import java.util.Queue;

/**
 * Implementation of heap.
 * @author lmantyla
 * @param <Obj>
 */
public class MyMinHeap<Obj> {

    private Object[] objects;
    private int size;
    private final Comparator<Obj> comparator;

    public MyMinHeap(Comparator<Obj> comparator) {
        this.objects = new Object[1000000];
        this.size = 0;
        this.comparator = comparator;
    }

    public boolean add(Object object) {
        size++;
        int position = size;
        objects[size] = object;
        while (position > 1 && isBigger(getParent(position), position)) {
            //System.out.println("position " + position + " " + objects[getParent(position)].toString());
            
            objects[position] = objects[getParent(position)];
            position = getParent(position);
            objects[position] = object;
        }
        return true;
    }

    private int getLeft(int x) {
        return x * 2;
    }

    private int getRight(int x) {
        return (x * 2) + 1;
    }

    private int getParent(int x) {
        /*if (x % 2 == 1) {
            return (x - 1) / 2;
        } else {*/
            return x / 2;
        //}
    }

    private boolean isBigger(int x, int y) {
        if (comparator.compare((Obj) objects[x], (Obj) objects[y]) > 0) {
            return true;
        }
        /*if (objects[x].compareTo(objects[y]) > 0) {
            return true;
        }*/
        return false;
    }

    private void heapify(int val) {
        if (getRight(val) <= size) {
            int smallerChild;

            if (isBigger(getLeft(val), getRight(val))) {
                smallerChild = getRight(val);
            } else {
                smallerChild = getLeft(val);
            }
            if (isBigger(val, smallerChild)) {
                exchange(val, smallerChild);
                heapify(smallerChild);
            }
        } else if (getLeft(val) == size) {
            if (isBigger(val, getLeft(val))) {
                exchange(val, getLeft(val));
            }
        }
    }

    private void exchange(int x, int y) {
        Object obj = objects[x];
        objects[x] = objects[y];
        objects[y] = obj;
    }

    public int size() {
        return size;
    }
    
    public Object peek() {
        return objects[1];
    }

    public Obj poll() {
        Object polled = objects[1];
        
        objects[1] = objects[size];
        size--;
        heapify(1);
        return (Obj) polled;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
}
