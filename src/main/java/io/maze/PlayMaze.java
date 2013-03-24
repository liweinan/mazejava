package io.maze;

import io.maze.Maze;
import io.maze.MazeFactory;
import io.maze.finder.DefaultPathFinder;
import io.maze.finder.PathFinder;

/**
 * Created with IntelliJ IDEA.
 * User: weinanli
 * Date: 3/24/13
 * Time: 12:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlayMaze {

    public static final void main(String[] args) {
        Maze maze = MazeFactory.createMaze(30, 30);
        maze.print();
        PathFinder finder = new DefaultPathFinder();
        finder.solve(maze);
        maze.print();
    }
}
