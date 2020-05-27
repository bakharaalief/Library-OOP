package daomodel;

import DBConnection.DBConnection;
import dao.Dao;
import model.Income;
import model.UserBook;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RentIncomeDaoModel implements Dao<Income> {
    @Override
    public ArrayList<Income> getAll() {
        String sql = "SELECT * FROM rent_income";
        ArrayList<Income> listIncome = new ArrayList<>();
        ResultSet rs;

        try {
            Statement stmt = DBConnection.getConnection().createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                //mengambil data dari server
                int idIncome = rs.getInt("id_income");
                String firstName = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                double income = rs.getDouble("income");
                double discount = rs.getDouble("discount");
                double finalIncome = rs.getDouble("final_income");
                Date created = rs.getDate("created");


                Income data = new Income(idIncome, firstName, lastname, income, discount, finalIncome, created);
                listIncome.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listIncome;
    }

    @Override
    public void create(Income income) {
        String sql = "INSERT INTO rent_income (firstname, lastname, income, discount, final_income ) VALUES ( ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);

            pstmt.setString(1, income.getFirstname());
            pstmt.setString(2, income.getLastname());
            pstmt.setDouble(3, income.getIncome());
            pstmt.setDouble(4, income.getDiscount());
            pstmt.setDouble(5, income.getFinal_income());
            pstmt.executeUpdate();

            //notif user berhasil dibuat
            System.out.println("Note : Rent income berhasil di import");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Income income) {
    }

    @Override
    public void delete(Income income) {
    }
}
