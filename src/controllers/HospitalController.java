package controllers;

import controllers.interfaces.IHospitalController;
import models.Hospital;
import repositories.iterfaces.IHospitalRepository;

import java.util.List;

public class HospitalController implements IHospitalController {
    private final IHospitalRepository repo;

    public HospitalController(IHospitalRepository repo) {
        this.repo = repo;
    }

    public String create(String name, String location) {
        Hospital hospital = new Hospital(name, location);

        boolean created = repo.create(hospital);

        return (created ? "Hospital was created!" : "Hospital creation was failed!");
    }

    public String getAll() {
        List<Hospital> hospitals = repo.getAll();

        StringBuilder response = new StringBuilder();
        for (Hospital hospital : hospitals) {
            response.append(hospital.toString()).append("\n");
        }

        return response.toString();
    }
}
// MVC - Model / View / Controller