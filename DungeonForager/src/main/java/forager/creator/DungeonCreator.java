/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager.creator;

import java.util.Random;

/**
 *
 * @author lmantyla
 */
public class DungeonCreator {

    int xwidth;
    int ywidth;
    int startingEnergy;
    char[][] map = new char[ywidth][xwidth];

    int energyTiles;

    public DungeonCreator(int xwidth, int ywidth, int startingEnergy) {
        this.xwidth = xwidth;
        this.ywidth = ywidth;
        this.startingEnergy = startingEnergy;
        map = new char[ywidth][xwidth];

        energyTiles = 0;
    }

    public char[][] createDungon() {
        Random random = new Random();

        digBestPath();

        for (int yval = 0; yval < ywidth; yval++) {
            for (int xval = 0; xval < xwidth; xval++) {
                int randomVal = random.nextInt(100);
                if (map[yval][xval] != '\0') {
                    continue;
                } else if (randomVal < 5) {
                    map[yval][xval] = 'E';
                    energyTiles++;

                } else if (randomVal < 40) {
                    map[yval][xval] = '#';
                } else {
                    map[yval][xval] = '.';
                }
            }
        }
        map[1][1] = 'S';
        map[ywidth - 2][xwidth - 2] = 'G';
        System.out.println("Number of energy tiles: " + energyTiles);
        return map;
    }

    public void digBestPath() {
        int x = 1;
        int y = 1;
        int energy = startingEnergy;

        while (x < xwidth - 1) {
            energy--;
            map[y][x] = '.';
            if (energy == 0) {
                energy += 10;
                energyTiles++;
                map[y][x] = 'E';
            }
            x++;
        }
        x = xwidth - 2;
        while (y < ywidth) {
            energy--;
            map[y][x] = '.';
            if (energy == 0) {
                energy += 10;
                energyTiles++;

                map[y][x] = 'E';
            }
            y++;
        }
    }
}
