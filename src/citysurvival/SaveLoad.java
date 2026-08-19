package citysurvival;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class SaveLoad {

    private static final String SAVE_FILE = "savegame.txt";

    public static void save(GameState s) throws IOException {
        try (PrintWriter out = new PrintWriter(
                new OutputStreamWriter(new FileOutputStream(SAVE_FILE), StandardCharsets.UTF_8))) {

            out.println("mapId=" + s.mapId);
            out.println("playerX=" + s.playerX);
            out.println("playerY=" + s.playerY);
            out.println("hp=" + s.hp);
            out.println("food=" + s.food);
            out.println("hasWeapon1=" + s.hasWeapon1);
            out.println("hasWeapon2=" + s.hasWeapon2);
            out.println("equippedWeapon=" + (s.equippedWeapon == null ? "NONE" : s.equippedWeapon.name()));

            // Enemies: x,y;x,y;...
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.enemies.size(); i++) {
                if (i > 0) sb.append(";");
                sb.append(s.enemies.get(i).x).append(",").append(s.enemies.get(i).y);
            }
            out.println("enemies=" + sb);
        }
    }

    public static GameState load() throws IOException {
        File f = new File(SAVE_FILE);
        if (!f.exists()) return null;

        GameState s = new GameState();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                if (!line.contains("=")) continue;
                String[] parts = line.split("=", 2);
                String k = parts[0].trim();
                String v = parts[1].trim();

                switch (k) {
                    case "mapId" -> s.mapId = v;
                    case "playerX" -> s.playerX = Integer.parseInt(v);
                    case "playerY" -> s.playerY = Integer.parseInt(v);
                    case "hp" -> s.hp = Integer.parseInt(v);
                    case "food" -> s.food = Integer.parseInt(v);
                    case "hasWeapon1" -> s.hasWeapon1 = Boolean.parseBoolean(v);
                    case "hasWeapon2" -> s.hasWeapon2 = Boolean.parseBoolean(v);
                    case "equippedWeapon" -> {
                        if ("NONE".equalsIgnoreCase(v)) s.equippedWeapon = null;
                        else s.equippedWeapon = WeaponType.valueOf(v);
                    }
                    case "enemies" -> {
                        if (!v.isEmpty()) {
                            String[] items = v.split(";");
                            for (String item : items) {
                                String[] xy = item.split(",");
                                if (xy.length == 2) {
                                    int x = Integer.parseInt(xy[0].trim());
                                    int y = Integer.parseInt(xy[1].trim());
                                    s.enemies.add(new GameState.EnemyState(x, y));
                                }
                            }
                        }
                    }
                }
            }
        }

        return s;
    }
}
