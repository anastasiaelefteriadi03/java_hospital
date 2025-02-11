package controllers.interfaces;

import java.util.Date;

public interface IDoctorController {
    String create(String name, Date birthDate, boolean gender, String speciality, int yearsOfExperience, int hospitalId);
    String getAll();
    String getPatientsByDoctorId(int id);
}
