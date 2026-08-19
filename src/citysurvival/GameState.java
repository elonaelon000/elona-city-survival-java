package citysurvival;

import java.util.ArrayList;
import java.util.List;

/**
 * Mban gjendjen e plotë të lojës për Save / Load.
 * Kjo klasë është vetëm DATA (pa logjikë loje).
 */
public class GameState {

    // ===== PLAYER =====
    public int playerX;
    public int playerY;

    // ===== STATS =====
    public int hp;
    public int food;

    // ===== INVENTORY =====
    public boolean hasWeapon1;
    public boolean hasWeapon2;
    public WeaponType equippedWeapon; // LEVEL1, LEVEL2 ose null

    // ===== MAP =====
    public String mapId; // p.sh. "LEVEL1", "LEVEL2"

    // ===== ENEMIES =====
    public List<EnemyState> enemies = new ArrayList<>();

    /**
     * Gjendja minimale e një armiku (pozicioni).
     * Mund të zgjerohet më vonë me HP, tip, etj.
     */
    public static class EnemyState {
        public int x;
        public int y;

        public EnemyState(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
