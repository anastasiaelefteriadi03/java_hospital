import controllers.interfaces.IDoctorController;
import controllers.interfaces.IHospitalController;
import controllers.interfaces.IPatientController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class App {
    private final Scanner scanner = new Scanner(System.in).useDelimiter("\n");;

    private final IHospitalController hospitalController;
    private final IDoctorController doctorController;
    private final IPatientController patientController;


    public App(IHospitalController hospitalController, IDoctorController doctorController, IPatientController patientController) {
        this.hospitalController = hospitalController;
        this.doctorController = doctorController;
        this.patientController = patientController;
    }

    private void mainMenu() {
        System.out.println();
        System.out.println("Welcome to My Application");
        System.out.println("Select option:");
        System.out.println("1. create hospital");
        System.out.println("2. view hospitals");
        System.out.println("3. create doctor");
        System.out.println("4. view doctors (sorted by years of experience)");
        System.out.println("5. view patients of a doctor");
        System.out.println("6. create patient");
        System.out.println("7. view patients (+ display doctor)");
        System.out.println("8. view patients over *input* years");
        System.out.println("9. update condition of a patient by ID");
        System.out.println("10. delete patient by ID");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Enter option (1-11): ");
    }

    public void start() {
        while (true) {
            mainMenu();
            try {
                int option = scanner.nextInt();

                switch (option) {
                    case 1: createHospitalMenu(); break;
                    case 2: getAllHospitalsMenu(); break;
                    case 3: createDoctorMenu(); break;
                    case 4: getAllDoctorsMenu(); break;
                    case 5: getPatientsByDoctorId(); break;
                    case 6: createPatientMenu(); break;
                    case 7: getAllPatientsMenu(); break;
                    case 8: getAllPatientsWithAgeOver(); break;
                    case 9: updatePatientConditionById(); break;
                    case 10: deletePatientById(); break;
                    default: return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer: " + e);
                scanner.nextLine(); // to ignore incorrect input
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("*************************");
        }
    }

    public void createHospitalMenu() {
        System.out.println("Please enter name");
        String name = scanner.next();
        System.out.println("Please enter location");
        String location = scanner.next();

        String response = hospitalController.create(name, location);
        System.out.println(response);
    }

    public void getAllHospitalsMenu() {
        String response = hospitalController.getAll();
        System.out.println(response);
    }

    public void createDoctorMenu() {
        System.out.println("Please enter name");
        String name = scanner.next();
        System.out.println("Please enter birth date (dd-mm-yyyy)");
        String rawDate = scanner.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localdate = LocalDate.parse(rawDate, formatter);
        Date birthdate = Date.from(localdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println("Please enter gender (0 - male, 1 - female)");
        boolean gender = scanner.next().equals("1");
        System.out.println("Please enter speciality");
        String speciality = scanner.next();
        System.out.println("Please enter years of experience");
        Integer yearsOfExperience = Integer.parseInt(scanner.next());
        System.out.println("Please enter hospital id");
        Integer hospitalId = Integer.parseInt(scanner.next());

        String response = doctorController.create(name, birthdate, gender, speciality, yearsOfExperience, hospitalId);
        System.out.println(response);
    }

    public void getAllDoctorsMenu() {
        String response = doctorController.getAll();
        System.out.println(response);
    }

    public void getPatientsByDoctorId() {
        System.out.println("Please enter doctor id");
        Integer id = Integer.parseInt(scanner.next());
        String response = doctorController.getPatientsByDoctorId(id);
        System.out.println(response);
    }

    public void createPatientMenu() {

    }

    public void getAllPatientsMenu() {

    }

    public void getAllPatientsWithAgeOver() {

    }

    public void updatePatientConditionById() {

    }

    public void deletePatientById() {

    }

}
