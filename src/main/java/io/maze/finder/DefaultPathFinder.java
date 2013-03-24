package io.maze.finder;

import io.maze.Maze;
import io.maze.cell.CellState;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: weinanli
 * Date: 3/24/13
 * Time: 3:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class DefaultPathFinder implements PathFinder {

    @Override
    public void solve(Maze maze) {
        if (maze == null)
            return;
        maze.resetCellState();

        CellState current = maze.getIn();
        current.setVisited(true);

        Stack<CellState> route = new Stack<CellState>();
        route.push(current);
        boolean poped = false;

        while (!maze.getOut().equals(current)) {
            CellState prev = current;
            current = walkToNext(maze, current);

            if (current != null) {
                if (poped) {
                    route.push(prev);
                    poped = false;
                }

                route.push(current);

            } else {
                current = route.pop();
                poped = true;
            }
        }

        current.setInPath(true);
        route.push(current);

        for (CellState step : route) {
            step.setInPath(true);
            System.out.printf("(%d, %d)", step.getX(), step.getY());
        }
        System.out.println("");
    }

    private CellState walkToNext(Maze maze, CellState current) {
        CellState next = nextAvaliable(maze, current);
        if (next != null)
            next.setVisited(true);
        return next;
    }

    private CellState nextAvaliable(Maze maze, CellState current) {
        if (current.getNorthDoor().isOpened() && !maze.northOf(current).isVisited())
            return maze.northOf(current);
        if (current.getEastDoor().isOpened() && !maze.eastOf(current).isVisited())
            return maze.eastOf(current);
        if (current.getSouthDoor().isOpened() && !maze.southOf(current).isVisited())
            return maze.southOf(current);
        if (current.getWestDoor().isOpened() && !maze.westOf(current).isVisited())
            return maze.westOf(current);
        return null;
    }
}
