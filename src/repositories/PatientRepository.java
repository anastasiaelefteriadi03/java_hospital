package repositories;

import data.interfaces.IDB;
import models.Patient;
import repositories.iterfaces.IPatientRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientRepository implements IPatientRepository {
    private final IDB db;

    public PatientRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean create(Patient patient) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "INSERT INTO patients(name,birthdate,gender,condition,doctor_id) VALUES (?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, patient.getName());
            st.setString(2, patient.getBirthDate().toString());
            st.setString(3, patient.getGender() ? "0" : "1");
            st.setString(4, patient.getCondition());
            st.setString(5, Integer.toString(patient.getDoctorId()));

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<Patient> getAll() {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT * FROM patients LEFT JOIN doctors ON patients.doctor_id = doctors.id";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Patient> patients = new ArrayList<>();
            while (rs.next()) {
                Patient patient = new Patient(rs.getInt("patients.id"),
                        rs.getString("patients.name"),
                        rs.getDate("patients.birthdate"),
                        rs.getBoolean("patients.gender"),
                        rs.getString("patients.condition"),
                        rs.getInt("patients.doctor_id"),
                        rs.getString("doctors.name"));

                patients.add(patient);
            }

            return patients;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean deleteById(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "DELETE FROM patients WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, Integer.toString(id));

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean updateConditionById(int id, String condition) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "UPDATE patients SET condition = ? WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, condition);
            st.setString(2, Integer.toString(id));

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<Patient> getAllWithAgeOver(int age) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT * from patients left join doctors on patients.doctor_id = doctors.id where DATE_PART('YEAR', AGE(CURRENT_DATE, birthdate)) > ?";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Patient> patients = new ArrayList<>();
            while (rs.next()) {
                Patient patient = new Patient(rs.getInt("patients.id"),
                        rs.getString("patients.name"),
                        rs.getDate("patients.birthdate"),
                        rs.getBoolean("patients.gender"),
                        rs.getString("patients.condition"),
                        rs.getInt("patients.doctor_id"),
                        rs.getString("doctors.name"));

                patients.add(patient);
            }

            return patients;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }
}
