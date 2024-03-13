package test;

import model.Word;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WordTest {

    @Test
    void testGetWord() {
        Word word = new Word("Test", "This is a test");
        assertEquals("Test", word.getWord());
    }

    @Test
    void testGetDefinition() {
        Word word = new Word("Test", "This is a test");
        assertEquals("This is a test", word.getDefinition());
    }

    @Test
    void testGetCensoredWord() {
        Word word = new Word("Test", "This is a test");
        assertEquals("T--t", word.getCensoredWord());
    }

    @Test
    void testSetWord() {
        Word word = new Word("Test", "This is a test");
        word.setWord("NewWord");
        assertEquals("NewWord", word.getWord());
    }

    @Test
    void testSetCensoredWord() {
        Word word = new Word("Test", "This is a test");
        word.setCensoredWord("NewCensoredWord");
        assertEquals("NewCensoredWord", word.getCensoredWord());
    }
}
