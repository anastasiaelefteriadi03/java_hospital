package repositories;

import data.interfaces.IDB;
import models.Hospital;
import repositories.iterfaces.IHospitalRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalRepository implements IHospitalRepository {
    private final IDB db;

    public HospitalRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean create(Hospital hospital) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "INSERT INTO hospitals(name,location) VALUES (?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, hospital.getName());
            st.setString(2, hospital.getLocation());

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<Hospital> getAll() {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT * FROM hospitals";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Hospital> hospitals = new ArrayList<>();
            while (rs.next()) {
                Hospital hospital = new Hospital(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("location"));

                hospitals.add(hospital);
            }

            return hospitals;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }
}
