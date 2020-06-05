package daomodel;

import DBConnection.DBConnection;
import dao.Dao;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoModel implements Dao<User> {
    //mengambil semua data User dari server
    @Override
    public ArrayList<User> getAll() {
        String sql = "SELECT * FROM user";
        ArrayList<User> listUser = new ArrayList<>();
        ResultSet rs;

        try {
            Statement stmt = DBConnection.getConnection().createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                //mengambil data dari server
                int idUser = rs.getInt("id_user");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                int age = rs.getInt("age");
                double balance = rs.getDouble("balance");
                Date created = rs.getDate("created");

                User data = new User(idUser, firstname, lastname, age, balance, (java.sql.Date) created);
                listUser.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listUser;
    }

    @Override
    public void create(User user) {
        String sql = "INSERT INTO user (firstname, lastname, age, balance) VALUES ( ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);

            pstmt.setString(1, user.getFirstname());
            pstmt.setString(2, user.getLastname());
            pstmt.setInt(3, user.getAge());
            pstmt.setDouble( 4, user.getBalance());
            pstmt.executeUpdate();

            //notif user berhasil dibuat
            System.out.println("Note : user berhasil dibuat");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE user SET firstname=?, lastname=?, age=? WHERE id_user=?";

        try {
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);

            pstmt.setString(1, user.getFirstname());
            pstmt.setString(2, user.getLastname());
            pstmt.setInt(3, user.getAge());
            pstmt.setInt(4, user.getId());
            pstmt.executeUpdate();

            //notif user berhasil diupdate
            //System.out.println("Note : Perubahaan berhasil dilakukan");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateBalance(User user){
        String sql = "UPDATE user SET balance=? WHERE id_user=?";

        try {
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);

            pstmt.setDouble(1, user.getBalance());
            pstmt.setInt(2, user.getId());
            pstmt.executeUpdate();

            //notif user berhasil diupdate
            //System.out.println("Note : Perubahaan saldo berhasil dilakukan");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        String sql = "DELETE FROM user WHERE id_user=?";

        try {
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);

            pstmt.setInt(1, user.getId());
            pstmt.executeUpdate();

            //notif user berhasil dihapus
            //System.out.println("Note : user berhasil dihapus");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
