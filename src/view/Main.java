package view;

import controller.Reader;
import controller.WordController;
import model.Word;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Main {
    public static void main(String[] args) {
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

        JLabel labelMot = new JLabel(word.getCensoredWord());
        contentPane.add(labelMot, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        JLabel wrongLettersLabel = new JLabel("Lettres proposées : ");
        bottomPanel.add(wrongLettersLabel, BorderLayout.NORTH);

        JTextField lettreField = new JTextField();
        bottomPanel.add(lettreField, BorderLayout.CENTER);

        JButton btnLettre = new JButton("Soumettre");
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
        labelDefinition.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(labelDefinition);
        labelDefinition.setVisible(false);

        JButton btnDefinition = new JButton("Afficher la définition");
        bottomPanel.add(btnDefinition, BorderLayout.NORTH);
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

        fenetrePrincipale.setLocationRelativeTo(null); // Centrer la fenêtre
        fenetrePrincipale.setVisible(true);
    }
}
