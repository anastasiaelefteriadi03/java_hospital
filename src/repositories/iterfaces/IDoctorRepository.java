package repositories.iterfaces;

import models.Doctor;
import models.Patient;

import java.util.List;

public interface IDoctorRepository {
    boolean create(Doctor doctor);
    List<Doctor> getAll();
    List<Patient> getPatientsByDoctorId(int id);
}
