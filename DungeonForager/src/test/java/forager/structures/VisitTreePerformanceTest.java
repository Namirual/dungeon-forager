/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager.structures;

import forager.domain.VisitMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import org.junit.Test;

/**
 *
 * @author lmantyla
 */
public class VisitTreePerformanceTest {

    VisitTree tree;
    TreeSet<VisitMap> treeSet;
    HashSet<VisitMap> hash;

    final int ITERATIONS = 10;
    final int ADDVALUE = 10000;
    final int MAXVALUE = 100000;

    //@Test
    public void VisitTreePerformanceTest() {

        for (int val = ADDVALUE; val <= MAXVALUE; val += ADDVALUE) {
            testTree(val / 20, val, false);
        }
    }

    @Test
    public void HashSetPerformanceTest() {

        for (int val = ADDVALUE; val <= MAXVALUE; val += ADDVALUE) {
            testHashSet(val / 20, val, false);

        }
    }

    //@Test
    public void VisitTreePerformanceTestFind() {

        for (int val = ADDVALUE; val <= MAXVALUE; val += ADDVALUE) {
            testTree(val / 20, val, true);
        }
    }

    //@Test
    public void HashSetPerformanceTestFind() {

        for (int val = ADDVALUE; val <= MAXVALUE; val += ADDVALUE) {
            testHashSet(val / 20, val, true);

        }
    }

    public void testHashSet(int specialTileNumber, int permutations, boolean search) {
        System.out.println("Testing HashSet: "
                + specialTileNumber + " special tiles, " + permutations + " VisitMaps");
        List<VisitMap> visitMaps = createVisitMaps(specialTileNumber, permutations);

        tree = new VisitTree(0);
        hash = new HashSet<VisitMap>();

        long aikaAlussa;
        long aikaLopussa;

        long time = 0;

        for (int val = 0; val < ITERATIONS; val++) {
            aikaAlussa = System.currentTimeMillis();

            for (VisitMap map : visitMaps) {
                hash.add(map);
            }
            if (search == true) {
                aikaAlussa = System.currentTimeMillis();
                for (VisitMap map : visitMaps) {
                    hash.contains(map);
                }
            }
            aikaLopussa = System.currentTimeMillis();
            time += aikaLopussa - aikaAlussa;
        }
        time = time / ITERATIONS;
        System.out.println("Time spent: " + (time) + "ms.");

    }

    public void testTree(int specialTileNumber, int permutations, boolean search) {
        System.out.println("Testing VisitTree: "
                + specialTileNumber + " special tiles, " + permutations + " VisitMaps");
        List<VisitMap> visitMaps = createVisitMaps(specialTileNumber, permutations);

        tree = new VisitTree(0);

        long aikaAlussa;
        long aikaLopussa;

        long time = 0;

        for (int val = 0; val < ITERATIONS; val++) {
            aikaAlussa = System.currentTimeMillis();

            for (VisitMap map : visitMaps) {
                tree.addVisitMap(map);
            }
            if (search) {
                aikaAlussa = System.currentTimeMillis();
                for (VisitMap map : visitMaps) {
                    tree.findVisitMap(map);
                }
            }
            aikaLopussa = System.currentTimeMillis();
            time += aikaLopussa - aikaAlussa;
        }
        time = time / ITERATIONS;
        System.out.println("Time spent: " + (time) + "ms.");
    }

    List<VisitMap> createVisitMaps(int specialTileNumber, int permutations) {
        List<VisitMap> list = new ArrayList<>();

        int currentNum = 0;
        int correctTiles = 1;
        for (int val = 0; val < permutations; val++) {
            boolean[] specialTilesVisited = new boolean[specialTileNumber];
            for (int addVal = 0; addVal < correctTiles; addVal++) {
                specialTilesVisited[addVal] = true;
            }

            if (currentNum < specialTileNumber) {
                specialTilesVisited[currentNum] = true;
                currentNum++;
            } else {
                correctTiles++;
                currentNum = correctTiles;
                continue;
            }

            list.add(new VisitMap(specialTilesVisited, new boolean[50][50], 0));
        }
        return list;
    }

}
