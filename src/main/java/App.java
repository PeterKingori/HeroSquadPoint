//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.HashMap;
//
//import dao.Sql2oHeroDao;
//import models.Hero;
//import org.sql2o.Sql2o;
//import spark.ModelAndView;
//import spark.template.handlebars.HandlebarsTemplateEngine;
//import static spark.Spark.*;
//
//public class App {
//    public static void main(String[] args){
//        staticFileLocation("/public");
//        String connectionString = "jdbc:h2:~/herosquad.db;INIT=RUNSCRIPT from " +
//                "'classpath:db/create.sql'";
//        Sql2o sql2o = new Sql2o(connectionString, "", "");
//        Sql2oHeroDao heroDao = new Sql2oHeroDao(sql2o);
//
//        //get: delete all heroes
//        get("/heroes/delete", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            heroDao.clearAllHeroes();
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        //get: delete an individual hero
//        get("/heroes/:id/delete", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            int idOfHeroToDelete = Integer.parseInt(request.params("id"));
//            heroDao.deleteById(idOfHeroToDelete);
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        //get: show all heroes
//        get("/", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            List<Hero> heroes = heroDao.getAll();
//            model.put("heroes", heroes);
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        //get: show form to add new hero
//        get("/heroes/new", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            return new ModelAndView(model, "hero-form.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        //post: process form to add new hero
//        post("/heroes/new", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            String name = request.queryParams("name");
//            int age = Integer.parseInt(request.queryParams("age"));
//            String superpower = request.queryParams("superpower");
//            String weakness = request.queryParams("weakness");
//            Hero newHero = new Hero(name, age, superpower, weakness);
//            heroDao.add(newHero);
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        //get: show an individual hero and the details
//        get("/heroes/:id", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            int idOfHeroToFind = Integer.parseInt(request.params("id"));
//            Hero foundHero = heroDao.findById(idOfHeroToFind);
//            model.put("hero", foundHero);
//            return new ModelAndView(model, "hero-details.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        //get: show a form to update a hero
//        get("/heroes/:id/update", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            int idOfHeroToEdit = Integer.parseInt(request.params("id"));
//            Hero editHero = heroDao.findById(idOfHeroToEdit);
//            model.put("editHero", editHero);
//            return new ModelAndView(model, "hero-form.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        //post: process a form to update a hero
//        post("/heroes/:id/update", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            String newName = request.queryParams("name");
//            int newAge = Integer.parseInt(request.queryParams("age"));
//            String newSuperpower = request.queryParams("superpower");
//            String newWeakness = request.queryParams("weakness");
//            int idOfHeroToEdit = Integer.parseInt(request.params("id"));
//            heroDao.update(idOfHeroToEdit, newName, newAge, newSuperpower, newWeakness);
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());
//
//    }
//}
