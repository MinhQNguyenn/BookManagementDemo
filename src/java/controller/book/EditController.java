/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.book;

import dal.AuthorDBContext;
import dal.BookDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import model.Author;
import model.Book;

/**
 *
 * @author sonnt
 */
public class EditController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] bids = req.getParameterValues("bid");
        ArrayList<Book> books = new ArrayList<>();
        AuthorDBContext authorDB = new AuthorDBContext();
        ArrayList<Author> authors = authorDB.getAuthors();
        
        for (String bid : bids) {
            int id = Integer.parseInt(bid);
            Book b = new Book();
            b.setId(id);
            b.setTitle(req.getParameter("title"+id));
            b.setPublisheddate(Date.valueOf(req.getParameter("date"+id)));
            for (Author author : authors) {
                String str_aid = req.getParameter("aid_"+author.getId()+"_"+id);
                if(str_aid!=null)
                {
                    b.getAuthors().add(author);
                }
            }
            books.add(b);
        }
       BookDBContext db = new BookDBContext();
       db.update(books);
       resp.sendRedirect("edit");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDBContext bookDB = new BookDBContext();
        AuthorDBContext authorDB = new AuthorDBContext();
        ArrayList<Book> books = bookDB.getBooks();
        ArrayList<Author> authors = authorDB.getAuthors();
        req.setAttribute("books", books);
        req.setAttribute("authors", authors);
        req.getRequestDispatcher("../view/book/edit.jsp").forward(req, resp);
    }
    
}
