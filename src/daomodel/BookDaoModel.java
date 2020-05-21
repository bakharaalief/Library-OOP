package daomodel;

import DBConnection.DBConnection;
import dao.Dao;
import model.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BookDaoModel implements Dao<Book> {
    @Override
    public ArrayList<Book> getAll() {
        String sql = "SELECT * FROM book_stock";
        ArrayList<Book> listBook = new ArrayList<>();
        ResultSet rs;

        try {
            Statement stmt = DBConnection.getConnection().createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                //mengambil data dari server
                int idBook = rs.getInt("id_book");
                String title = rs.getString("title");
                String synopsis = rs.getString("synopsis");
                String genre = rs.getString("genre");
                String writer = rs.getString("writer");
                int published = rs.getInt("published");
                double price = rs.getDouble("price");
                int amount = rs.getInt("amount");

                Book data = new Book(idBook, title, synopsis, genre, writer, published, price, amount);
                listBook.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listBook;
    }

    @Override
    public void create(Book book) {
        String sql = "INSERT INTO book_stock (title, synopsis, genre, writer, published, price, amount) VALUES ( ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getSynopsis());
            pstmt.setString(3, book.getGenre());
            pstmt.setString(4, book.getWriter());
            pstmt.setInt(5, book.getPublished());
            pstmt.setDouble( 6, book.getPrice());
            pstmt.setInt(7, book.getAmount());
            pstmt.executeUpdate();

            //notif user berhasil dibuat
            System.out.println("buku berhasil di import");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Book book) {
        String sql = "UPDATE book_stock SET title=?, synopsis=?, genre=?, writer=?, published=?, price=? WHERE id_book=?";

        try {
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getSynopsis());
            pstmt.setString(3, book.getGenre());
            pstmt.setString(4, book.getWriter());
            pstmt.setInt(5, book.getPublished());
            pstmt.setDouble(6, book.getPrice());
            pstmt.setInt(7, book.getId());
            pstmt.executeUpdate();

            //notif user berhasil diupdate
            System.out.println("Perubahaan berhasil dilakukan");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStock(Book book){
        String sql = "UPDATE book_stock SET amount=? WHERE id_book=?";

        try {
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);

            pstmt.setInt(1, book.getAmount());
            pstmt.setInt(2, book.getId());
            pstmt.executeUpdate();

            //notif user berhasil diupdate
            System.out.println("Perubahaan stock berhasil dilakukan");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Book book) {
        String sql = "DELETE FROM book_stock WHERE id_book=?";

        try {
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);

            pstmt.setInt(1, book.getId());
            pstmt.executeUpdate();

            //notif user berhasil dihapus
            System.out.println("buku berhasil dihapus");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
