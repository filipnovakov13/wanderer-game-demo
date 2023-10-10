package models;

import java.util.ArrayList;
import java.util.List;

public class Area {
    private List<int[][]> tiles;

    public Area() {
        tiles = new ArrayList<>();
        generateTiles();
    }

    private void generateTiles() {
        int[][] firstTiles =
                        {{0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 1, 0, 0, 0, 0, 1, 1, 1, 1},
                        {0, 1, 1, 1, 1, 0, 1, 0, 0, 0},
                        {0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
                        {0, 0, 1, 0, 1, 0, 1, 0, 0, 0},
                        {0, 1, 1, 0, 1, 0, 1, 1, 0, 1},
                        {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                        {1, 1, 1, 1, 1, 0, 1, 0, 1, 0},
                        {0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
                        {0, 0, 1, 1, 1, 0, 0, 0, 1, 0}};
        int[][] secondTiles =
                {{0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 1, 0, 0, 0, 0, 1, 1, 1, 1},
                        {0, 1, 1, 1, 1, 0, 1, 0, 0, 0},
                        {0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
                        {0, 0, 1, 0, 1, 0, 1, 0, 0, 0},
                        {0, 1, 1, 0, 1, 0, 1, 1, 0, 1},
                        {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                        {1, 1, 1, 1, 1, 0, 1, 0, 1, 0},
                        {0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
                        {0, 0, 1, 1, 1, 0, 0, 0, 1, 0}};
        tiles.add(firstTiles);
        tiles.add(secondTiles);
    }

    public int[][] getTiles(int index) {
        return tiles.get(index);
    }
}
