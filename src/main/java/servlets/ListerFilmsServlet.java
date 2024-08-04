package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.Factory;
import DAO.FilmDao;
import model.Film;

@WebServlet("/ListFilms")
public class ListerFilmsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ListerFilmsServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Factory daoFactory = Factory.getInstance("jdbc:mysql://localhost:3306/film_database", "root", "Password!123");
            FilmDao filmDao = daoFactory.getFilmDao();

            List<Film> films = filmDao.lister();
            request.setAttribute("films", films);
            request.getRequestDispatcher("viewfilm.jsp").forward(request, response);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "An SQL exception occurred", e);
            request.setAttribute("errorMessage", "Une erreur est survenue lors de la récupération des films.");
            request.getRequestDispatcher("viewfilm.jsp").forward(request, response);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "An unexpected exception occurred", ex);
            request.setAttribute("errorMessage", "Une erreur inattendue est survenue.");
            request.getRequestDispatcher("viewfilm.jsp").forward(request, response);
        }
    }
}
