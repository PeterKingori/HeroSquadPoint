package models;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SquadTest {

    @Test
    public void NewSquadObjectIsCorrectlyCreated_true() throws Exception {
        Squad squad = setupNewSquad();
        assertEquals(true, squad instanceof Squad);
    }
    @Test
    public void SquadInstantiatesWithProperties_true() throws Exception {
        Squad squad = setupNewSquad();
        assertEquals("Avengers", squad.getName());
        assertEquals("Injustice", squad.getCause());
    }

    //helper methods
    public Squad setupNewSquad() {
        return new Squad("Avengers", "Injustice");
    }
}
