/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager.structures;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author lmantyla
 */
public class MyArrayList<Obj> implements Iterable<Obj> {

    private Object[] objects;
    private int size;

    public MyArrayList() {
        this.objects = new Object[5];
        this.size = 0;
    }

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

    public Obj get(int number) {
        if (number < size) {
            return (Obj) objects[number];
        }
        return null;
    }

    private void copyToArray(Object[] array, Object[] copied) {
        for (int val = 0; val < copied.length; val++) {
            array[val] = copied[val];
        }
    }

    public Obj remove(int number) {
        Object removed;

        if (number < 0) {
            return null;
        }
        if (number > size) {
            System.out.println("No such object!");
            return null;
        }
        removed = objects[number];
        if (number == size) {
            size--;
        } else {
            while (number + 1 < size) {
                objects[number] = objects[number + 1];
                number++;

            }
            size--;
        }

        return (Obj) removed;
    }

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

    public int size() {
        return size;
    }

}
