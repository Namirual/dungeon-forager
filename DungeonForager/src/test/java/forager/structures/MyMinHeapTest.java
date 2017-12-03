/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager.structures;

import java.util.Comparator;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import org.junit.Before;

/**
 *
 * @author lmantyla
 */
public class MyMinHeapTest {

    MyMinHeap heap;

    private class IntegerComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    @Before
    public void setUp() {
        IntegerComparator comparator = new IntegerComparator();
        heap = new MyMinHeap<Integer>(comparator);
    }

    @Test
    public void canAddToHeap() {
        heap.add(2);
        assertEquals(heap.peek(), 2);
        assertEquals(heap.size(), 1);
    }

    @Test
    public void addingPutsSmallestAtTheBeginning() {
        heap.add(2);
        heap.add(0);
        heap.add(1);
        assertEquals(heap.peek(), 0);
    }

    @Test
    public void canPollMultipleTimesInCorrectOrder() {
        heap.add(5);
        heap.add(3);
        heap.add(12);
        heap.add(9);
        heap.add(2);
        heap.add(0);
        heap.add(1);
        assertEquals(heap.poll(), 0);
        assertEquals(heap.poll(), 1);
        assertEquals(heap.poll(), 2);
    }

    @Test
    public void knowsWhenEmpty() {
        heap.add(2);
        heap.poll();
        assertTrue(heap.isEmpty());
    }
}
