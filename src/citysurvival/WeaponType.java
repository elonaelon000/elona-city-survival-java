package citysurvival;

public enum WeaponType {
    NONE(0),
    LEVEL1(1),
    LEVEL2(2);

    public final int power;

    WeaponType(int power) {
        this.power = power;
    }
}