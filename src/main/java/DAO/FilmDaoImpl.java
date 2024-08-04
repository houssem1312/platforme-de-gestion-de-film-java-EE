package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Film;

public class FilmDaoImpl implements FilmDao {
    private Factory daoFactory;

    public FilmDaoImpl(Factory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void ajouter(Film film) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO movies (titre, description, duree, annee_sortie, pays_production, acteurs_principaux, image, genre, note) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, film.getTitre());
            preparedStatement.setString(2, film.getDescription());
            preparedStatement.setInt(3, film.getDuree());
            preparedStatement.setInt(4, film.getAnneeSortie());
            preparedStatement.setString(5, film.getPaysProduction());
            preparedStatement.setString(6, film.getActeursPrincipaux());
            preparedStatement.setString(7, film.getImage()); // Use getImage() instead of getImages()
            preparedStatement.setString(8, film.getGenre());
            preparedStatement.setDouble(9, film.getNote());
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }

    @Override
    public void modifier(Film film) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE movies SET titre=?, description=?, duree=?, annee_sortie=?, pays_production=?, acteurs_principaux=?, genre=?, note=? WHERE id=?");
            preparedStatement.setString(1, film.getTitre());
            preparedStatement.setString(2, film.getDescription());
            preparedStatement.setInt(3, film.getDuree());
            preparedStatement.setInt(4, film.getAnneeSortie());
            preparedStatement.setString(5, film.getPaysProduction());
            preparedStatement.setString(6, film.getActeursPrincipaux());
            // Use getImage() instead of getImages()
            preparedStatement.setString(7, film.getGenre());
            preparedStatement.setDouble(8, film.getNote());
            preparedStatement.setInt(9, film.getId());
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM movies WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }

    @Override
    public Film getFilmById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Film> lister() throws SQLException {
        List<Film> films = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            statement = connection.prepareStatement("SELECT * FROM movies");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Film film = new Film();
                film.setId(resultSet.getInt("id"));
                film.setTitre(resultSet.getString("titre"));
                film.setDescription(resultSet.getString("description"));
                film.setDuree(resultSet.getInt("duree"));
                film.setAnneeSortie(resultSet.getInt("annee_sortie"));
                film.setPaysProduction(resultSet.getString("pays_production"));
                film.setActeursPrincipaux(resultSet.getString("acteurs_principaux"));
                film.setGenre(resultSet.getString("genre"));
                film.setNote(resultSet.getDouble("note"));

                films.add(film);
            }
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return films;
    }
}
