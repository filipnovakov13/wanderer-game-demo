package models.areaelements;

import models.characters.Boss;
import models.characters.Monster;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Area {


    private int areaLevel;
    private List<Tile[][]> tiles;

    private List<Monster> monsters;

    protected Random random = new Random();

    public Area() {
        tiles = new ArrayList<>();
        monsters = new ArrayList<>();
        areaLevel = 1;
        generateTiles();
        generateMonsters();
    }

    private static class Cell {
        int row, col;
        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private void generateTiles() {
        int[][] firstTiles = generateRandomMaze();
        tiles.add(generateTileMap(firstTiles));

        int[][] secondTiles = generateRandomMaze();
        tiles.add(generateTileMap(secondTiles));

        int[][] thirdTiles = generateRandomMaze();
        tiles.add(generateTileMap(thirdTiles));

        int[][] fourthTiles = generateRandomMaze();
        tiles.add(generateTileMap(fourthTiles));

        int[][] fifthTiles = generateRandomMaze();
        tiles.add(generateTileMap(fifthTiles));

        int [][] sixthTiles = generateRandomMaze();
        tiles.add(generateTileMap(sixthTiles));
    }

    private int[][] generateRandomMaze() {
        int[][] grid = new int[10][10];

        // Initialize the grid with 1's
        for (int i = 0; i < 10; i++) {
            Arrays.fill(grid[i], 1);
        }

        generateMaze(grid);

        return grid;
    }

    private void generateMaze(int[][] grid) {
        Deque<Cell> stack = new LinkedList<>();
        Cell start = new Cell(0, 0);
        grid[start.row][start.col] = 0;
        stack.push(start);

        while (!stack.isEmpty()) {
            Cell current = stack.peek();
            Cell next = getRandomNeighbor(grid, current);

            if (next != null) {
                grid[next.row][next.col] = 0;
                int wallRow = (current.row + next.row) / 2;
                int wallCol = (current.col + next.col) / 2;
                grid[wallRow][wallCol] = 0;
                stack.push(next);
            } else {
                stack.pop();
            }
        }

        ensureEdges(grid);
    }

    private void ensureEdges(int[][] grid) {
        for (int i = 0; i < 10; i++) {
            if (grid[i][8] == 0 && grid[i][9] == 1) {
                grid[i][9] = 0;
            }
            if (grid[8][i] == 0 && grid[9][i] == 1) {
                grid[9][i] = 0;
            }
        }
    }

    private Cell getRandomNeighbor(int[][] grid, Cell cell) {
        Cell[] neighbors = new Cell[] {
                new Cell(cell.row - 2, cell.col),
                new Cell(cell.row + 2, cell.col),
                new Cell(cell.row, cell.col - 2),
                new Cell(cell.row, cell.col + 2)
        };
        Collections.shuffle(Arrays.asList(neighbors));

        for (Cell neighbor : neighbors) {
            if (isValid(grid, neighbor)) {
                return neighbor;
            }
        }
        return null;
    }

    private boolean isValid(int[][] grid, Cell cell) {
        if (cell.row >= 0 && cell.row < 10 && cell.col >= 0 && cell.col < 10) {
            return grid[cell.row][cell.col] == 1;
        }
        return false;
    }

    @NotNull
    private static Tile[][] generateTileMap(int[][] map) {
        Tile[][] tileMap = new Tile[10][10];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                tileMap[i][j] = map[i][j] == 0 ? new Floor() : new Wall();
            }
        }
        return tileMap;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public Tile[][] getTiles(int index) {
        return tiles.get(index);
    }

    public Tile[][] getCurrentTiles() {
        return tiles.get(areaLevel - 1);
    }

    public void increaseLevel() {
        areaLevel++;
        generateMonsters();
    }

    public int getAreaLevel() {
        return areaLevel;
    }

    private void generateMonsters() {
        monsters.clear();
        int monsterCount = 3 + (int) (Math.random() * 4);

        for (int i = 0; i < monsterCount; i++) {
            int level;
            double chance = Math.random();

            if (chance < 0.5) level = areaLevel;
            else if (chance < 0.9) level = areaLevel + 1;
            else level = areaLevel + 2;

            int monsterX = random.nextInt(9) + 1;
            int monsterY = random.nextInt(9) + 1;

            while (getCurrentTiles()[monsterY][monsterX] instanceof Wall
                    || isPositionOccupied(monsterX , monsterY)) {
                monsterX = random.nextInt(9) + 1;
                monsterY = random.nextInt(9) + 1;
            }
            monsters.add(new Monster(level, monsterX , monsterY));
        }

        int bossX = random.nextInt(9) + 1;
        int bossY = random.nextInt(9) + 1;

        while (getCurrentTiles()[bossY][bossX] instanceof Wall
                || isPositionOccupied(bossX, bossY)) {
            bossX = random.nextInt(9) + 1;
            bossY = random.nextInt(9) + 1;
        }

        monsters.get((int) (Math.random() * monsters.size())).setHasKey(true);
        monsters.add(new Boss(areaLevel, bossX, bossY));
    }


    public boolean isPositionOccupied(int x, int y) {
        for (Monster monster : monsters) {
            if (monster.getX() == x && monster.getY() == y) {
                return true;
            }
        }
        return false;
    }
}
