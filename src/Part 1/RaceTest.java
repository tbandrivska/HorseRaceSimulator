package Part1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RaceTest {

    @Test
    public void testAddHorse() {
        Part1.Race race = new Part1.Race(10);
        Part1.Horse h = new Part1.Horse('♞', "TestHorse", 0.5);
        race.addHorse(h, 1);  // We can't directly access lane1Horse, but no error = pass
        assertDoesNotThrow(() -> race.addHorse(h, 1));
    }

    @Test
    public void testHorseWinsRace() {
        Part1.Race race = new Part1.Race(3);
        Part1.Horse h = new Part1.Horse('♘', "Speedy", 0.9);
        h.moveForward();
        h.moveForward();
        h.moveForward();
        assertTrue(invokeRaceWonBy(race, h));
    }

    @Test
    public void testRaceNotWonIfFallen() {
        Part1.Race race = new Part1.Race(3);
        Part1.Horse h = new Part1.Horse('H', "Tragic", 0.9);
        h.moveForward();
        h.moveForward();
        h.moveForward();
        h.fall(); // manually fall before win check
        assertFalse(invokeRaceWonBy(race, h));
    }

    @Test
    public void testHorseReset() {
        Part1.Horse h = new Part1.Horse('♞', "ResetBoy", 0.9);
        h.moveForward();
        h.moveForward();
        h.fall();

        // Ensure it's moved and fallen
        assertTrue(h.getDistanceTravelled() > 0);
        assertTrue(h.hasFallen());

        // Now reset
        h.goBackToStart();

        // Check it's reset
        assertEquals(0, h.getDistanceTravelled());
        assertFalse(h.hasFallen());
    }


    // Helper to access private method raceWonBy
    private boolean invokeRaceWonBy(Part1.Race race, Part1.Horse h) {
        try {
            var method = Part1.Race.class.getDeclaredMethod("raceWonBy", Part1.Horse.class);
            method.setAccessible(true);
            return (boolean) method.invoke(race, h);
        } catch (Exception e) {
            fail("Couldn't access raceWonBy");
            return false;
        }
    }
}
