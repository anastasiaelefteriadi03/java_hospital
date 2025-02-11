import controllers.DoctorController;
import controllers.HospitalController;
import controllers.PatientController;
import controllers.interfaces.IDoctorController;
import controllers.interfaces.IHospitalController;
import controllers.interfaces.IPatientController;
import data.PostgresDB;
import data.interfaces.IDB;
import repositories.DoctorRepository;
import repositories.HospitalRepository;
import repositories.PatientRepository;
import repositories.iterfaces.IDoctorRepository;
import repositories.iterfaces.IHospitalRepository;
import repositories.iterfaces.IPatientRepository;

public class Main {
    public static void main(String[] args) {
        // Here you specify which DB and UserRepository to use
        // And changing DB should not affect to whole code
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "hEJgnlK97mT6", "nastya");

        IHospitalRepository hospitalRepo = new HospitalRepository(db);
        IDoctorRepository doctorRepo = new DoctorRepository(db);
        IPatientRepository patientRepo = new PatientRepository(db);

        IHospitalController hospitalController = new HospitalController(hospitalRepo);
        IDoctorController doctorController = new DoctorController(doctorRepo);
        IPatientController patientController = new PatientController(patientRepo);


        App app = new App(hospitalController, doctorController, patientController);

        app.start();

        db.close();
    }
}
