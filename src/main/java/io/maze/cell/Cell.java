package io.maze.cell;

import io.maze.doors.EastDoor;
import io.maze.doors.NorthDoor;
import io.maze.doors.SouthDoor;
import io.maze.doors.WestDoor;

/**
 * Created with IntelliJ IDEA.
 * User: weli
 * Date: 3/22/13
 * Time: 12:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class Cell {

    private NorthDoor northDoor = new NorthDoor();
    private EastDoor eastDoor = new EastDoor();
    private SouthDoor southDoor = new SouthDoor();
    private WestDoor westDoor = new WestDoor();

    public NorthDoor getNorthDoor() {
        return northDoor;
    }

    public void setNorthDoor(NorthDoor northDoor) {
        this.northDoor = northDoor;
    }

    public EastDoor getEastDoor() {
        return eastDoor;
    }

    public void setEastDoor(EastDoor eastDoor) {
        this.eastDoor = eastDoor;
    }

    public SouthDoor getSouthDoor() {
        return southDoor;
    }

    public void setSouthDoor(SouthDoor southDoor) {
        this.southDoor = southDoor;
    }

    public WestDoor getWestDoor() {
        return westDoor;
    }

    public void setWestDoor(WestDoor westDoor) {
        this.westDoor = westDoor;
    }
}
