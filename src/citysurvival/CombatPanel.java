package citysurvival;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CombatPanel extends JPanel {

    private boolean playerWins;
    private BufferedImage playerImg;
    private BufferedImage ArmiqImg;

    public CombatPanel(boolean playerWins,
                        BufferedImage playerImg,
                        BufferedImage ArmiqImg) {

        this.playerWins = playerWins;
        this.playerImg = playerImg;
        this.ArmiqImg = ArmiqImg;

        setPreferredSize(new Dimension(400, 250));
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Background
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        // PLAYER
        if (playerImg != null) {
            g.drawImage(playerImg, 50, 100, 80, 80, null);
        }

        // ENEMY
        if (ArmiqImg != null) {
            g.drawImage(ArmiqImg, 270, 100, 80, 80, null);
        }

        // TEXT
        g.setFont(new Font("Arial", Font.BOLD, 26));
        g.setColor(Color.WHITE);

        if (playerWins) {
            g.drawString("YOU WON!", 130, 60);
        } else {
            g.drawString("YOU LOST!", 120, 60);
        }
    }
}
