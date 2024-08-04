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
import model.Film;

@WebServlet("/ModifierFilmServlet")
public class ModifierFilmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int filmId = Integer.parseInt(request.getParameter("filmId"));
        String titre = request.getParameter("titre");
        String description = request.getParameter("description");
        int duree = Integer.parseInt(request.getParameter("duree"));
        int anneeSortie = Integer.parseInt(request.getParameter("anneeSortie"));
        String paysProduction = request.getParameter("paysProduction");
        String acteursPrincipaux = request.getParameter("acteursPrincipaux");
        String genre = request.getParameter("genre");
        double note = Double.parseDouble(request.getParameter("note"));

        Film film = new Film();
        film.setId(filmId);
        film.setTitre(titre);
        film.setDescription(description);
        film.setDuree(duree);
        film.setAnneeSortie(anneeSortie);
        film.setPaysProduction(paysProduction);
        film.setActeursPrincipaux(acteursPrincipaux);
        film.setGenre(genre);
        film.setNote(note);

        Factory daoFactory = Factory.getInstance("jdbc:mysql://localhost:3306/film_database", "root", "Password!123");
        FilmDao filmDao = daoFactory.getFilmDao();

        try {
            filmDao.modifier(film);
            response.sendRedirect("Msuccess.html");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("Merror.html");
        }
    }
}
