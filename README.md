# Maze Java

A maze game written in java for fun.

## Usage

Create a 5x5 maze:

	   Maze maze = MazeFactory.createMaze(5, 5);

Print it:

	  maze.print();

Here is the result:

   	+---+---+---+---+---+
   	  >     |   |       |
   	+---+   +   +   +   +
   	|   |   |       |   |
   	+   +   +   +---+   +
   	|   |   |       |   |
   	+   +   +---+---+   +
   	|       |       |   |
   	+   +---+   +   +   +
   	|           |     V |
   	+---+---+---+---+   +

From the log you can see how the maze is generated:

	LEGEND: C- CANDIDATE, D- DECISION, R- RESULT, G- GO BACK
	(C- DOWN;RIGHT;)(D- go RIGHT)(R- x:1 y:0)
	(C- DOWN;RIGHT;)(D- go DOWN)(R- x:1 y:1)
	(C- DOWN;LEFT;RIGHT;)(D- go RIGHT)(R- x:2 y:1)
	(C- DOWN;UP;)(D- go DOWN)(R- x:2 y:2)
	(C- LEFT;)(D- go LEFT)(R- x:1 y:2)
	(C- LEFT;)(D- go LEFT)(R- x:0 y:2)
	(C- UP;)(D- go UP)(R- x:0 y:1)
	(G- x:0 y:1)
	(G- x:0 y:2)
	(G- x:1 y:2)
	(G- x:2 y:2)
	(G- x:2 y:1)
	(C- UP;)(D- go UP)(R- x:2 y:0)
	(G- x:2 y:0)
	+---+---+---+
	  >     |   |
	+---+   +   +
	|   |       |
	+   +---+   +
	|         V |
	+---+---+   +

Now you can try to use PathFinder to solve the maze:

	PathFinder finder = new DefaultPathFinder();
	finder.solve(maze);

And print it:

	maze.print();

Now let's have some fun:

	Maze maze = MazeFactory.createMaze(16, 9);
	PathFinder finder = new DefaultPathFinder();
	finder.solve(maze);
	maze.print();

	+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
	  > |     *   *   *   *   *   *   * |                           |
	+   +---+   +---+---+---+---+---+   +---+   +---+   +---+---+   +
	| *   *   * |           |       | *   * |   |       |           |
	+---+---+---+   +---+   +   +---+---+   +---+   +---+   +---+---+
	|       |   | *   * |       | *   * | *   * |   |               |
	+   +   +   +   +   +---+---+   +   +---+   +   +---+---+---+---+
	|   |       | * | *   *   *   * | *     | * |   |               |
	+   +---+---+   +---+---+---+---+   +---+   +   +   +---+---+   +
	| *   *   *   * |               | *   *   * |       |   |       |
	+   +---+---+---+---+---+---+   +---+---+---+   +---+   +   +---+
	| * | *   *   *   *   *   * |     *   * |               |       |
	+   +   +---+---+---+---+   +---+   +   +---+---+---+---+---+   +
	| * | *   * |           | *   *   * | * | *   *   *   *   *   * |
	+   +---+   +   +   +---+---+---+---+   +   +---+---+---+---+   +
	| *   *   * |   |                   | * | * |           | *   * |
	+---+---+---+   +---+---+---+   +---+   +   +   +   +---+   +---+
	|                           |         *   * |   |         *   V |
	+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+   +


## Reference

* [Practical algorithms and code optimization: maze generation ](http://ilay.org/yann/articles/maze/index.en.html)