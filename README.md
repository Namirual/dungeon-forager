# dungeon-forager
Algorithm for pathfinding in a dungeon that requires gathering limited energy resources along the way to reach the goal.

### User guide

The algorithm can be tested with a text interface by downloading the [.jar file](DungeonForager.jar). The jar file can be executed in the console by first entering the folder the file is in and using the command *java -jar DungeonForager.jar*.

In the text interface it is possible to either create (c) random dungeons or load dungeons from a file (f). To create random dungeons, enter the width and height of the dungeon and the starting amount of energy. To load a file, enter the name of the file, which should be in the same folder the .jar file and the console browser are, and enter the starting amount of energy.

Do not be alarmed if the algorithm freezes; foraging is a difficult task!

### Tests

Performance tests are operated with JUnit. To perform them, download the whole project.


[![Build Status](https://travis-ci.org/Namirual/dungeon-forager.svg?branch=master)](https://travis-ci.org/Namirual/dungeon-forager)

[![codecov](https://codecov.io/gh/Namirual/dungeon-forager/branch/master/graph/badge.svg)](https://codecov.io/gh/Namirual/dungeon-forager)
