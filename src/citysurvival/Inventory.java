package citysurvival;

public class Inventory {

    private int food = 0;

    private boolean hasWeapon1 = false;
    private boolean hasWeapon2 = false;

    private WeaponType equippedWeapon = WeaponType.NONE;

    private int food1; // F
private int food2; // G
private int food3; // H


    // ===== FOOD =====
    public void addFood() {
        food++;
    }

    public int getFood() {
        return food;
    }

    public int getFood1() { return food1; }
public int getFood2() { return food2; }
public int getFood3() { return food3; }


    public void useFood(PlayerStats stats) {
        if (food > 0 && stats.hp < stats.maxHp) {
            food--;
            stats.hp++;
        }
    }

    public void addFood1() {
    food1++;
}

public void addFood2() {
    food2++;
}

public void addFood3() {
    food3++;
}


public void useFood1(PlayerStats stats) {
    if (food1 > 0 && stats.hp < stats.maxHp) {
        food1--;
        stats.heal(); // +1
    }
}

public void useFood2(PlayerStats stats) {
    if (food2 > 0 && stats.hp < stats.maxHp) {
        food2--;
        stats.heal();
        stats.heal(); // +2
    }
}

public void useFood3(PlayerStats stats) {
    if (food3 > 0 && stats.hp < stats.maxHp) {
        food3--;
        while (stats.hp < stats.maxHp) {
            stats.heal(); // full heal
        }
    }
}


    // ===== WEAPONS =====
    public void pickWeapon(WeaponType weapon) {
        if (weapon == WeaponType.LEVEL1)
            hasWeapon1 = true;

        if (weapon == WeaponType.LEVEL2)
            hasWeapon2 = true;

        // auto-equip më e forta
        if (weapon.power > equippedWeapon.power) {
            equippedWeapon = weapon;
        }
    }

    public void equipWeapon(WeaponType weapon) {
        if (weapon == WeaponType.LEVEL1 && hasWeapon1)
            equippedWeapon = weapon;

        if (weapon == WeaponType.LEVEL2 && hasWeapon2)
            equippedWeapon = weapon;
    }

    // ===== SETTERS (për Load Game) =====
public void setFood(int food) {
    this.food = Math.max(0, food);
}

public void setWeapon1(boolean v) {
    this.hasWeapon1 = v;
    if (!hasWeapon1 && equippedWeapon == WeaponType.LEVEL1) {
        equippedWeapon = WeaponType.NONE;
    }
}

public void setWeapon2(boolean v) {
    this.hasWeapon2 = v;
    if (!hasWeapon2 && equippedWeapon == WeaponType.LEVEL2) {
        equippedWeapon = WeaponType.NONE;
    }
}

public void setEquippedWeapon(WeaponType w) {
    if (w == null) {
        equippedWeapon = WeaponType.NONE;
        return;
    }
    // lejo equip vetëm nëse e ke armën
    if (w == WeaponType.LEVEL1 && hasWeapon1) equippedWeapon = w;
    else if (w == WeaponType.LEVEL2 && hasWeapon2) equippedWeapon = w;
    else if (w == WeaponType.NONE) equippedWeapon = WeaponType.NONE;
}

    public WeaponType getEquippedWeapon() {
        return equippedWeapon;
    }

    public boolean hasWeapon1() { return hasWeapon1; }
    public boolean hasWeapon2() { return hasWeapon2; }
}
