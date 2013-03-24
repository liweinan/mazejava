package io.maze;

import io.maze.Maze;
import io.maze.MazeFactory;

/**
 * Created with IntelliJ IDEA.
 * User: weinanli
 * Date: 3/24/13
 * Time: 12:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlayMaze {

    public static final void main(String[] args) {
        Maze maze = MazeFactory.createMaze(5, 5);
        maze.print();
    }
}
