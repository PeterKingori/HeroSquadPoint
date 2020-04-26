package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeroTest {

//    @Before
//    public void setUp() throws Exception {
//    }

//    @After
//    public void tearDown() throws Exception {
//        Hero.clearAllHeroes();
//    }

    @Test
    public void NewHeroObjectIsCorrectlyCreated_true() throws Exception {
        Hero hero = setupNewHero();
        assertEquals(true, hero instanceof Hero);
    }

    @Test
    public void HeroInstantiatesWithProperties_true() throws Exception {
        Hero hero = setupNewHero();
        assertEquals("Batman", hero.getName());
        assertEquals(20, hero.getAge());
        assertEquals("Wealth", hero.getSuperpower());
        assertEquals("Trauma", hero.getWeakness());
    }

    //helper methods
    public Hero setupNewHero() { return new Hero("Batman", 20, "Wealth", "Trauma"); }

//    @Test
//    public void AllHerosAreCorrectlyReturned_true() throws Exception {
//        Hero hero = setupNewHero();
//        Hero secondhero = new Hero("Superman", 30, "Strength", "Kryptonite");
//        assertEquals(2, Hero.getAll().size());
//    }

//    @Test
//    public void AllHerosContainAllHeros_true() throws Exception {
//        Hero hero = setupNewHero();
//        Hero secondHero = new Hero("Superman", 30, "Strength", "Kryptonite");
//        assertTrue(Hero.getAll().contains(hero));
//        assertTrue(Hero.getAll().contains(secondHero));
//    }

//    @Test
//    public void getId_heroInstantiatedWithAnId_1() throws Exception {
//        Hero.clearAllHeroes();
//        Hero hero = setupNewHero();
//        assertEquals(1, hero.getId());
//    }

//    @Test
//    public void findReturnsCorrectHero() throws Exception {
//        Hero hero = setupNewHero();
//        assertEquals(1, Hero.findById(hero.getId()).getId());
//    }

//    @Test
//    public void findReturnsCorrectHeroWhenMoreThanOneHeroExists() throws Exception {
//        Hero hero = setupNewHero();
//        Hero secondHero = new Hero("Superman", 30, "Strength", "Kryptonite");
//        assertEquals(2, Hero.findById(secondHero.getId()).getId());
//    }

//    @Test
//    public void updateChangesHeroDetails() throws Exception {
//        Hero hero = setupNewHero();
//        String formerName = hero.getName();
//        int formerAge = hero.getAge();
//        String formerSuperpower = hero.getSuperpower();
//        String formerWeakness = hero.getWeakness();
//        int formerId = hero.getId();
//        hero.update("Green Lantern", 25, "Memory", "Time");
//        assertEquals(formerId, hero.getId());
//        assertNotEquals(formerName, hero.getName());
//    }

//    @Test
//    public void deleteDeleteASpecificHero() throws Exception {
//        Hero hero = setupNewHero();
//        Hero secondHero = new Hero("Superman", 30, "Strength", "Kryptonite");
//        hero.deleteHero();
//        assertEquals(1, Hero.getAll().size());
//        assertEquals(2, Hero.getAll().get(0).getId());
//    }

//    @Test
//    public void deleteAllHeroes() throws Exception {
//        Hero hero = setupNewHero();
//        Hero secondHero = new Hero("Superman", 30, "Strength", "Kryptonite");
//        Hero.clearAllHeroes();
//        assertEquals(0, Hero.getAll().size());
//    }

}