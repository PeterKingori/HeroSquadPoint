package models;

import dao.SquadDao;
import org.junit.Test;

import static org.junit.Assert.*;

public class SquadTest {
    @Test
    public void NewSquadObjectIsCorrectlyCreated_true() throws Exception {
        Squad squad = setupNewSquad();
        assertEquals(true, squad instanceof Squad);
    }

    @Test
    public void HeroInstantiatesWithProperties_true() throws Exception {
        Squad squad = setupNewSquad();
        assertEquals("Avengers", squad.getName());
        assertEquals("Crime", squad.getCause());
        assertEquals(5, squad.getMaxSize());
    }

    //helper methods
    public Squad setupNewSquad() { return new Squad("Avengers", "Crime", 5); }
}
