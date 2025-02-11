package models;

public class Hospital {
    private int id;
    private String name;
    private String location;

    public Hospital() {

    }

    public Hospital(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Hospital(int id, String name, String location) {
        this(name, location);
        this.id = id;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
