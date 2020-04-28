package models;

public class Hero {
    private String name;
    private int age;
    private String superpower;
    private String weakness;
    private int id;
    private int squadId;

    public Hero(String name, int age, String superpower, String weakness, int squadId) {
        this.name = name;
        this.age = age;
        this.superpower = superpower;
        this.weakness = weakness;
        this.squadId = squadId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hero hero = (Hero) o;

        if (age != hero.age) return false;
        if (id != hero.id) return false;
        if (squadId != hero.squadId) return false;
        if (!name.equals(hero.name)) return false;
        if (!superpower.equals(hero.superpower)) return false;
        return weakness.equals(hero.weakness);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age;
        result = 31 * result + superpower.hashCode();
        result = 31 * result + weakness.hashCode();
        result = 31 * result + id;
        result = 31 * result + squadId;
        return result;
    }

    public String getName() { return name; }

    public int getAge() {
        return age;
    }

    public String getSuperpower() {
        return superpower;
    }

    public String getWeakness() {
        return weakness;
    }

    public int getId() {
        return id;
    }

    public int getSquadId() { return squadId; }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSuperpower(String superpower) {
        this.superpower = superpower;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSquadId(int squadId) {
        this.squadId = squadId;
    }
}
