package daomodel;

import DBConnection.DBConnection;
import dao.Dao;
import model.Book;
import model.UserBook;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UserBookDaoModel implements Dao<UserBook> {
    @Override
    public ArrayList<UserBook> getAll() {
        String sql = "SELECT * FROM book_rent";
        ArrayList<UserBook> listRent = new ArrayList<>();
        ResultSet rs;

        try {
            Statement stmt = DBConnection.getConnection().createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                //mengambil data dari server
                int idBookRent = rs.getInt("id_buku_pinjam");
                String firstName = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String title = rs.getString("title_book");
                int amount = rs.getInt("amount");
                Date rentStart = rs.getDate("rent_start");
                Date rentEnd = rs.getDate("rent_end");
                Date returnDate = rs.getDate("return_date");
                int returned = rs.getInt("returned");

                ///mengubah returned ke dalam boolean
                boolean returned_boolean = returned == 1 ? true : false;

                UserBook data = new UserBook(idBookRent, firstName, lastname, title, amount, rentStart, rentEnd, returnDate, returned_boolean);
                listRent.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listRent;
    }

    @Override
    public void create(UserBook userBook) {
        String sql = "INSERT INTO book_rent (firstname, lastname, title_book, amount, rent_start, rent_end, return_date, returned) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);

            int returned = userBook.isReturned() ? 1 : 0;

            pstmt.setString(1, userBook.getFirstName());
            pstmt.setString(2, userBook.getLastName());
            pstmt.setString(3, userBook.getTitle());
            pstmt.setInt(4, userBook.getAmount());
            pstmt.setDate(5, userBook.getRent_start());
            pstmt.setDate( 6, userBook.getRent_end());
            pstmt.setDate(7, userBook.getReturn_date());
            pstmt.setInt(8, returned);
            pstmt.executeUpdate();

            //notif user berhasil dibuat
            //System.out.println("Note : userBook berhasil di import");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UserBook userBook) { }

    @Override
    public void delete(UserBook userBook) {
//        String sql = "DELETE FROM book_rent WHERE id_user_pinjam=?";
//
//        try {
//            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
//
//            pstmt.setInt(1, userBook.getId());
//            pstmt.executeUpdate();
//
//            //notif user berhasil dihapus
//            System.out.println("buku berhasil dihapus");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void returnedBook(UserBook userBook){
        String sql = "UPDATE book_rent SET return_date=?, returned=? WHERE id_buku_pinjam=?";

        try {
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);

            pstmt.setDate(1, userBook.getReturn_date());
            pstmt.setInt(2, 1);
            pstmt.setInt(3,userBook.getId());
            pstmt.executeUpdate();

            //notif user berhasil diupdate
            //System.out.println("Note : Buku berhasil dikembalikan ");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
