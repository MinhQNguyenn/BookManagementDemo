/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Author;
import model.Book;

/**
 *
 * @author sonnt
 */
public class BookDBContext extends DBContext {

    public ArrayList<Book> getBooks() {
        ArrayList<Book> books = new ArrayList<>();
        try {
            String sql = "SELECT b.bid,b.btitle,b.publisheddate,a.aid,a.aname FROM Book b LEFT JOIN Book_Author ba ON b.bid = ba.bid\n"
                    + "					LEFT JOIN Author a ON a.aid = ba.aid";
            PreparedStatement stm = connection.prepareStatement(sql); 
            ResultSet rs = stm.executeQuery();
            Book currentBook = new Book();
            currentBook.setId(-1);
            while (rs.next()) {
                int bid = rs.getInt("bid");
                if (bid != currentBook.getId()) {
                    currentBook = new Book();
                    currentBook.setId(bid);
                    currentBook.setTitle(rs.getString("btitle"));
                    currentBook.setPublisheddate(rs.getDate("publisheddate"));
                    books.add(currentBook);
                }
                int aid = rs.getInt("aid");
                if (aid != 0) {
                    Author a = new Author();
                    a.setId(aid);
                    a.setName(rs.getString("aname"));
                    currentBook.getAuthors().add(a);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return books;
    }

    public void update(ArrayList<Book> books) {
        try {
            for (Book book : books) {
                String sql_delete_bookauthor = "DELETE FROM Book_Author WHERE bid = ?";
                PreparedStatement stm_remove_authors = connection.prepareStatement(sql_delete_bookauthor);
                stm_remove_authors.setInt(1, book.getId());
                stm_remove_authors.executeUpdate();
                for (Author author : book.getAuthors()) {
                    String sql_insert_authors = "INSERT INTO Book_Author(bid,aid) VALUES(?,?)";
                    PreparedStatement stm_insert = connection.prepareStatement(sql_insert_authors);
                    stm_insert.setInt(1, book.getId());
                    stm_insert.setInt(2, author.getId());
                    stm_insert.executeUpdate();
                }
                String sql_update_book = "UPDATE [Book]\n"
                        + "   SET \n"
                        + "      [btitle] = ?\n"
                        + "      ,[publisheddate] = ?\n"
                        + " WHERE [bid] = ?";
                PreparedStatement stm_update = connection.prepareStatement(sql_update_book);
                stm_update.setString(1, book.getTitle());
                stm_update.setDate(2, book.getPublisheddate());
                stm_update.setInt(3, book.getId());
                stm_update.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
