package io.maze.cell;

import io.maze.cell.Cell;
import io.maze.doors.*;

/**
 * Created with IntelliJ IDEA.
 * User: weli
 * Date: 3/22/13
 * Time: 12:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class CellState extends Cell {
    public static final Door[] DOORS = {new NorthDoor(), new EastDoor(), new SouthDoor(), new WestDoor()};
    private boolean visited = false;
    private int x, y;

    public boolean isVisited() {
        return visited;
    }

    public Door selectDoor(Door door) {
        if (door.getClass().equals(NorthDoor.class)) {
            return this.getNorthDoor();
        }
        if (door.getClass().equals(SouthDoor.class)) {
            return this.getNorthDoor();
        }
        if (door.getClass().equals(EastDoor.class)) {
            return this.getEastDoor();
        }
        if (door.getClass().equals(WestDoor.class)) {
            return this.getWestDoor();
        }
        return null;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
