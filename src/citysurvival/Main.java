package citysurvival;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // Shfaq menunë e startit
            StartMenu menu = new StartMenu(null);
            menu.setVisible(true);

            int choice = menu.getChoice();

            // Nëse user e mbylli dritaren pa zgjedhur
            if (choice == -1) {
                System.exit(0);
                return;
            }

            // Krijo objektet e lojës
            Inventory inventory = new Inventory();
            PlayerStats stats = new PlayerStats();

            // Krijo frame
            GameFrame frame = new GameFrame(inventory, stats);

            // Nëse zgjodhi Load Game, ngarko nga file
            if (choice == 1) {
                try {
                    GameState state = SaveLoad.load();
                    if (state != null) {
                        frame.loadState(state);
                        System.out.println("Game loaded from start menu.");
                    } else {
                        System.out.println("No save file found. Starting new game.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("Load failed. Starting new game.");
                }
            }
        });
    }
}
