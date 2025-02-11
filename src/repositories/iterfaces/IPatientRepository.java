package repositories.iterfaces;

import models.Patient;

import java.util.List;

public interface IPatientRepository {
    boolean create(Patient patient);
    List<Patient> getAll();
    List<Patient> getAllWithAgeOver(int age);
    boolean updateConditionById(int id, String condition);
    boolean deleteById(int id);
}
