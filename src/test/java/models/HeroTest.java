package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class HeroTest {

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
        assertEquals(1, hero.getSquadId());
    }

    //helper methods
    public Hero setupNewHero() { return new Hero("Batman", 20, "Wealth", "Trauma", 1); }

}