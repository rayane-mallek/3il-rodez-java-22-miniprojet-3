package view;

import controller.Reader;
import controller.WordController;
import model.Word;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Classe principale représentant l'interface utilisateur du jeu du pendu.
 * Cette classe étend JFrame et gère l'affichage et l'interaction avec l'utilisateur.
 */
public class MainPanel extends JFrame {

    /**
     * Constructeur de la classe MainPanel.
     * Initialise l'interface utilisateur du jeu du pendu.
     */
    public MainPanel() {
        JFrame fenetrePrincipale = new JFrame("Pendu de Rayane");
        fenetrePrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetrePrincipale.setSize(800, 800);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        fenetrePrincipale.setContentPane(contentPane);

        PenduPanel penduPanel = new PenduPanel();
        contentPane.add(penduPanel, BorderLayout.CENTER);

        Word word = (new Reader()).getRandomWordAndDefinition();
        WordController controller = new WordController(word, penduPanel);

        String definition = word.getDefinition();

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        contentPane.add(topPanel, BorderLayout.NORTH);

        JLabel labelMot = new JLabel(word.getCensoredWord());
        topPanel.add(labelMot, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        JLabel wrongLettersLabel = new JLabel("Lettres proposées : ");
        topPanel.add(wrongLettersLabel, BorderLayout.CENTER);

        JTextField lettreField = new JTextField();
        bottomPanel.add(lettreField, BorderLayout.CENTER);

        JButton btnLettre = new JButton("Soumettre");
        bottomPanel.add(btnLettre, BorderLayout.EAST);
        btnLettre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lettre = lettreField.getText().toUpperCase();
                if (lettre.length() == 1 && Character.isLetter(lettre.charAt(0))) {
                    char lettreChar = lettre.charAt(0);
                    if (! controller.isLetterAlreadyGuessed(lettreChar)) {
                        controller.checkLetter(lettreChar);
                        labelMot.setText(word.getCensoredWord());
                        updateWrongLettersLabel(controller.getWrongLetters(), wrongLettersLabel);
                    } else {
                        JOptionPane.showMessageDialog(fenetrePrincipale, "Vous avez déjà deviné cette lettre.");
                    }
                } else {
                    JOptionPane.showMessageDialog(fenetrePrincipale, "Veuillez entrer une seule lettre.");
                }
                lettreField.setText("");
            }

            private void updateWrongLettersLabel(List<Character> wrongLetters, JLabel wrongLettersLabel) {
                StringBuilder sb = new StringBuilder("Lettres proposées : ");
                for (char letter : wrongLetters) {
                    sb.append(letter).append(", ");
                }
                wrongLettersLabel.setText(sb.substring(0, sb.length() - 2));
            }
        });
        bottomPanel.add(btnLettre, BorderLayout.EAST);

        JLabel labelDefinition = new JLabel("Définition : " + definition);
        topPanel.add(labelDefinition, BorderLayout.SOUTH);
        labelDefinition.setVisible(false);

        JButton btnDefinition = new JButton("Afficher la définition");
        bottomPanel.add(btnDefinition, BorderLayout.WEST);
        btnDefinition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (labelDefinition.isVisible()) {
                    labelDefinition.setVisible(false);
                    btnDefinition.setText("Afficher la définition");
                } else {
                    labelDefinition.setVisible(true);
                    btnDefinition.setText("Masquer la définition");
                }
            }
        });

        JButton btnStartTimer = new JButton("Démarrer le timer");
        bottomPanel.add(btnStartTimer, BorderLayout.SOUTH);

        JLabel timerLabel = new JLabel("Temps restant : ");
        bottomPanel.add(timerLabel, BorderLayout.NORTH);

        Timer timer = new Timer(1000, new ActionListener() {
            int timeLeft = 60;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeLeft > 0) {
                    timeLeft--;
                    timerLabel.setText("Temps restant : " + timeLeft + " secondes");
                } else {
                    ((Timer)e.getSource()).stop();
                    int option = JOptionPane.showOptionDialog(MainPanel.this,
                            "Le temps est écoulé! Le jeu est terminé. Voulez-vous relancer le jeu?",
                            "Temps écoulé",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            new String[]{"Relancer", "Quitter"},
                            "Relancer");

                    if (option == JOptionPane.YES_OPTION) {
                        setVisible(false);
                        dispose();
                        new MainPanel();
                    } else {
                        System.exit(0);
                    }
                }
            }
        });

        btnStartTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        });

        fenetrePrincipale.setLocationRelativeTo(null); // Centrer la fenêtre
        fenetrePrincipale.setVisible(true);
    }
}
