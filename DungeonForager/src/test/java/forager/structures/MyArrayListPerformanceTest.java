/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager.structures;

import java.util.ArrayList;
import org.junit.Test;

/**
 *
 * @author lmantyla
 */
public class MyArrayListPerformanceTest {

    final int TESTITERATIONS = 10;

    //@Test
    public void testMyArrayList() {

        for (int val = 500000; val <= 5000000; val += 500000) {
            testMyArrayList(new Integer(1), val, false);
        }
    }

    //@Test
    public void testMyArrayListDeletions() {
        for (int val = 20000; val <= 200000; val += 20000) {
            testMyArrayList(new Integer(1), val, true);
        }
    }

    //@Test
    public void testJavaArrayList() {
        for (int val = 500000; val <= 5000000; val += 500000) {
            testJavaArrayList(new Integer(1), val, false);
        }
    }

    //@Test
    public void testJavaArrayListDeletions() {
        for (int val = 20000; val <= 200000; val += 20000) {
            testJavaArrayList(new Integer(1), val, true);
        }
    }

    public void testMyArrayList(Object added, int iterations, boolean deletions) {
        if (!deletions) {
            System.out.println("Testing MyArrayList: " + iterations + " objects added: ");
        } else {
            System.out.println("Testing MyArrayList: " + iterations + " objects removed: ");
        }

        MyArrayList<Object> myList;

        long aikaAlussa;
        long aikaLopussa;

        int time = 0;
        for (int iterationNum = 0; iterationNum < TESTITERATIONS; iterationNum++) {
            myList = new MyArrayList<>();

            aikaAlussa = System.currentTimeMillis();
            for (int val = 0; val < iterations; val++) {
                myList.add(added);
            }
            if (deletions) {
                aikaAlussa = System.currentTimeMillis();

                for (int delval = (iterations / 2); delval > 0; delval--) {
                    myList.remove(delval);
                }
            }
            aikaLopussa = System.currentTimeMillis();
            time += aikaLopussa - aikaAlussa;
        }
        time = time / TESTITERATIONS;
        System.out.println("Operaatioon kului aikaa: " + (time) + "ms.");

    }

    public void testJavaArrayList(Object added, int iterations, boolean deletions) {
        if (!deletions) {
            System.out.println("Testing Java's ArrayList: " + iterations + " objects added: ");
        } else {
            System.out.println("Testing Java's ArrayList: " + iterations / 2 + " objects removed: ");
        }

        ArrayList<Object> javaList;
        javaList = new ArrayList<>();

        long aikaAlussa;
        long aikaLopussa;

        int time = 0;
        for (int iterationNum = 0; iterationNum < TESTITERATIONS; iterationNum++) {
            javaList = new ArrayList<>();

            aikaAlussa = System.currentTimeMillis();
            for (int val = 0; val < iterations; val++) {
                javaList.add(added);
            }
            if (deletions) {
                aikaAlussa = System.currentTimeMillis();

                for (int delval = (iterations / 2); delval > 0; delval--) {
                    javaList.remove(delval);
                }
            }

            aikaLopussa = System.currentTimeMillis();
            time += aikaLopussa - aikaAlussa;
        }
        time = time / TESTITERATIONS;
        System.out.println("Operaatioon kului aikaa: " + time + "ms.");
    }
}
