package models;

public class Squad {
    private String name;
    private String cause;
    private int maxSize;
    private int id;

    public Squad(String name, String cause, int maxSize) {
        this.name = name;
        this.cause = cause;
        this.maxSize = maxSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Squad squad = (Squad) o;

        if (maxSize != squad.maxSize) return false;
        if (id != squad.id) return false;
        if (!name.equals(squad.name)) return false;
        return cause.equals(squad.cause);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + cause.hashCode();
        result = 31 * result + maxSize;
        result = 31 * result + id;
        return result;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
