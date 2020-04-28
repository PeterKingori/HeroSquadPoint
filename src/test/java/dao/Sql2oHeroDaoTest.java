package dao;

import models.Hero;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Sql2oHeroDaoTest {
    private static Sql2oHeroDao heroDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        //String connectionString = "jdbc:postgresql://localhost:5432/herosquad_test";
//        Sql2o sql2o = new Sql2o(connectionString, null, null);
        String connectionString = "jdbc:postgresql://zeykqwccfpruol" +
                ":73c4fce1607c43ef106184369d54bbc78f748f90ade0c45051119e3c06d64a2f@ec2-52-6-143-153.compute-1.amazonaws.com:5432/d5bo5nhkso48t8\n";
        Sql2o sql2o = new Sql2o(connectionString, "zeykqwccfpruol", "73c4fce1607c43ef106184369d54bbc78f748f90ade0c45051119e3c06d64a2f\n");
        heroDao = new Sql2oHeroDao(sql2o);
        conn = sql2o.open(); //keep connection open through entire test so it does not get erased
    }

    @After // run after every test
    public void tearDown() throws Exception { //I have changed
        System.out.println("clearing database");
        heroDao.clearAllHeroes(); // clear all categories after every test
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
        System.out.println("connection closed");
    }

    //This will confirm that adding a Hero successfully sets the Hero's Id
    @Test
    public void addingHeroSetsId() throws Exception {
        Hero hero = setupNewHero();
        int originalHeroId = hero.getId();
        heroDao.add(hero);
        assertNotEquals(originalHeroId, hero.getId());
    }

    @Test
    public void existingHeroesCanBeFoundById() throws Exception {
        Hero hero = setupNewHero();
        heroDao.add(hero);
        Hero foundHero = heroDao.findById(hero.getId());
        assertEquals(hero, foundHero);
    }

    @Test
    public void allAddedHeroesAreReturned() throws Exception {
        Hero hero = setupNewHero();
        Hero secondHero = new Hero("Superman", 20, "Strength", "Kryptonite", 1);
        heroDao.add(hero);
        heroDao.add(secondHero);
        assertEquals(2, heroDao.getAll().size());
    }

    @Test
    public void noHeroesReturnsEmptyList() throws Exception {
        assertEquals(0, heroDao.getAll().size());
    }

    @Test
    public void updateChangesHeroDetails() throws Exception {
        String initialName = "Batman";
        int initialAge = 35;
        String initialSuperpower = "Wealth";
        String initialWeakness = "Past trauma";
        int initialSquad = 1;
        Hero hero = new Hero(initialName, initialAge, initialSuperpower, initialWeakness, 1);
        heroDao.add(hero);
        heroDao.update(hero.getId(),"Green Lantern", 25, "Memory", "Time",2);
        Hero updatedHero = heroDao.findById(hero.getId());
        assertNotEquals(initialName, updatedHero.getName());
        assertNotEquals(initialAge, updatedHero.getAge());
        assertNotEquals(initialSuperpower, updatedHero.getSuperpower());
        assertNotEquals(initialWeakness, updatedHero.getWeakness());
        assertNotEquals(initialSquad, updatedHero.getSquadId());
    }

//    @Test
//    public void deleteByIdDeletesCorrectHero() throws Exception {
//        Hero hero = setupNewHero();
//        heroDao.add(hero);
//        heroDao.deleteById(hero.getId());
//        assertEquals(0, heroDao.getAll().size());
//    }

    @Test
    public void deleteAllHeroes() throws Exception {
        Hero hero = setupNewHero();
        Hero secondHero = new Hero("Superman", 20, "Strength", "Kryptonite", 1);
        heroDao.add(hero);
        heroDao.add(secondHero);
        int daoSize = heroDao.getAll().size();
        heroDao.clearAllHeroes();
        assertTrue(daoSize > 0 && daoSize > heroDao.getAll().size()); //this is a little overcomplicated, but illustrates well how we might use `assertTrue` in a different way.
    }

    @Test
    public void squadIdIsReturnedCorrectly() throws Exception {
        Hero hero = setupNewHero();
        int originalSquadId = hero.getSquadId();
        heroDao.add(hero);
        assertEquals(originalSquadId, heroDao.findById(hero.getId()).getSquadId());
    }

    //helper methods
    public Hero setupNewHero() {return new Hero("Batman", 35, "Wealth", "Past trauma", 1); }
}
