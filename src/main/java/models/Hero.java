package models;

import java.util.ArrayList;

public class Hero {
    private String name;
    private static ArrayList<Hero> instances = new ArrayList<Hero>();
    private int age;
    private String superpower;
    private String weakness;

    public Hero(String name, int age, String superpower, String weakness) {
        this.name = name;
        this.age = age;
        this.superpower = superpower;
        this.weakness = weakness;
        instances.add(this);
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
}
