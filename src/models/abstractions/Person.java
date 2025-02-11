package models.abstractions;

import java.util.Date;

public abstract class Person {
    private int id;
    private String name;
    private Date birthDate;
    private boolean gender;

    public Person() {

    }

    public Person(String name, Date birthDate, boolean gender) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public Person(int id, String name, Date birthDate, boolean gender) {
        this(name, birthDate, gender);
        this.id = id;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Date getBirthDate() { return birthDate; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }
    public boolean getGender() { return gender; }
    public void setGender(boolean gender) { this.gender = gender; }

    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate.toString() + '\'' +
                ", gender='" + (gender ? "Female" : "Male") + '\'';
    }
}
