package models;

import java.util.ArrayList;

public class Hero {
    private String name;
    private static ArrayList<Hero> instances = new ArrayList<Hero>();

    public Hero(String name) {
        this.name = name;
        instances.add(this);
    }

    public String getName() {
        return name;
    }

    public static ArrayList<Hero> getAll() {
        return instances;
    }

    public static void clearAllHeros() {
        instances.clear();
    }
}
