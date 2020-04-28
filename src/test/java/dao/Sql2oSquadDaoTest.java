package dao;

import models.Hero;
import models.Squad;
import org.junit.After;
import org.junit.Before;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Sql2oSquadDaoTest {
    private static Sql2oSquadDao squadDao;
    private static Sql2oHeroDao heroDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/herosquad_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        squadDao = new Sql2oSquadDao(sql2o);
        heroDao = new Sql2oHeroDao(sql2o);
        conn = sql2o.open(); //keep connection open through entire test so it does not get erased
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        squadDao.clearAllSquads();
        heroDao.clearAllHeroes();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close(); // close connection once after this entire test file is finished
        System.out.println("connection closed");
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
    public void allAddedSquadsAreReturnedByGetAll() throws Exception {
        Squad squad = setupNewSquad();
        Squad secondSquad = new Squad("Justice League", "Stupidity", 5);
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
        int initialMaxSize = 3;
        Squad squad = new Squad(initialName, initialCause, initialMaxSize);
        squadDao.add(squad);
        squadDao.update(squad.getId(), "Avengers", "Injustice", 5);
        Squad updatedSquad = squadDao.findById(squad.getId());
        assertNotEquals(initialName, updatedSquad.getName());
        assertNotEquals(initialCause, updatedSquad.getCause());
        assertNotEquals(initialMaxSize, updatedSquad.getMaxSize());
        assertEquals(squad.getId(), updatedSquad.getId());
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
        Squad secondSquad = new Squad("Justice League", "Poverty", 6);
        squadDao.add(squad);
        squadDao.add(secondSquad);
        int daoSize = squadDao.getAll().size();
        squadDao.clearAllSquads();
        int newDaoSize = squadDao.getAll().size();
        assertTrue(newDaoSize == 0);
        //assertTrue(daoSize > 0 && daoSize > squadDao.getAll().size()); //this is a little
        // overcomplicated, but illustrates well how we might use `assertTrue` in a different way.
    }

    @Test
    public void getAllHeroesBySquadReturnsHeroesCorrectly() throws Exception {
        Squad squad = setupNewSquad();
        squadDao.add(squad);
        int squadId = squad.getId();
        Hero firstHero = new Hero("Thor", 30, "The Hammer", "Anger", squadId);
        Hero secondHero = new Hero("Antman", 40, "Shrinking", "Size",squadId);
        Hero thirdHero = new Hero("Iron Man", 45, "Iron Man Suit", "Fear",squadId);
        heroDao.add(firstHero);
        heroDao.add(secondHero);
        assertEquals(2, squadDao.getAllHeroesBySquadId(squadId).size());
        assertTrue(squadDao.getAllHeroesBySquadId(squadId).contains(firstHero));
        assertTrue(squadDao.getAllHeroesBySquadId(squadId).contains(secondHero));
        assertFalse(squadDao.getAllHeroesBySquadId(squadId).contains(thirdHero));
    }

    //helper methods
    public Squad setupNewSquad() {
        return new Squad("Avengers", "Injustice", 7);
    }
}