package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeroTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        Hero.clearAllHeros();
    }

    @Test
    public void NewHeroObjectIsCorrectlyCreated_true() throws Exception {
        Hero hero = setupNewHero();
        assertEquals(true, hero instanceof Hero);
    }

    @Test
    public void HeroInstantiatesWithName_true() throws Exception {
        Hero hero = setupNewHero();
        assertEquals("Batman", hero.getName());
    }

    @Test
    public void AllHerosAreCorrectlyReturned_true() {
        Hero hero = setupNewHero();
        Hero secondhero = new Hero("Superman");
        assertEquals(2, Hero.getAll().size());
    }

    @Test
    public void AllHerosContainAllHeros_true() {
        Hero hero = setupNewHero();
        Hero secondHero = new Hero("Superman");
        assertTrue(Hero.getAll().contains(hero));
        assertTrue(Hero.getAll().contains(secondHero));
    }

    //helper methods
    public Hero setupNewHero() { return new Hero("Batman"); }
}