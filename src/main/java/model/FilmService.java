package model;


import java.sql.SQLException;
import java.util.List;
import DAO.Factory;
import DAO.FilmDao;

public class FilmService {
    private FilmDao filmDao;

    public FilmService() {
        Factory daoFactory = Factory.getInstance("jdbc:mysql://localhost:3306/film_database", "root", "Password!123");
        filmDao = daoFactory.getFilmDao();
    }

    public List<Film> listerFilms() throws SQLException {
        return filmDao.lister();
    }

	public void ajouterFilm(Film film) {
		// TODO Auto-generated method stub
		
	}
}
