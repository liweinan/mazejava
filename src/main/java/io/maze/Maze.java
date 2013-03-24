package io.maze;

import io.maze.cell.CellState;
import io.maze.cell.Direction;
import io.maze.doors.Door;
import io.maze.doors.PrototypeDoor;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: weli
 * Date: 3/22/13
 * Time: 12:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class Maze {
    private CellState in, out;
    private CellState[][] cells;
    private int m, n;
    private Stack<CellState> histories = new Stack<CellState>();
    private int visited_cnt = 0;

    public CellState getCell(int x, int y) {
        if (in_scope(x, y))
            return cells[x][y];
        return null;
    }

    public CellState getIn() {
        return in;
    }

    public CellState getOut() {
        return out;
    }

    public Maze(int m, int n) {
        this.m = m;
        this.n = n;

        cells = new CellState[m][n];

        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                cells[x][y] = new CellState();
                cells[x][y].setX(x);
                cells[x][y].setY(y);
            }
        }
    }

    public void generate() {
        in = cells[0][0];
        out = cells[m - 1][n - 1];

        in.getWestDoor().setOpened(true);
        out.getSouthDoor().setOpened(true);

        CellState currentCell = in;
        currentCell.setVisited(true);
        histories.push(currentCell);
        visited_cnt++;

        System.out.println("LEGEND: C- CANDIDATE, D- DECISION, R- RESULT, G- GO BACK");
        while (histories.size() > 0 && visited_cnt < m * n) {
            while (currentCell != null) {
                currentCell = nextCell(currentCell);
                if (currentCell != null) {
                    histories.push(currentCell);
                    visited_cnt++;
                }
            }
            currentCell = histories.pop();
            System.out.println("(G- x:" + currentCell.getX() + " y:" + currentCell.getY() + ")");
        }
    }

    private CellState nextCell(CellState currentCell) {

        HashMap<Direction, CellState> candidates = new HashMap<Direction, CellState>();

        Selection sel = null;

        checkCandidate(candidates, currentCell.getX() - 1, currentCell.getY(), Direction.LEFT);
        checkCandidate(candidates, currentCell.getX() + 1, currentCell.getY(), Direction.RIGHT);
        checkCandidate(candidates, currentCell.getX(), currentCell.getY() - 1, Direction.UP);
        checkCandidate(candidates, currentCell.getX(), currentCell.getY() + 1, Direction.DOWN);


        if (candidates.size() > 0) {
            System.out.print("(C- ");

            for (Direction dir : candidates.keySet()) {
                System.out.print(dir.toString() + ";");
            }
            System.out.print(")");
        }

        sel = selectNext(candidates);

        if (sel != null) {
            System.out.print("(D- go " + sel.getDirection().toString() + ")");
            System.out.print("(R- x:" + sel.getCell().getX() + " y:" + sel.getCell().getY() + ")\n");
            processNext(sel, currentCell);
            return sel.getCell(); // return next cell
        }
        return null;
    }

    public void resetCellState() {
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                cells[x][y].setVisited(false);
            }
        }
    }

    public CellState eastOf(CellState current) {
        int x = current.getX() + 1;
        int y = current.getY();
        if (in_scope(x, y))
            return getCell(x, y);
        return null;
    }

    public CellState southOf(CellState current) {
        int x = current.getX();
        int y = current.getY() + 1;
        if (in_scope(x, y))
            return getCell(x, y);
        return null;
    }

    public CellState westOf(CellState current) {
        int x = current.getX() - 1;
        int y = current.getY();
        if (in_scope(x, y))
            return getCell(x, y);
        return null;
    }

    public CellState northOf(CellState current) {
        int x = current.getX();
        int y = current.getY() - 1;
        if (in_scope(x, y))
            return getCell(x, y);
        return null;
    }

    private class Selection {
        private Direction direction;
        private CellState cell;

        private Selection(Direction direction, CellState cell) {
            this.direction = direction;
            this.cell = cell;
        }

        public Direction getDirection() {
            return direction;
        }

        public CellState getCell() {
            return cell;
        }
    }

    private Selection selectNext(HashMap<Direction, CellState> candidates) {
        Random rand = new Random();
        Set<Direction> keys = candidates.keySet();
        if (keys.size() > 0) {
            Direction dir = (Direction) keys.toArray()[rand.nextInt(keys.size())];
            return new Selection(dir, candidates.get(dir));
        }
        return null;
    }

    private void processNext(Selection sel, CellState currentCell) {
        CellState nextCell = sel.getCell();
        switch (sel.getDirection()) {
            case UP:
                currentCell.getNorthDoor().setOpened(true);
                nextCell.getSouthDoor().setOpened(true);
                nextCell.setVisited(true);
                break;
            case DOWN:
                currentCell.getSouthDoor().setOpened(true);
                nextCell.getNorthDoor().setOpened(true);
                nextCell.setVisited(true);
                break;
            case LEFT:
                currentCell.getWestDoor().setOpened(true);
                nextCell.getEastDoor().setOpened(true);
                nextCell.setVisited(true);
                break;
            case RIGHT:
                currentCell.getEastDoor().setOpened(true);
                nextCell.getWestDoor().setOpened(true);
                nextCell.setVisited(true);
                break;
        }

    }

    private void checkCandidate(HashMap<Direction, CellState> candidates, int x, int y, Direction dir) {
        if (in_scope(x, y)) {
            if (!cells[x][y].isVisited()) {
                candidates.put(dir, cells[x][y]);
            }
        }
    }

    private boolean in_scope(int x, int y) {
        return (0 <= x && x < m && 0 <= y && y < n);
    }

    public void print() {
        String token = "   |\n---+";

        // first row
        System.out.print("+");
        for (int x = 0; x < m; x++) {
            System.out.print("---+");
        }
        System.out.println("");
        // end of first row

        for (int y = 0; y < n; y++) {
            Queue<String> parts = new LinkedList<String>();
            for (int x = 0; x < m; x++) {

                CellState cell = cells[x][y];
                String part = new String(token);

                if (x == 0 && y == 0) { // in
                    part = part.replaceAll("   \\|", " > |");
                }

                if (x == m - 1 && y == n - 1) { //out
                    part = part.replaceAll("   \\|", " V |");
                }

                // draw path
                if (cell.isInPath()) {
                    part = part.replaceAll("   \\|", " * |");
                }

                if (cell.getSouthDoor().isOpened()) {
                    part = part.replaceAll("\\-", " ");
                }
                if (cell.getEastDoor().isOpened()) {
                    part = part.replaceAll("\\|", " ");
                }

                parts.offer(part);
            }

            String[] rows = new String[2];
            rows[0] = "";
            rows[1] = "";
            String part;
            while ((part = parts.poll()) != null) {
                String[] _rows = part.split("\n");
                rows[0] += _rows[0];
                rows[1] += _rows[1];
            }
            if (y != 0) {
                System.out.print("|");
            } else {
                System.out.print(" ");
            }
            System.out.println(rows[0]);
            System.out.println("+" + rows[1]);
        }
    }
}
