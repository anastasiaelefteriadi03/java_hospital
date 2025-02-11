package models;

import models.abstractions.Person;

import java.util.Date;

public class Doctor extends Person {
    private String speciality;
    private int yearsOfExperience;
    private int hospitalId;

    public Doctor() {

    }

    public Doctor(String name, Date birthDate, boolean gender, String speciality, int yearsOfExperience, int hospitalId) {
        super(name, birthDate, gender);
        this.speciality = speciality;
        this.yearsOfExperience = yearsOfExperience;
        this.hospitalId = hospitalId;
    }
    
    public Doctor(int id, String name, Date birthDate, boolean gender, String speciality, int yearsOfExperience, int hospitalId) {
        super(id, name, birthDate, gender);
        this.speciality = speciality;
        this.yearsOfExperience = yearsOfExperience;
        this.hospitalId = hospitalId;
    }

    public String getSpeciality() { return speciality; }
    public void setSpeciality(String speciality) { this.speciality = speciality; }
    public int getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(int yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }
    public int getHospitalId() { return hospitalId; }
    public void setHospitalId(int hospitalId) { this.hospitalId = hospitalId; }

    public String toString() {
        return "Doctor{" +
                super.toString() +
                ", speciality='" + speciality + '\'' +
                ", yearsOfExperience=" + Integer.toString(yearsOfExperience) +
                ", hospitalId=" + Integer.toString(hospitalId) +
                '}';
    }
}
