package view;

import controller.Reader;
import controller.WordController;
import model.Word;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame fenetrePrincipale = new JFrame("Pendu de Rayane");
        fenetrePrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetrePrincipale.setSize(800, 600);

        JPanel contentPane = new JPanel(new BorderLayout());
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
                    } else {
                        JOptionPane.showMessageDialog(fenetrePrincipale, "Vous avez déjà deviné cette lettre.");
                    }
                } else {
                    JOptionPane.showMessageDialog(fenetrePrincipale, "Veuillez entrer une seule lettre.");
                }
                lettreField.setText("");
            }
        });
        bottomPanel.add(btnLettre, BorderLayout.EAST);

        fenetrePrincipale.pack();
        fenetrePrincipale.setLocationRelativeTo(null); // Centrer la fenêtre
        fenetrePrincipale.setVisible(true);
    }
}
