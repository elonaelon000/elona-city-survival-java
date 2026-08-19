package citysurvival;

import java.io.*;
import java.util.*;

public class TileMap {

    private char[][] map;
    public int rows, cols;

    public TileMap(String path) {
        loadMap(path);
    }
public void clear(int x, int y) {
    map[y][x] = '.';
}

    private void loadMap(String path) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            System.err.println(" Nuk u lexua map.txt");
            e.printStackTrace();
            return;
        }

        if (lines.isEmpty()) {
            System.err.println(" map.txt është bosh");
            return;
        }

        rows = lines.size();
        cols = lines.get(0).length();
        map = new char[rows][cols];

        for (int y = 0; y < rows; y++) {
            map[y] = lines.get(y).toCharArray();
        }
    }

   public char get(int x, int y) {
    if (y < 0 || y >= rows) return '#';
    if (x < 0 || x >= map[y].length) return '#';
    return map[y][x];
}


    public boolean isWalkable(int x, int y) {
    char t = get(x, y);

    // Të pabanuara (bllokuese)
    if (t == '#') return false; // building1 + jashtë harte
    if (t == 'T') return false; // pemë
    if (t == 'N') return false; // building
    if (t == 'B') return false; // building2

    // çdo gjë tjetër lejohet (rrugë, bar, ushqime, armë, etj.)
    return true;
}

}
