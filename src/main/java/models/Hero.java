package models;

import java.util.ArrayList;

public class Hero {
    private String name;
    private static ArrayList<Hero> instances = new ArrayList<Hero>();
    private int age;
    private String superpower;
    private String weakness;
    private int id;

    public Hero(String name, int age, String superpower, String weakness) {
        this.name = name;
        this.age = age;
        this.superpower = superpower;
        this.weakness = weakness;
        instances.add(this);
        this.id = instances.size();
    }

    public String getName() {
        return name;
    }

    public String getSuperpower() {
        return superpower;
    }

    public int getAge() {
        return age;
    }

    public String getWeakness() {
        return weakness;
    }

    public static ArrayList<Hero> getAll() {
        return instances;
    }

    public static void clearAllHeros() {
        instances.clear();
    }

    public int getId() {
        return id;
    }

    public static Hero findById(int id) {
        return instances.get(id - 1);
    }

    public void update(String name, int age, String superpower, String weakness) {
        this.name = name;
        this.age = age;
        this.superpower = superpower;
        this.weakness = weakness;
    }
}
