package dao;

import models.Hero;
import models.Squad;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Sql2oSquadDaoTest {
    private Sql2oSquadDao squadDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        squadDao = new Sql2oSquadDao(sql2o);
        conn = sql2o.open(); //keep connection open through entire test so it does not get erased
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    //This will confirm that adding a Squad successfully sets the Squad's Id
    @Test
    public void addingSquadSetsId() throws Exception {
        Squad squad = setupNewSquad();
        int originalSquadId = squad.getId();
        squadDao.add(squad);
        assertNotEquals(originalSquadId, squad.getId());
    }

    @Test
    public void existingSquadsCanBeFoundById() throws Exception {
        Squad squad = setupNewSquad();
        squadDao.add(squad);
        Squad foundSquad = squadDao.findById(squad.getId());
        assertEquals(squad, foundSquad);
    }

    @Test
    public void allAddedSquadsAreReturned() throws Exception {
        Squad squad = setupNewSquad();
        Squad secondSquad = new Squad("Justice League", "Stupidity");
        squadDao.add(squad);
        squadDao.add(secondSquad);
        assertEquals(2, squadDao.getAll().size());
    }

    @Test
    public void noSquadsReturnsEmptyList() throws Exception {
        assertEquals(0, squadDao.getAll().size());
    }

    @Test
    public void updateChangesSquadDetails() throws Exception {
        String initialName = "Justice League";
        String initialCause = "Stupidity";
        Squad squad = new Squad(initialName, initialCause);
        squadDao.add(squad);
        squadDao.update(squad.getId(), "Avengers", "Injustice");
        Squad updatedSquad = squadDao.findById(squad.getId());
        assertNotEquals(initialName, updatedSquad.getName());
        assertNotEquals(initialCause, updatedSquad.getCause());
    }

    @Test
    public void deleteByIdDeletesCorrectSquad() throws Exception {
        Squad squad = setupNewSquad();
        squadDao.add(squad);
        squadDao.deleteById(squad.getId());
        assertEquals(0, squadDao.getAll().size());
    }

    @Test
    public void deleteAllSquads() throws Exception {
        Squad squad = setupNewSquad();
        Squad secondSquad = new Squad("Justice League", "Poverty");
        squadDao.add(squad);
        squadDao.add(secondSquad);
        int daoSize = squadDao.getAll().size();
        squadDao.clearAllSquads();
        assertTrue(daoSize > 0 && daoSize > squadDao.getAll().size()); //this is a little
        // overcomplicated, but illustrates well how we might use `assertTrue` in a different way.
    }

    //helper methods
    public Squad setupNewSquad() {
        return new Squad("Avengers", "Injustice");
    }
}
