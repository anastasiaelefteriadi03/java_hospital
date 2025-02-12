package models;

import models.abstractions.Person;

import java.util.Date;

public class Patient extends Person {
    private String condition;
    private int doctorId;
    private String doctorName;

    public Patient() {

    }

    public Patient(String name, Date birthDate, boolean gender, String condition, int doctorId) {
        super(name, birthDate, gender);
        this.condition = condition;
        this.doctorId = doctorId;
    }

    public Patient(int id, String name, Date birthDate, boolean gender, String condition, int doctorId, String doctorName) {
        super(id, name, birthDate, gender);
        this.condition = condition;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
    }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }
    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }
    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }

    public String toString() {
        return "Patient{" +
                super.toString() +
                ", condition='" + condition + '\'' +
                (doctorName != null ? ", doctorName='" + doctorName + '\'' : ", doctorId=" + Integer.toString(doctorId)) +
                '}';
    }
}
