package repositories;

import data.interfaces.IDB;
import models.Patient;
import repositories.iterfaces.IPatientRepository;

import java.sql.*;
import java.text.SimpleDateFormat;
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
            String sql = "INSERT INTO patients(name,birthdate,gender,condition,doctor_id) VALUES (?,TO_DATE(?, 'DD-MM-YYYY'),?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, patient.getName());
            st.setString(2, new SimpleDateFormat("dd-MM-yyyy").format(patient.getBirthDate()));
            st.setBoolean(3, patient.getGender());
            st.setString(4, patient.getCondition());
            st.setInt(5, patient.getDoctorId());

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
            String sql = "SELECT patients.id AS id, patients.name AS name, patients.birthdate AS birthdate, patients.gender AS gender, patients.condition AS condition, patients.doctor_id AS doctor_id, doctors.name AS doctor FROM patients LEFT JOIN doctors ON patients.doctor_id = doctors.id";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
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

    @Override
    public boolean deleteById(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "DELETE FROM patients WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

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
            st.setInt(2, id);

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
            String sql = "SELECT patients.id AS id, patients.name AS name, patients.birthdate AS birthdate, patients.gender AS gender, patients.condition AS condition, patients.doctor_id AS doctor_id, doctors.name AS doctor FROM patients LEFT JOIN doctors ON patients.doctor_id = doctors.id WHERE DATE_PART('YEAR', AGE(CURRENT_DATE, patients.birthdate)) > ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, age);

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
