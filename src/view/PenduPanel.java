package view;

import javax.swing.*;
import java.awt.*;

public class PenduPanel extends JPanel {
    private int numParts;

    public PenduPanel() {
        this.numParts = 0;
        this.setPreferredSize(new Dimension(200, 300));
    }

    public void increaseParts() {
        numParts++;
        repaint();

        if (numParts >= 7) {
            // Afficher la boîte de dialogue
            int option = JOptionPane.showOptionDialog(this,
                    "Perdu! Le pendu est complet. Voulez-vous relancer le jeu?",
                    "Partie Terminée",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new String[]{"Relancer", "Quitter"},
                    "Relancer");

            if (option == JOptionPane.YES_OPTION) {

            } else {
                System.exit(0);
            }
        }
    }

    public void displayVictoryDialog() {
        JOptionPane.showMessageDialog(null, "Félicitations! Vous avez gagné!");
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        if (numParts >= 1) {
            g.drawLine(width/2 - 40, height - 20, width/2 - 40, height - 250);
            g.drawLine(width/2 - 40, height - 250, width/2 + 60, height - 250);
            g.drawLine(width/2 + 60, height - 250, width/2 + 60, height - 220);
        }

        if (numParts >= 2) {
            g.drawOval(width/2 + 20, height - 220, 80, 80);
        }
        if (numParts >= 3) {
            g.drawLine(width/2 + 60, height - 140, width/2 + 60, height - 40);
        }
        if (numParts >= 4) {
            g.drawLine(width/2 + 60, height - 120, width/2 + 20, height - 80);
        }
        if (numParts >= 5) {
            g.drawLine(width/2 + 60, height - 120, width/2 + 100, height - 80);
        }
        if (numParts >= 6) {
            g.drawLine(width/2 + 60, height - 40, width/2 + 20, height + 40);
        }
        if (numParts >= 7) {
            g.drawLine(width/2 + 60, height - 40, width/2 + 100, height + 40);
        }
    }
}
