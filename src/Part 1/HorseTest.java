package Part1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class HorseTest {

    @Test
    public void testMoveForward() {
        Part1.Horse h = new Part1.Horse('♘', "Max", 0.8);
        int oldPos = h.getDistanceTravelled();
        h.moveForward();
        assertEquals(oldPos + 1, h.getDistanceTravelled());
    }

    @Test
    public void testFallAndHasFallen() {
        Part1.Horse h = new Part1.Horse('♘', "FallBoy", 0.5);
        assertFalse(h.hasFallen());
        h.fall();
        assertTrue(h.hasFallen());
    }

    @Test
    public void testSetConfidenceBounds() {
        Part1.Horse h = new Part1.Horse('♘', "Confy", 0.5);
        h.setConfidence(1.2); // above range
        assertEquals(1.0, h.getConfidence());

        h.setConfidence(-0.1); // below range
        assertEquals(0.0, h.getConfidence());
    }

    @Test
    public void testGoBackToStart() {
        Part1.Horse h = new Part1.Horse('♘', "Restart", 0.5);
        h.moveForward();
        h.fall();
        h.goBackToStart();
        assertEquals(0, h.getDistanceTravelled());
        assertFalse(h.hasFallen());
    }
}
