package model;

/**
 * La classe Word représente un mot dans le jeu du pendu.
 */
public class Word {
    private String word;
    private String definition;
    private String censoredWord;

    /**
     * Constructeur de la classe Word.
     *
     * @param word       Le mot à deviner.
     * @param definition La définition du mot.
     */
    public Word(String word, String definition) {
        this.word = word;
        this.definition = definition;
        this.censoredWord = getDisplayedWord(word);
    }

    /**
     * Retourne le mot à deviner.
     *
     * @return Le mot à deviner.
     */
    public String getWord() {
        return word;
    }

    /**
     * Retourne la définition du mot.
     *
     * @return La définition du mot.
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * Retourne le mot censuré, où seuls les premières et dernières lettres sont affichées, le reste étant remplacé par des tirets.
     *
     * @return Le mot censuré.
     */
    public String getCensoredWord() {
        return censoredWord;
    }

    /**
     * Définit le mot à deviner.
     *
     * @param word Le mot à définir.
     * @return Une référence à cet objet Word.
     */
    public Word setWord(String word) {
        this.word = word;
        return this;
    }

    /**
     * Définit le mot censuré.
     *
     * @param word Le mot censuré à définir.
     * @return Une référence à cet objet Word.
     */
    public Word setCensoredWord(String word) {
        this.censoredWord = word;
        return this;
    }

    /**
     * Génère une représentation du mot censuré, où seuls les premières et dernières lettres sont affichées, le reste étant remplacé par des tirets.
     *
     * @param mot Le mot à censurer.
     * @return Le mot censuré.
     */
    private static String getDisplayedWord(String mot) {
        if (mot.length() <= 2) {
            return mot;
        }

        StringBuilder displayedWord = new StringBuilder();
        displayedWord.append(mot.charAt(0));
        for (int i = 1; i < mot.length() - 1; i++) {
            displayedWord.append("-");
        }
        displayedWord.append(mot.charAt(mot.length() - 1));

        return displayedWord.toString();
    }
}
