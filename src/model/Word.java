package model;

public class Word {
    private String word;
    private String definition;
    private String censoredWord;

    public Word(String word, String definition) {
        this.word = word;
        this.definition = definition;
        this.censoredWord = getDisplayedWord(word);
        System.out.println(word);
        System.out.println(this.censoredWord);
    }

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

    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }

    public String getCensoredWord() {
        return censoredWord;
    }

    public Word setWord(String word) {
        this.word = word;

        return this;
    }

    public Word setCensoredWord(String word) {
        this.censoredWord = word;

        return this;
    }
}
