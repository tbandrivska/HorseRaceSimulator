import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RaceTest {

    @Test
    public void testAddHorse() {
        Race race = new Race(10);
        Horse h = new Horse('♞', "TestHorse", 0.5);
        race.addHorse(h, 1);  // We can't directly access lane1Horse, but no error = pass
        assertDoesNotThrow(() -> race.addHorse(h, 1));
    }

    @Test
    public void testHorseWinsRace() {
        Race race = new Race(3);
        Horse h = new Horse('♘', "Speedy", 0.9);
        h.moveForward();
        h.moveForward();
        h.moveForward();
        assertTrue(invokeRaceWonBy(race, h));
    }

    @Test
    public void testRaceNotWonIfFallen() {
        Race race = new Race(3);
        Horse h = new Horse('H', "Tragic", 0.9);
        h.moveForward();
        h.moveForward();
        h.moveForward();
        h.fall(); // manually fall before win check
        assertFalse(invokeRaceWonBy(race, h));
    }

    @Test
    public void testHorseReset() {
        Horse h = new Horse('♞', "ResetBoy", 0.9);
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
    private boolean invokeRaceWonBy(Race race, Horse h) {
        try {
            var method = Race.class.getDeclaredMethod("raceWonBy", Horse.class);
            method.setAccessible(true);
            return (boolean) method.invoke(race, h);
        } catch (Exception e) {
            fail("Couldn't access raceWonBy");
            return false;
        }
    }
}
