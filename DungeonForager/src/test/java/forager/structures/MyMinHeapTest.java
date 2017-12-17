package forager.structures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
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
        assertEquals(heap.poll(), 3);
        assertEquals(heap.poll(), 5);
        assertEquals(heap.poll(), 9);
        assertEquals(heap.poll(), 12);
    }

    @Test
    public void addsAndPollsLargeAmounts() {
        Random random = new Random();

        ArrayList<Integer> testList = new ArrayList<>();
        for (int val = 0; val < 100; val++) {
            testList.add(new Integer(random.nextInt(1000000)));
        }

        for (Integer integer : testList) {
            heap.add(integer);
        }

        testList.sort(new IntegerComparator());

        for (int val = 0; val < testList.size(); val++) {
            assertEquals(testList.get(val), heap.poll());
        }
    }

    @Test
    public void knowsWhenEmpty() {
        heap.add(2);
        heap.poll();
        assertTrue(heap.isEmpty());
    }
}
