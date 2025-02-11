package controllers;

import controllers.interfaces.IDoctorController;
import models.Doctor;
import models.Patient;
import repositories.iterfaces.IDoctorRepository;

import java.util.Date;
import java.util.List;

public class DoctorController implements IDoctorController {
    private final IDoctorRepository repo;

    public DoctorController(IDoctorRepository repo) {
        this.repo = repo;
    }

    public String create(String name, Date birthDate, boolean gender, String speciality, int yearsOfExperience, int hospitalId) {
        Doctor doctor = new Doctor(name, birthDate, gender, speciality, yearsOfExperience, hospitalId);

        boolean created = repo.create(doctor);

        return (created ? "Doctor was created!" : "Doctor creation was failed!");
    }

    public String getAll() {
        List<Doctor> doctors = repo.getAll();

        StringBuilder response = new StringBuilder();
        for (Doctor doctor : doctors) {
            response.append(doctor.toString()).append("\n");
        }

        return response.toString();
    }

    public String getPatientsByDoctorId(int id) {
        List<Patient> patients = repo.getPatientsByDoctorId(id);

        StringBuilder response = new StringBuilder();
        for (Patient patient : patients) {
            response.append(patient.toString()).append("\n");
        }

        return response.toString();
    }
}
