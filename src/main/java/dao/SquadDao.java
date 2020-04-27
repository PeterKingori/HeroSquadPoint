package dao;

import models.Squad;
import models.Hero;
import java.util.List;

public interface SquadDao {
    //LIST all squads
    List<Squad> getAll();

    //LIST all heroes in a particular squad
    List<Hero> getAllHeroesBySquadId(int squadId);

    //CREATE
    void add(Squad squad);

    //READ
    Squad findById(int id);

    //UPDATE
    void update(int id, String name, String cause);

    //DELETE
    void deleteById(int id);
    void clearAllSquads();

}
