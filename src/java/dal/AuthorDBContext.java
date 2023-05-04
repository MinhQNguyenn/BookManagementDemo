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
public class AuthorDBContext extends DBContext {
    public ArrayList<Author> getAuthors() {
        ArrayList<Author> authors = new ArrayList<>();
        try {
            String sql = "SELECT aid,aname FROM Author";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Author a = new Author();
                a.setId(rs.getInt("aid"));
                a.setName(rs.getString("aname"));
                authors.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AuthorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return authors;
    }
}
