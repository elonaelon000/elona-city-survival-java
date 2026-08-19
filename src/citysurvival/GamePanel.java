package citysurvival;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    // TILE & VIEW
    private final int TILE = 32;
    private final int VIEW_COLS = 20;
    private final int VIEW_ROWS = 15;
private final GameStats gameStats = new GameStats();

    // Player (tile coords)
    private int playerX, playerY;

    // Camera (top-left tile of viewport)
    private int camX, camY;

    // Core
    private TileMap tileMap;
    private final Inventory inventory;
    private final PlayerStats stats;

    // Images
    private BufferedImage roadImg, roadgrassImg;
    private BufferedImage buildingImg, building1Img, building2Img;
    private BufferedImage treeImg, playerImg, armiqImg;
    private BufferedImage weapon1Img, weapon2Img;
    private BufferedImage food1Img, food2Img, food3Img;

    // Enemies
    private final ArrayList<Armiq> armiqte = new ArrayList<>();

    // Game state
    private boolean gameOver = false;
    private boolean gameOverShown = false;

    // Win state
private boolean gameWon = false;
private boolean gameWonShown = false;

    public GamePanel(Inventory inventory, PlayerStats stats) {
        this.inventory = inventory;
        this.stats = stats;

        setPreferredSize(new Dimension(VIEW_COLS * TILE, VIEW_ROWS * TILE));
        setFocusable(true);

        tileMap = new TileMap("map.txt");

        loadImages();
        loadMapEntities();
        updateCamera();

        addKeyListener(new KeyHandler());

        new Timer(16, e -> repaint()).start();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
    }

    // ================= LOAD =================

    private void loadImages() {
        try {
            roadImg = ImageIO.read(new File("assets/road.png"));
            roadgrassImg = ImageIO.read(new File("assets/roadgrass.png"));
            buildingImg = ImageIO.read(new File("assets/building.png"));
            building1Img = ImageIO.read(new File("assets/building1.png"));
            building2Img = ImageIO.read(new File("assets/building2.png"));
            treeImg = ImageIO.read(new File("assets/tree.png"));
            playerImg = ImageIO.read(new File("assets/player.png"));
            armiqImg = ImageIO.read(new File("assets/Armiq.png"));
            weapon1Img = ImageIO.read(new File("assets/weapon1.png"));
            weapon2Img = ImageIO.read(new File("assets/weapon2.png"));

            food1Img = ImageIO.read(new File("assets/food1.png"));
            food2Img = ImageIO.read(new File("assets/food2.png"));
            food3Img = ImageIO.read(new File("assets/food3.png"));

        } catch (Exception e) {
            System.err.println("Images not loaded");
        }
    }

    private void loadMapEntities() {
        armiqte.clear();

        for (int y = 0; y < tileMap.rows; y++) {
            for (int x = 0; x < tileMap.cols; x++) {
                char t = tileMap.get(x, y);
                if (t == 'P') {
                    playerX = x;
                    playerY = y;
                } else if (t == 'A') {
                    armiqte.add(new Armiq(x, y));
                }
            }
        }
    }

    // ================= SAVE / LOAD =================

    private GameState buildState() {
        GameState s = new GameState();

        s.mapId = "LEVEL2";
        s.playerX = playerX;
        s.playerY = playerY;

        s.hp = stats.hp;

        // nëse ke ende getFood() në Inventory, e lëmë kështu
        // (ndryshe mund ta heqim fare nga GameState)
        s.food = inventory.getFood();

        s.hasWeapon1 = inventory.hasWeapon1();
        s.hasWeapon2 = inventory.hasWeapon2();
        s.equippedWeapon = inventory.getEquippedWeapon();

        for (Armiq a : armiqte) {
            s.enemies.add(new GameState.EnemyState(a.x, a.y));
        }

        return s;
    }

    public void applyState(GameState s) {
        if (s == null) return;

        // player
        playerX = s.playerX;
        playerY = s.playerY;

        // stats
        stats.hp = s.hp;
        gameOver = stats.isDead();
        gameOverShown = false;

        // inventory
        inventory.setFood(s.food);
        inventory.setWeapon1(s.hasWeapon1);
        inventory.setWeapon2(s.hasWeapon2);
        inventory.setEquippedWeapon(s.equippedWeapon == null ? WeaponType.NONE : s.equippedWeapon);

        // enemies
        armiqte.clear();
        for (GameState.EnemyState es : s.enemies) {
            armiqte.add(new Armiq(es.x, es.y));
        }

        updateCamera();
        repaint();
    }

    // ================= INPUT =================

    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            // Save / Load të parat
            if (e.getKeyCode() == KeyEvent.VK_F5) {
                try {
                    SaveLoad.save(buildState());
                    System.out.println("Game saved.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return;
            }

            if (e.getKeyCode() == KeyEvent.VK_F9) {
                try {
                    GameState s = SaveLoad.load();
                    if (s != null) {
                        applyState(s);
                        System.out.println("Game loaded.");
                    } else {
                        System.out.println("No save file found.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return;
            }

          if (gameOver || gameWon) return;


            int newX = playerX;
            int newY = playerY;

            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP -> newY--;
                case KeyEvent.VK_DOWN -> newY++;
                case KeyEvent.VK_LEFT -> newX--;
                case KeyEvent.VK_RIGHT -> newX++;

                case KeyEvent.VK_1 -> inventory.equipWeapon(WeaponType.LEVEL1);
                case KeyEvent.VK_2 -> inventory.equipWeapon(WeaponType.LEVEL2);

                case KeyEvent.VK_3 -> inventory.useFood1(stats);
                case KeyEvent.VK_4 -> inventory.useFood2(stats);
                case KeyEvent.VK_5 -> inventory.useFood3(stats);

                default -> { }
            }

            // nëse vetëm equip/food, mos e detyro lëvizjen
            if (newX == playerX && newY == playerY) {
                repaint();
                return;
            }

            if (!tileMap.isWalkable(newX, newY)) return;

            playerX = newX;
            playerY = newY;
            gameStats.steps++;


            updateCamera();
            handleTilePickup();
            moveEnemiesAndFight();

            repaint();
        }
    }

    private void handleTilePickup() {
        char tile = tileMap.get(playerX, playerY);

        // 🍎 Food 1
        if (tile == 'F') {
    inventory.addFood1();
    tileMap.clear(playerX, playerY);
    gameStats.itemsCollected++;
}

if (tile == 'G') {
    inventory.addFood2();
    tileMap.clear(playerX, playerY);
    gameStats.itemsCollected++;
}

if (tile == 'H') {
    inventory.addFood3();
    tileMap.clear(playerX, playerY);
    gameStats.itemsCollected++;
}

if (tile == '1') {
    inventory.pickWeapon(WeaponType.LEVEL1);
    tileMap.clear(playerX, playerY);
    gameStats.itemsCollected++;
}

if (tile == '2') {
    inventory.pickWeapon(WeaponType.LEVEL2);
    tileMap.clear(playerX, playerY);
    gameStats.itemsCollected++;
}

    }

    private void moveEnemiesAndFight() {

        for (Armiq a : new ArrayList<>(armiqte)) {

            a.leviz(tileMap);

            if (a.x == playerX && a.y == playerY) {

                WeaponType playerWeapon = inventory.getEquippedWeapon();
                boolean win = (playerWeapon.power >= a.weapon.power);

                GameFrame frame = (GameFrame) SwingUtilities.getWindowAncestor(this);
                if (frame != null) {
                    frame.showCombat(win, playerImg, armiqImg);
                }

                if (win) {
                    armiqte.remove(a);
                    gameStats.enemiesDefeated++;
                    if (armiqte.isEmpty()) {
    gameWon = true;
    triggerWin();
}

                } else {
                    stats.damage();
                    if (stats.isDead()) {
                        gameOver = true;
                        triggerGameOver();
                    }
                }

                return;
            }
        }
    }

    // ================= GAME OVER =================

    private void triggerGameOver() {
        if (gameOverShown) return;
        gameOverShown = true;

        SwingUtilities.invokeLater(() -> {
            String[] options = {"Restart", "Exit"};
            int choice = JOptionPane.showOptionDialog(
                    this,
                    "Game Over!\n\n" +
"Statistikat:\n" +
"- Hapat: " + gameStats.steps + "\n" +
"- Objekte të mbledhura: " + gameStats.itemsCollected + "\n" +
"- Armiq të mposhtur: " + gameStats.enemiesDefeated + "\n",

                    "Game Over",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice == 0) {
                restartGame();
            } else {
                System.exit(0);
            }
        });
    }

    private void triggerWin() {
    if (gameWonShown) return;
    gameWonShown = true;

    SwingUtilities.invokeLater(() -> {
        String[] options = {"Restart", "Exit"};
        int choice = JOptionPane.showOptionDialog(
                this,
                "TI FITOVE!\n\n" +
                "Statistikat:\n" +
                "- Hapat: " + gameStats.steps + "\n" +
                "- Objekte të mbledhura: " + gameStats.itemsCollected + "\n" +
                "- Armiq të mposhtur: " + gameStats.enemiesDefeated + "\n",
                "Victory",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == 0) {
            restartGame();
        } else {
            System.exit(0);
        }
    });
}


    private void restartGame() {
        tileMap = new TileMap("map.txt");
        loadMapEntities();

        stats.hp = stats.maxHp;

        inventory.setFood(0);
        inventory.setWeapon1(false);
        inventory.setWeapon2(false);
        inventory.setEquippedWeapon(WeaponType.NONE);

        gameOver = false;
        gameOverShown = false;
        gameWon = false;
gameWonShown = false;


        updateCamera();
        gameStats.steps = 0;
gameStats.itemsCollected = 0;
gameStats.enemiesDefeated = 0;

        repaint();
    }

    // ================= CAMERA =================

    private void updateCamera() {
        camX = Math.max(0, Math.min(playerX - VIEW_COLS / 2, tileMap.cols - VIEW_COLS));
        camY = Math.max(0, Math.min(playerY - VIEW_ROWS / 2, tileMap.rows - VIEW_ROWS));
    }

    // ================= DRAW =================

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawMap(g);
        drawEnemies(g);
        drawPlayer(g);
        drawHUD(g);

        if (gameOver) {
            g.setColor(new Color(0, 0, 0, 180));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("GAME OVER", getWidth() / 2 - 150, getHeight() / 2);
        }

        if (gameWon) {
    g.setColor(new Color(255, 255, 255, 180));
    g.fillRect(0, 0, getWidth(), getHeight());
    g.setColor(new Color(0, 120, 0));
    g.setFont(new Font("Arial", Font.BOLD, 48));
    g.drawString("TI FITOVE", getWidth() / 2 - 140, getHeight() / 2);
}

    }

    private void drawMap(Graphics g) {
        for (int y = 0; y < VIEW_ROWS; y++) {
            for (int x = 0; x < VIEW_COLS; x++) {
                int wx = camX + x;
                int wy = camY + y;
                char t = tileMap.get(wx, wy);

                BufferedImage img = switch (t) {
                    case 'N' -> buildingImg;
                    case '#' -> building1Img;
                    case 'B' -> building2Img;
                    case 'T' -> treeImg;
                    case '~' -> roadgrassImg;

                    case 'F' -> food1Img;
                    case 'G' -> food2Img;
                    case 'H' -> food3Img;

                    case '1' -> weapon1Img;
                    case '2' -> weapon2Img;

                    default -> roadImg;
                };

                if (img != null) {
                    g.drawImage(img, x * TILE, y * TILE, TILE, TILE, null);
                }
            }
        }
    }

    private void drawEnemies(Graphics g) {
        for (Armiq a : armiqte) {
            int ax = (a.x - camX) * TILE;
            int ay = (a.y - camY) * TILE;
            if (ax >= 0 && ay >= 0 && ax < VIEW_COLS * TILE && ay < VIEW_ROWS * TILE) {
                g.drawImage(armiqImg, ax, ay, TILE, TILE, null);
            }
        }
    }

    private void drawPlayer(Graphics g) {
        int px = (playerX - camX) * TILE;
        int py = (playerY - camY) * TILE;
        g.drawImage(playerImg, px, py, TILE, TILE, null);
    }

    private void drawHUD(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);

        for (int i = 0; i < stats.hp; i++) {
            int x = 10 + i * 26;
            int y = 10;
            g2.fillOval(x, y, 14, 14);
            g2.fillOval(x + 12, y, 14, 14);
            Polygon p = new Polygon();
            p.addPoint(x - 2, y + 10);
            p.addPoint(x + 14, y + 26);
            p.addPoint(x + 28, y + 10);
            g2.fillPolygon(p);
        }

        g.setColor(Color.WHITE);
        g.drawString("Weapon: " + inventory.getEquippedWeapon(), 10, 55);
        g.drawString("F5 Save | F9 Load", 10, 70);
    }
}
