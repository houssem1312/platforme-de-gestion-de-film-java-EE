package servlets;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.Factory;
import DAO.FilmDao;

@WebServlet("/DeleteFilm")
public class SupprimerFilmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int filmId = Integer.parseInt(request.getParameter("filmId"));

        Factory daoFactory = Factory.getInstance("jdbc:mysql://localhost:3306/film_database", "root", "Password!123");
        FilmDao filmDao = daoFactory.getFilmDao();

        try {
            filmDao.supprimer(filmId);
            response.sendRedirect("supsuccess.html");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("superror.html");
        }
    }
}

