package dao;

import models.Hero;
import models.Squad;
import org.sql2o.*;
import java.util.List;

public class Sql2oSquadDao implements SquadDao {
    private final Sql2o sql2o;

    public Sql2oSquadDao(Sql2o sql2o){
        this.sql2o = sql2o; //making the sql2o object available everywhere so we can call methods in it
    }

    @Override
    public List<Squad> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM squads")
                    .executeAndFetch(Squad.class);
        }
    }

    @Override
    public void add(Squad squad) {
        String sql = "INSERT INTO squads (name, cause) VALUES (:name, :cause)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(squad)
                    .executeUpdate()
                    .getKey();
            squad.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public Squad findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM squads WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Squad.class);
        }
    }

    @Override
    public void update(int id, String newName, String newCause){
        String sql = "UPDATE squads SET (name, cause) = (:name, :cause) WHERE " +
                "id = :id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", newName)
                    .addParameter("cause", newCause)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from squads WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllSquads() {
        String sql = "DELETE from squads";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
    @Override
    public List<Hero> getAllHeroesBySquadId(int squadId) {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM heroes WHERE squadId = :squadId")
                    .addParameter("squadId", squadId)
                    .executeAndFetch(Hero.class);
        }
    }

}
