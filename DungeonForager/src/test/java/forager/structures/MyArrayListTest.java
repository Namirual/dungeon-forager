package forager.structures;

import java.util.Comparator;
import java.util.Iterator;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author lmantyla
 */
public class MyArrayListTest {

    MyArrayList<Integer> list;

    @Before
    public void setUp() {
        list = new MyArrayList<Integer>();
    }

    @Test
    public void canAddToList() {
        list.add(2);
        assertEquals(list.get(0), (Integer) 2);
        assertEquals(list.size(), 1);
    }

    @Test
    public void canRemoveFromList() {
        list.add(2);
        Integer value = list.remove(0);

        assertEquals(value, (Integer) 2);
        assertEquals(list.size(), 0);
    }

    @Test
    public void canAddManyToList() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);

        assertEquals(list.get(6), (Integer) 7);
        assertEquals(list.size(), 7);
    }

    @Test
    public void iteratorFunctions() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        int total = 0;
        for (Integer integer : list) {
            total += integer;
        }
        assertEquals(total, 10);
    }

    @Test
    public void removingWhileIteratingWorks() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        int removed = 0;

        for (int val = 3; val >= 0; val--) {
            removed = list.remove(val);
        }
        assertEquals(removed, 1);
        assertEquals(list.size(), 0);
    }

    @Test
    public void iteratorHasNextWorks() {
        list.add(1);
        list.add(2);

        Iterator<Integer> iterator = list.iterator();
        assertEquals(iterator.hasNext(), true);

        assertEquals(iterator.next(), (Integer) 1);
        assertEquals(iterator.next(), (Integer) 2);

        assertEquals(iterator.hasNext(), false);
    }

    @Test
    public void removingLessThanZeroRemovesNothing() {
        list.add(1);
        list.add(2);

        Integer integer = list.remove(-1);

        assertEquals(integer, null);
        assertEquals(list.size(), 2);
    }

    @Test
    public void removingNumberNotOnListRemovesNothing() {
        list.add(1);
        list.add(2);

        Integer integer = list.remove(3);

        assertEquals(integer, null);
        assertEquals(list.size(), 2);
    }

    @Test
    public void canRemoveValueFromMiddle() {
        list.add(1);
        list.add(2);
        list.add(null);
        list.add(4);

        for (int val = 3; val >= 0; val--) {
            if (list.get(val) == null) {
                list.remove(val);
            }
        }
        assertEquals(list.size(), 3);
    }
}
