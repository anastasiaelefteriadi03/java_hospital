package controllers;

import controllers.interfaces.IPatientController;
import repositories.iterfaces.IPatientRepository;

public class PatientController implements IPatientController {
    private final IPatientRepository repo;

    public PatientController(IPatientRepository repo) {
        this.repo = repo;
    }
}
