package citysurvival;

import javax.swing.*;
import java.awt.*;

public class StartMenu extends JDialog {

    private int choice = -1; 
    // 0 = New Game, 1 = Load Game, -1 = closed

    public StartMenu(Frame owner) {
        super(owner, "City Survival", true);

        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("City Survival", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        add(title, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new GridLayout(2, 1, 10, 10));

        JButton btnNew = new JButton("New Game");
        JButton btnLoad = new JButton("Load Game");

        btnNew.setFont(new Font("Arial", Font.BOLD, 16));
        btnLoad.setFont(new Font("Arial", Font.BOLD, 16));

        center.add(btnNew);
        center.add(btnLoad);

        add(center, BorderLayout.CENTER);

        JLabel hint = new JLabel("Choose an option to start.", SwingConstants.CENTER);
        add(hint, BorderLayout.SOUTH);

        btnNew.addActionListener(e -> {
            choice = 0;
            dispose();
        });

        btnLoad.addActionListener(e -> {
            choice = 1;
            dispose();
        });

        setSize(320, 220);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public int getChoice() {
        return choice;
    }
}
