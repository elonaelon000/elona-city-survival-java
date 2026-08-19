package citysurvival;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class InventoryPanel extends JPanel {

    private Inventory inventory;
    private PlayerStats stats;

    private BufferedImage heartImg;
    private BufferedImage foodImg;
    private BufferedImage weapon1Img;
    private BufferedImage weapon2Img;

    public InventoryPanel(Inventory inventory, PlayerStats stats) {
        this.inventory = inventory;
        this.stats = stats;

        setPreferredSize(new Dimension(180, 480));
        setBackground(new Color(200, 230, 200)); // pastel green


        loadImages();
        new Timer(150, e -> repaint()).start();
    
    }

    private void loadImages() {
        try {
            heartImg = ImageIO.read(new File("assets/heart.png"));
            foodImg = ImageIO.read(new File("assets/food.png"));
            weapon1Img = ImageIO.read(new File("assets/weapon1.png"));
            weapon2Img = ImageIO.read(new File("assets/weapon2.png"));
        } catch (Exception e) {
            System.err.println("Inventory images not loaded");
        }
    }

   @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;

    // Font dhe ngjyra teksti
    g2.setFont(new Font("Arial", Font.BOLD, 16));
    g2.setColor(new Color(25, 25, 25));

    int x = 20;
    int y = 35;
    int line = 22; // distanca mes rreshtave

    // ===== HP =====
    g2.drawString("HP:", x, y);
    y += line;

    // Shfaq zemrat (nëse i ke imazhet e zemrës, mund t'i vizatosh; këtu vetëm tekst)
    g2.setFont(new Font("Arial", Font.PLAIN, 14));
    g2.drawString(stats.hp + " / " + stats.maxHp, x, y);
    y += line + 10;

    // ===== FOOD =====
    g2.setFont(new Font("Arial", Font.BOLD, 16));
    g2.drawString("Food:", x, y);
    y += line;

    g2.setFont(new Font("Arial", Font.PLAIN, 14));
    g2.drawString("3: Food 1 = " + inventory.getFood1(), x, y);
    y += line;

    g2.drawString("4: Food 2 = " + inventory.getFood2(), x, y);
    y += line;

    g2.drawString("5: Food 3 = " + inventory.getFood3(), x, y);
    y += line + 10;

    // ===== WEAPON =====
    g2.setFont(new Font("Arial", Font.BOLD, 16));
    g2.drawString("Weapon:", x, y);
    y += line;

    g2.setFont(new Font("Arial", Font.PLAIN, 14));

    // Shfaq çfarë ke marrë
    if (!inventory.hasWeapon1() && !inventory.hasWeapon2()) {
        g2.drawString("None", x, y);
        y += line;
    } else {
        if (inventory.hasWeapon1()) {
            String t = "1: Weapon 1";
            if (inventory.getEquippedWeapon() == WeaponType.LEVEL1) t += " (equipped)";
            g2.drawString(t, x, y);
            y += line;
        }
        if (inventory.hasWeapon2()) {
            String t = "2: Weapon 2";
            if (inventory.getEquippedWeapon() == WeaponType.LEVEL2) t += " (equipped)";
            g2.drawString(t, x, y);
            y += line;
        }
    }

    y += 6;
    g2.drawString("Press 1 or 2 to equip", x, y);
}

}
