package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.Factory;
import DAO.FilmDao;
import model.Film;

@WebServlet("/AccueilServlet")
public class AccueilServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get the DAO factory instance
            Factory daoFactory = Factory.getInstance("jdbc:mysql://localhost:3306/film_database", "root", "Password!123");

            // Get the FilmDao instance from the factory
            FilmDao filmDao = daoFactory.getFilmDao();

            // Retrieve list of films from the database
            List<Film> films = filmDao.lister();

            // Set the films attribute in the request
            request.setAttribute("films", films);

            // Forward the request to the JSP page for display
            request.getRequestDispatcher("accueil_utilisateur.jsp").forward(request, response);
        } catch (SQLException e) {
            // Handle database errors
            e.printStackTrace();
            // Redirect to an error page
            response.sendRedirect("erreur.jsp");
        }
    }
}
