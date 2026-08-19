package citysurvival;

public class PlayerStats {

    public int hp = 3;
    public int maxHp = 5;

    public void heal() {
        if (hp < maxHp) {
            hp++;
        }
    }

    public void damage() {
        hp--;
    }

    public boolean isDead() {
        return hp <= 0;
    }
}
