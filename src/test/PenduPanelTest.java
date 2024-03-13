package test;

import org.junit.jupiter.api.Test;
import view.PenduPanel;

import static org.junit.jupiter.api.Assertions.*;

public class PenduPanelTest {

    @Test
    void testDecreaseAttempts() {
        PenduPanel penduPanel = new PenduPanel();
        int initialRemainingAttempts = penduPanel.getRemainingAttempts();
        penduPanel.decreaseAttempts();
        assertEquals(initialRemainingAttempts - 1, penduPanel.getRemainingAttempts());
    }

    @Test
    void testIncreaseParts() {
        PenduPanel penduPanel = new PenduPanel();
        int initialNumParts = penduPanel.getNumParts();
        penduPanel.increaseParts();
        assertEquals(initialNumParts + 1, penduPanel.getNumParts());
    }
}


