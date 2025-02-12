package controllers.interfaces;

import models.Patient;

import java.util.Date;

public interface IPatientController {
    String create(String name, Date birthDate, boolean gender, String condition, int doctorId);
    String getAll();
    String getAllWithAgeOver(int age);
    String updateConditionById(int id, String condition);
    String deleteById(int id);
}
