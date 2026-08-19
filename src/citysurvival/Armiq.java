package citysurvival;

import java.util.Random;

public class Armiq {
    public int x, y;
    public WeaponType weapon;

    private Random rand = new Random();

    public Armiq(int x, int y) {
        this.x = x;
        this.y = y;
        weapon = WeaponType.LEVEL1;
    }

    public void leviz(TileMap map) {
        int dir = rand.nextInt(4);
        int newX = x, newY = y;

        switch (dir) {
            case 0 -> newY--;
            case 1 -> newY++;
            case 2 -> newX--;
            case 3 -> newX++;
        }

        if (map.isWalkable(newX, newY)) {
            x = newX;
            y = newY;
        }
    }
}
