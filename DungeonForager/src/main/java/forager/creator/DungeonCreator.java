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
    Random random;

    int energyTiles;

    /**
     * Defines the size of the dungeon and the starting energy with which the
     * dungeon is solvable.
     *
     * @param xwidth width of the dungeon
     * @param ywidth height of the dungeon
     * @param startingEnergy amount of energy at the start of the dungeon
     */
    public DungeonCreator(int xwidth, int ywidth, int startingEnergy) {
        this.xwidth = xwidth;
        this.ywidth = ywidth;
        this.startingEnergy = startingEnergy;
        map = new char[ywidth][xwidth];

        energyTiles = 0;
    }

    /**
     * Creates a random, solvable dungeon.
     *
     * @param easy Determines whether an easy or difficult dungeon is created.
     * @param seed Random seed for the random dungeon.
     * @return 2d char array containing the dungeon.
     */
    public char[][] createDungeon(boolean easy, int seed) {
        random = new Random();
        if (seed != 0) {
            random.setSeed(seed);
        }
        
        placeRandomEnergy(5, 4);
        if (easy) {
            digBestPath();
        } else {
            digWindingPath();
        }

        for (int yval = 0; yval < ywidth; yval++) {
            for (int xval = 0; xval < xwidth; xval++) {
                int randomVal = random.nextInt(100);
                if (map[yval][xval] != '\0') {
                    continue;
                } else if (randomVal < 40) {
                    map[yval][xval] = '#';
                } else {
                    map[yval][xval] = '.';
                    if (yval > 30) {
                        map[yval][xval] = '.';
                    }
                }
            }
        }
        map[2][2] = 'S';
        map[ywidth - 3][xwidth - 3] = 'G';
        System.out.println("Number of energy tiles: " + energyTiles);
        return map;
    }

    /**
     * Places energy at random positions in the dungeon.
     * @param percentage percentage chance of a tile being energy
     * @param offset distance from the edge where no energy is placed.
     */
    public void placeRandomEnergy(int percentage, int offset) {
        for (int yval = offset; yval < ywidth - offset; yval++) {
            for (int xval = offset; xval < xwidth - offset; xval++) {
                int randomVal = random.nextInt(100);
                if (map[yval][xval] != '\0') {
                    continue;
                } else if (randomVal < percentage) {
                    map[yval][xval] = 'E';
                    energyTiles++;

                }
            }
        }
    }

    /**
     * Produces a straight path through the dungeon with enough energy on the
     * route to pass through.
     */
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

    /**
     * Produces a path through the dungeon that has enough energy, but requires
     * backtracking.
     */
    public void digWindingPath() {
        int x = 0;
        int y = 2;
        int energy = startingEnergy + 2;

        boolean placeDown = true;
        while (x < xwidth - 3) {
            energy--;
            map[y][x] = '.';
            if (energy == 4) {
                energy += 6;
                energyTiles++;
                if (placeDown) {
                    map[y - 1][x] = '.';
                    map[y - 2][x] = 'E';
                    placeDown = false;
                } else {
                    map[y + 1][x] = '.';
                    map[y + 2][x] = 'E';
                    placeDown = true;

                }
            }
            x++;
        }
        x = xwidth - 3;
        boolean placeRight = true;

        while (y < ywidth) {
            energy--;

            map[y][x] = '.';
            if (energy == 4) {
                energy += 6;
                energyTiles++;
                if (placeRight) {
                    map[y][x + 1] = '.';
                    map[y][x + 2] = 'E';
                    placeRight = true;
                } else {
                    map[y][x - 1] = '.';
                    map[y][x - 2] = 'E';
                    placeRight = false;
                }
            }
            y++;
        }
    }

    /**
     * Removes energy tiles at a horizontal axis from a given location.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @param range distance within which energy tiles are removed
     */
    private void removeHorizontalEnergyTiles(int x, int y, int range) {
        int varX = x - range;
        int endX = x + range;

        if (varX < 0) {
            varX = 0;
        }
        if (endX >= xwidth) {
            endX = xwidth - 1;
        }

        for (; varX <= endX; varX++) {
            if (map[y][varX] == 'E') {
                map[y][varX] = '.';
            }
        }
    }

    /**
     * Removes energy tiles at a vertical axis from a given location.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @param range distance within which energy tiles are removed
     */
    public void removeVerticalEnergyTiles(int x, int y, int range) {
        int varY = y - range;
        int endY = y + range;

        if (varY <= 0) {
            varY = 0;
        }
        if (endY >= ywidth) {
            endY = ywidth - 1;
        }

        for (; varY <= endY; varY++) {
            if (map[varY][x] == 'E') {
                map[varY][x] = '.';
            }
        }
    }
}
