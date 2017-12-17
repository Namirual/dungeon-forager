# dungeon-forager
Algorithm for finding fastest routes in a dungeon that requires gathering limited energy resources and backtracking along the way to reach the goal.

### User guide

The PhasedForager algorithm can be tested with a text interface by downloading the [.jar file](DungeonForager.jar). The jar file can be executed in the console by first entering the folder the file is in and using the command *java -jar DungeonForager.jar*.

In the text interface it is possible to either create (c) random dungeons or load dungeons from a file (f). To create random dungeons, enter the width and height of the dungeon and the starting amount of energy. To load a file, enter the name of the file, which should be in the same folder the .jar file and the console browser are, and enter the starting amount of energy. An [example file](dungeon.txt) is provided in the repo. 

Once a dungeon has been acquired, a search will start. The route will be printed into the window, with numbers representing how many times a particular tile was visited. Do not be alarmed if the algorithm freezes or runs out of memory; foraging is a difficult task!

### Tests

Automated performance tests are run with JUnit. To perform the tests, download the whole project. Many of the tests are time-consuming and are commented out in the source code; it is recommended to turn them on one at a time.


[![Build Status](https://travis-ci.org/Namirual/dungeon-forager.svg?branch=master)](https://travis-ci.org/Namirual/dungeon-forager)

[![codecov](https://codecov.io/gh/Namirual/dungeon-forager/branch/master/graph/badge.svg)](https://codecov.io/gh/Namirual/dungeon-forager)
