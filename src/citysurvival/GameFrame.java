package citysurvival;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameFrame extends JFrame {

    private final GamePanel gamePanel;

    public GameFrame(Inventory inventory, PlayerStats stats) {

        setTitle("City Survival");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        this.gamePanel = new GamePanel(inventory, stats);
        InventoryPanel inventoryPanel = new InventoryPanel(inventory, stats);

        add(gamePanel, BorderLayout.CENTER);
        add(inventoryPanel, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Thirret nga Main (Load nga start menu)
    public void loadState(GameState state) {
        gamePanel.applyState(state);
    }

    public void showCombat(boolean win, BufferedImage playerImg, BufferedImage armiqImg) {
        JDialog dialog = new JDialog(this, "Combat", true);
        dialog.setContentPane(new CombatPanel(win, playerImg, armiqImg));
        dialog.pack();
        dialog.setLocationRelativeTo(this);

        Timer t = new Timer(1000, e -> dialog.dispose());
        t.setRepeats(false);
        t.start();

        dialog.setVisible(true);
    }
}
