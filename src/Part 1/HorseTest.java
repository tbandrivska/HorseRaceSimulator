import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class HorseTest {

    @Test
    public void testMoveForward() {
        Horse h = new Horse('♘', "Max", 0.8);
        int oldPos = h.getDistanceTravelled();
        h.moveForward();
        assertEquals(oldPos + 1, h.getDistanceTravelled());
    }

    @Test
    public void testFallAndHasFallen() {
        Horse h = new Horse('♘', "FallBoy", 0.5);
        assertFalse(h.hasFallen());
        h.fall();
        assertTrue(h.hasFallen());
    }

    @Test
    public void testSetConfidenceBounds() {
        Horse h = new Horse('♘', "Confy", 0.5);
        h.setConfidence(1.2); // above range
        assertEquals(1.0, h.getConfidence());

        h.setConfidence(-0.1); // below range
        assertEquals(0.0, h.getConfidence());
    }

    @Test
    public void testGoBackToStart() {
        Horse h = new Horse('♘', "Restart", 0.5);
        h.moveForward();
        h.fall();
        h.goBackToStart();
        assertEquals(0, h.getDistanceTravelled());
        assertFalse(h.hasFallen());
    }
}
