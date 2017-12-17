package forager.structures;

import forager.AStarStepComparator;
import forager.domain.Heuristic;
import forager.domain.Step;
import forager.domain.Tile;
import forager.domain.VisitMap;
import forager.structures.VisitTree;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeSet;
import org.junit.Test;

/**
 *
 * @author lmantyla
 */
public class MyMinHeapPerformanceTest {

    final int ITERATIONS = 10;
    final int SEED = 1234;
    final int ADDVALUE = 100000;
    final int MAXVALUE = 2000000;

    //@Test
    public void testAddingToPriorityQueueWithInts() {
        for (int val = ADDVALUE; val <= MAXVALUE; val += ADDVALUE) {
            addToPriorityQueue(new PriorityQueue<>(new IntegerComparator()), generateIntegers(val));
        }
    }

    //@Test
    public void testPollingFromPriorityQueueWithInts() {
        for (int val = ADDVALUE; val <= MAXVALUE; val += ADDVALUE) {
            pollFromPriorityQueue(new PriorityQueue<>(new IntegerComparator()), generateIntegers(val));
        }
    }

    //@Test
    public void testAddingToPriorityQueueWithTiles() {
        Tile goalTile = new Tile(50, 50, 0, 0);
        for (int val = ADDVALUE; val <= MAXVALUE; val += ADDVALUE) {
            addToPriorityQueue(new PriorityQueue<>(new AStarStepComparator(goalTile,
                    Heuristic.Manhattan)), generateSteps(val));
        }
    }

    //@Test
    public void testPollingFromPriorityQueueWithTiles() {
        Tile goalTile = new Tile(50, 50, 0, 0);
        for (int val = ADDVALUE; val <= MAXVALUE; val += ADDVALUE) {
            pollFromPriorityQueue(new PriorityQueue<>(new AStarStepComparator(goalTile,
                    Heuristic.Manhattan)), generateSteps(val));
        }
    }

    //@Test
    public void testAddingToMyMinHeapWithIntegers() {
        for (int val = ADDVALUE; val <= MAXVALUE; val += ADDVALUE) {
            addToMyMinHeap(new MyMinHeap<>(new IntegerComparator()), generateIntegers(val));
        }
    }

    //@Test
    public void testPollingFromMyMinHeapWithIntegers() {
        for (int val = ADDVALUE; val <= MAXVALUE; val += ADDVALUE) {
            pollFromMyMinHeap(new MyMinHeap<>(new IntegerComparator()), generateIntegers(val));
        }
    }

    //@Test
    public void testAddingToMyMinHeapWithTiles() {
        Tile goalTile = new Tile(50, 50, 0, 0);

        for (int val = ADDVALUE; val <= MAXVALUE; val += ADDVALUE) {
            addToMyMinHeap(new MyMinHeap<>(new AStarStepComparator(goalTile,
                    Heuristic.Manhattan)), generateSteps(val));
        }
    }

    //@Test
    public void testPollingFromMyMinHeapWithTiles() {
        Tile goalTile = new Tile(50, 50, 0, 0);

        for (int val = ADDVALUE; val <= MAXVALUE; val += ADDVALUE) {
            pollFromMyMinHeap(new MyMinHeap<>(new AStarStepComparator(goalTile,
                    Heuristic.Manhattan)), generateSteps(val));
        }
    }

    public List<Integer> generateIntegers(int amount) {
        Random random = new Random();
        random.setSeed(SEED);

        ArrayList<Integer> testList = new ArrayList<>();
        for (int val = 0; val < amount; val++) {
            testList.add(random.nextInt(500));
        }

        return testList;
    }

    public List<Step> generateSteps(int amount) {
        Random random = new Random();
        random.setSeed(SEED);
        
        ArrayList<Step> testList = new ArrayList<>();
        for (int val = 0; val < amount; val++) {
            Step step = new Step(new Tile(random.nextInt(70), random.nextInt(70), 1, 1), null, null, 5, random.nextInt(100));
            testList.add(step);
        }

        return testList;
    }

    private class IntegerComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    private long addToMyMinHeap(MyMinHeap myHeap, List testList) {
        int iterations = testList.size();
        System.out.println("Adding to MyMinHeap: " + iterations + " objects");

        int time = 0;
        for (int val = 0; val < ITERATIONS; val++) {
            time += testMyMinHeapInteger(iterations, false, myHeap, testList);
        }
        System.out.println("Average time:" + time / ITERATIONS);

        return time / ITERATIONS;
    }

    private long pollFromMyMinHeap(MyMinHeap myHeap, List testList) {
        int iterations = testList.size();

        System.out.println("Polling from MyMinHeap: " + iterations + " objects");

        int time = 0;
        for (int val = 0; val < ITERATIONS; val++) {
            time += testMyMinHeapInteger(iterations, true, myHeap, testList);
        }
        System.out.println("Average time:" + time / ITERATIONS);

        return time / ITERATIONS;
    }

    private long addToPriorityQueue(PriorityQueue javaHeap, List testList) {
        int iterations = testList.size();

        System.out.println("Adding to PriorityQueue: " + iterations + " objects");

        int time = 0;
        for (int val = 0; val < ITERATIONS; val++) {
            time += testPriorityQueue(iterations, false, javaHeap, testList);
        }
        System.out.println("Average time:" + time / ITERATIONS);

        return time / ITERATIONS;
    }

    private long pollFromPriorityQueue(PriorityQueue javaHeap, List testList) {
        int iterations = testList.size();

        System.out.println("Polling from PriorityQueue: " + iterations + " objects");

        int time = 0;
        for (int val = 0; val < ITERATIONS; val++) {
            time += testPriorityQueue(iterations, true, javaHeap, testList);
        }
        System.out.println("Average time:" + time / ITERATIONS);
        return time / ITERATIONS;
    }

    private long testMyMinHeapInteger(int iterations, boolean poll, MyMinHeap myHeap, List testList) {
        long aikaAlussa;
        long aikaLopussa;

        aikaAlussa = System.currentTimeMillis();
        for (Object object : testList) {
            myHeap.add(object);
        }

        if (poll == true) {
            aikaAlussa = System.currentTimeMillis();
            for (int val = 0; val < iterations; val++) {
                myHeap.poll();
            }
        }

        aikaLopussa = System.currentTimeMillis();

        myHeap.empty();

        return aikaLopussa - aikaAlussa;
    }

    private long testPriorityQueue(int iterations, boolean poll, PriorityQueue javaHeap, List testList) {
        long aikaAlussa;
        long aikaLopussa;

        aikaAlussa = System.currentTimeMillis();
        for (Object object : testList) {
            javaHeap.add(object);
        }
        if (poll == true) {
            aikaAlussa = System.currentTimeMillis();

            for (int val = 0; val < iterations; val++) {
                javaHeap.poll();
            }
        }

        aikaLopussa = System.currentTimeMillis();
        //System.out.println("Operaatioon kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");

        javaHeap.clear();

        return aikaLopussa - aikaAlussa;
    }
}
