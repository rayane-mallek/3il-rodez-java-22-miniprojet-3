package view;

import javax.swing.*;
import java.awt.*;

/**
 * Le panneau PenduPanel affiche l'image du pendu et gère les interactions de l'utilisateur pendant le jeu.
 */
public class PenduPanel extends JPanel {
    private int numParts;
    private JLabel attemptsLabel;
    private int remainingAttempts = 7;

    /**
     * Constructeur de la classe PenduPanel.
     * Initialise le panneau et affiche le nombre de tentatives restantes.
     */
    public PenduPanel() {
        this.numParts = 0;
        this.setPreferredSize(new Dimension(200, 300));

        attemptsLabel = new JLabel("Tentatives restantes : " + remainingAttempts);
        add(attemptsLabel);
    }

    /**
     * Diminue le nombre de tentatives restantes.
     */
    public void decreaseAttempts() {
        remainingAttempts--;
        attemptsLabel.setText("Tentatives restantes : " + remainingAttempts);
    }

    /**
     * Augmente le nombre de parties du pendu et affiche une boîte de dialogue en cas de défaite.
     */
    public void increaseParts() {
        numParts++;
        repaint();

        if (numParts >= 7) {
            int option = JOptionPane.showOptionDialog(this,
                    "Le temps est écoulé! Le jeu est terminé. Voulez-vous relancer le jeu?",
                    "Temps écoulé",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new String[]{"Relancer", "Quitter"},
                    "Relancer");

            if (option == JOptionPane.YES_OPTION) {
                // Relancer le jeu
                JFrame window = (JFrame) SwingUtilities.getWindowAncestor(this);
                if (window != null) {
                    window.dispose();
                }
                new MainPanel();
            } else {
                System.exit(0);
            }
        } else {
            decreaseAttempts();
        }
    }

    /**
     * Affiche une boîte de dialogue de victoire avec la possibilité de relancer le jeu.
     */
    public void displayVictoryDialog() {
        int option = JOptionPane.showOptionDialog(this,
                "Félicitations! Vous avez gagné! Voulez-vous relancer le jeu?",
                "Partie Terminée",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Relancer", "Quitter"},
                "Relancer");

        if (option == JOptionPane.YES_OPTION) {
            JFrame window = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
            new MainPanel();
        } else {
            System.exit(0);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        if (numParts >= 1) {
            g.drawLine(width / 2 - 40, height - 20, width / 2 - 40, height - 250);
            g.drawLine(width / 2 - 40, height - 250, width / 2 + 60, height - 250);
            g.drawLine(width / 2 + 60, height - 250, width / 2 + 60, height - 220);
        }

        if (numParts >= 2) {
            g.drawOval(width / 2 + 20, height - 220, 80, 80);
        }
        if (numParts >= 3) {
            g.drawLine(width / 2 + 60, height - 140, width / 2 + 60, height - 40);
        }
        if (numParts >= 4) {
            g.drawLine(width / 2 + 60, height - 120, width / 2 + 20, height - 80);
        }
        if (numParts >= 5) {
            g.drawLine(width / 2 + 60, height - 120, width / 2 + 100, height - 80);
        }
        if (numParts >= 6) {
            g.drawLine(width / 2 + 60, height - 40, width / 2 + 20, height + 40);
        }
        if (numParts >= 7) {
            g.drawLine(width / 2 + 60, height - 40, width / 2 + 100, height + 40);
        }
    }

    /**
     * Renvoie le nombre de parties du pendu actuellement affichées.
     *
     * @return Le nombre de parties du pendu.
     */
    public int getNumParts() {
        return numParts;
    }

    /**
     * Renvoie l'étiquette représentant le nombre de tentatives restantes.
     *
     * @return L'étiquette des tentatives.
     */
    public JLabel getAttemptsLabel() {
        return attemptsLabel;
    }

    /**
     * Renvoie le nombre de tentatives restantes.
     *
     * @return Le nombre de tentatives restantes.
     */
    public int getRemainingAttempts() {
        return remainingAttempts;
    }
}
