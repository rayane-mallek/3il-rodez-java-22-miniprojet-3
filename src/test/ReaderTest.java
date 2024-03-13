package test;

import controller.Reader;
import model.Word;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class ReaderTest {

    @Test
    void testGetRandomWordAndDefinition() throws IOException {
        Reader reader = new Reader();
        Word word = reader.getRandomWordAndDefinition();
        assertNotNull(word);
        assertNotNull(word.getWord());
        assertNotNull(word.getDefinition());
    }

    @Test
    void testGetWord() {
        Reader reader = new Reader();
        String word = reader.getWord("Hello World");
        assertEquals("Hello", word);
    }

    @Test
    void testGetDefinition() {
        Reader reader = new Reader();
        String definition = reader.getDefinition("Hello World");
        assertEquals("World", definition);
    }
}

