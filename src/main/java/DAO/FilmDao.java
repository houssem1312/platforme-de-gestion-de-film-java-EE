package DAO;

import java.sql.SQLException;
import java.util.List;
import model.Film;

public interface FilmDao {
    void ajouter(Film film) throws SQLException;
    void modifier(Film film) throws SQLException;
    void supprimer(int id) throws SQLException;
    Film getFilmById(int id) throws SQLException;
    List<Film> lister() throws SQLException;
}
