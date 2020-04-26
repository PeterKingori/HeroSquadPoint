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
    public void AllHerosAreCorrectlyReturned_true() throws Exception {
        Hero hero = setupNewHero();
        Hero secondhero = new Hero("Superman", 30, "Strength", "Kryptonite");
        assertEquals(2, Hero.getAll().size());
    }

    @Test
    public void AllHerosContainAllHeros_true() throws Exception {
        Hero hero = setupNewHero();
        Hero secondHero = new Hero("Superman", 30, "Strength", "Kryptonite");
        assertTrue(Hero.getAll().contains(hero));
        assertTrue(Hero.getAll().contains(secondHero));
    }

    @Test
    public void HeroInstantiatesWithProperties_true() throws Exception {
        Hero hero = new Hero("Batman", 20, "Wealth", "Trauma");
        assertEquals("Batman", hero.getName());
        assertEquals(20, hero.getAge());
        assertEquals("Wealth", hero.getSuperpower());
        assertEquals("Trauma", hero.getWeakness());
    }

    @Test
    public void getId_heroInstantiatedWithAnId_1() throws Exception {
        Hero.clearAllHeros();
        Hero hero = setupNewHero();
        assertEquals(1, hero.getId());
    }

    @Test
    public void findReturnsCorrectHero() throws Exception {
        Hero hero = setupNewHero();
        assertEquals(1, Hero.findById(hero.getId()).getId());
    }

    @Test
    public void findReturnsCorrectHeroWhenMoreThanOneHeroExists() throws Exception {
        Hero hero = setupNewHero();
        Hero secondHero = new Hero("Superman", 30, "Strength", "Kryptonite");
        assertEquals(2, Hero.findById(secondHero.getId()).getId());
    }

    //helper methods
    public Hero setupNewHero() { return new Hero("Batman", 20, "Wealth", "Trauma"); }
}