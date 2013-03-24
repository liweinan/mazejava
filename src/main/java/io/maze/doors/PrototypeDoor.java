package io.maze.doors;

/**
 * Created with IntelliJ IDEA.
 * User: weli
 * Date: 3/22/13
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class PrototypeDoor implements Door {
    boolean opened = false;

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public PrototypeDoor() {
    }
}
