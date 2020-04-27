import java.util.List;
import java.util.Map;
import java.util.HashMap;

import dao.Sql2oHeroDao;
import dao.Sql2oSquadDao;
import models.Hero;
import models.Squad;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args){
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/herosquad7.db;INIT=RUNSCRIPT from " +
                "'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oHeroDao heroDao = new Sql2oHeroDao(sql2o);
        Sql2oSquadDao squadDao = new Sql2oSquadDao(sql2o);

        //get: show all heroes in all squads and show all squads
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Squad> squads = squadDao.getAll();
            model.put("squads", squads);
            List<Hero> heroes = heroDao.getAll();
            model.put("heroes", heroes);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to create a new squad
        get("/squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Squad> squads = squadDao.getAll();
            model.put("squads", squads);
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process form to create a new squad
        post("/squads", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String cause = request.queryParams("cause");
            Squad newSquad = new Squad(name, cause);
            squadDao.add(newSquad);
            response.redirect("/");
            return null;
            //return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete all squads and all heroes
        get("/squads/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            squadDao.clearAllSquads();
            heroDao.clearAllHeroes();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete a squad and the heroes it contains
        // /squads/:id/delete

        //get: delete all heroes
        get("/heroes/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            heroDao.clearAllHeroes();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get a specific squad (and the heroes it contains)
        get("/squads/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToFind = Integer.parseInt(request.params("id"));
            Squad foundSquad = squadDao.findById(idOfSquadToFind);
            model.put("squad", foundSquad);
            List<Hero> allHeroesBySquad = squadDao.getAllHeroesBySquadId(idOfSquadToFind);
            model.put("heroes", allHeroesBySquad);
            model.put("squads", squadDao.getAll()); //refresh list of links for navbar
            return new ModelAndView(model, "squad-details.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a squad
        get("/squads/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            //model.put("editSquad", true);
            int idOfSquadToEdit = Integer.parseInt(request.params("id"));
            Squad editSquad = squadDao.findById(idOfSquadToEdit);
            model.put("editSquad", editSquad);
            model.put("squads", squadDao.getAll()); //refresh list of links for navbar
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a squad
        post("/squads/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToEdit = Integer.parseInt(request.params("id"));
            String newName = request.queryParams("name");
            String newCause = request.queryParams("cause");
            squadDao.update(idOfSquadToEdit,newName, newCause);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete an individual hero
        get("/squads/:id/heroes/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int idOfHeroToDelete = Integer.parseInt(request.params("id"));
            heroDao.deleteById(idOfHeroToDelete);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to add a new hero
        get("/heroes/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Squad> allSquads = squadDao.getAll();
            model.put("squads", allSquads);
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process form to add new hero
        post("/heroes/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Squad> allSquads = squadDao.getAll();
            model.put("squads", allSquads);
            String name = request.queryParams("name");
            int age = Integer.parseInt(request.queryParams("age"));
            String superpower = request.queryParams("superpower");
            String weakness = request.queryParams("weakness");
            int squadId = Integer.parseInt(request.queryParams("squadId"));
            Hero newHero = new Hero(name, age, superpower, weakness, squadId);
            heroDao.add(newHero);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show an individual hero that is nested in a squad
        //comment this part out coz there's no where in a squad that you can go to a hero
        get("/heroes/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int idOfHeroToFind = Integer.parseInt(request.params("id"));
            Hero foundHero = heroDao.findById(idOfHeroToFind);
            model.put("hero", foundHero);
            return new ModelAndView(model, "hero-details.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a hero
        get("/heroes/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int idOfHeroToEdit = Integer.parseInt(request.params("id"));
            Hero editHero = heroDao.findById(idOfHeroToEdit);
            model.put("editHero", editHero);
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a hero
        post("/heroes/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String newName = request.queryParams("name");
            int newAge = Integer.parseInt(request.queryParams("age"));
            String newSuperpower = request.queryParams("superpower");
            String newWeakness = request.queryParams("weakness");
            int newSquadId = Integer.parseInt(request.queryParams("squadId"));
            int idOfHeroToEdit = Integer.parseInt(request.queryParams("id"));
            heroDao.update(idOfHeroToEdit,newName, newAge, newSuperpower, newWeakness, newSquadId);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
