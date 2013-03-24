package io.maze;

/**
 * Created with IntelliJ IDEA.
 * User: weli
 * Date: 3/22/13
 * Time: 12:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class MazeFactory {
    public static Maze createMaze(int m, int n) {
        Maze maze = new Maze(m, n);
        maze.generate();
        return maze;
    }
}
