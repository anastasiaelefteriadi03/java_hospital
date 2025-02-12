package controllers;

import controllers.interfaces.IPatientController;
import models.Patient;
import repositories.iterfaces.IPatientRepository;

import java.util.Date;
import java.util.List;

public class PatientController implements IPatientController {
    private final IPatientRepository repo;

    public PatientController(IPatientRepository repo) {
        this.repo = repo;
    }

    public String create(String name, Date birthDate, boolean gender, String condition, int doctorId) {
        Patient patient = new Patient(name, birthDate, gender, condition, doctorId);

        boolean created = repo.create(patient);

        return (created ? "Patient was created!" : "Patient creation was failed!");
    }

    public String getAll() {
        List<Patient> patients = repo.getAll();

        StringBuilder response = new StringBuilder();
        for (Patient patient : patients) {
            response.append(patient.toString()).append("\n");
        }

        return response.toString();
    }

    public String getAllWithAgeOver(int age) {
        List<Patient> patients = repo.getAllWithAgeOver(age);

        StringBuilder response = new StringBuilder();
        for (Patient patient : patients) {
            response.append(patient.toString()).append("\n");
        }

        return response.toString();
    }

    public String updateConditionById(int id, String condition) {
        boolean created = repo.updateConditionById(id, condition);

        return (created ? "Patient's condition was updated!" : "Patient's condition update was failed!");
    }

    public String deleteById(int id) {
        boolean created = repo.deleteById(id);

        return (created ? "Patient was deleted!" : "Patient deletion was failed!");
    }
}
