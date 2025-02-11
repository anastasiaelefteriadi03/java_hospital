package repositories;

import data.interfaces.IDB;
import models.Doctor;
import models.Patient;
import repositories.iterfaces.IDoctorRepository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepository implements IDoctorRepository {
    private final IDB db;

    public DoctorRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean create(Doctor doctor) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "INSERT INTO doctors(name,birthdate,gender,speciality,years_of_experience,hospital_id) VALUES (?,to_date(?, 'DD-MM-YYYY'),?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, doctor.getName());
            st.setString(2, new SimpleDateFormat("dd-MM-yyyy").format(doctor.getBirthDate()));
            st.setBoolean(3, doctor.getGender());
            st.setString(4, doctor.getSpeciality());
            st.setInt(5, doctor.getYearsOfExperience());
            st.setInt(6, doctor.getHospitalId());

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<Doctor> getAll() {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT * FROM doctors ORDER BY years_of_experience DESC";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Doctor> doctors = new ArrayList<>();
            while (rs.next()) {
                Doctor doctor = new Doctor(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("birthdate"),
                        rs.getBoolean("gender"),
                        rs.getString("speciality"),
                        rs.getInt("years_of_experience"),
                        rs.getInt("hospital_id"));

                doctors.add(doctor);
            }

            return doctors;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Patient> getPatientsByDoctorId(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT patients.id as id, patients.name as name, patients.birthdate as birthdate, patients.gender as gender, patients.condition as condition, patients.doctor_id as doctor_id, doctors.name as doctor FROM patients left join doctors on patients.doctor_id = doctors.id WHERE doctor_id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            List<Patient> patients = new ArrayList<>();
            while (rs.next()) {
                Patient patient = new Patient(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("birthdate"),
                        rs.getBoolean("gender"),
                        rs.getString("condition"),
                        rs.getInt("doctor_id"),
                        rs.getString("doctor"));

                patients.add(patient);
            }

            return patients;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }
}
