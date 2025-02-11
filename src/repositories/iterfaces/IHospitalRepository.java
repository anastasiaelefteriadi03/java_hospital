package repositories.iterfaces;

import models.Hospital;

import java.util.List;

public interface IHospitalRepository {
    boolean create(Hospital hospital);
    List<Hospital> getAll();
}
