package dao;

import models.Hero;
import java.util.List;

public interface HeroDao {
    //LIST all heroes
    List<Hero> getAll();

    //CREATE
    void add(Hero hero);

    //READ
    Hero findById(int id);

    //UPDATE
    //void update(int id, String name, int age, String superpower, String weakness);

    //DELETE
    //void deleteHeroById(int id);
    //void clearAllHeroes();

}
