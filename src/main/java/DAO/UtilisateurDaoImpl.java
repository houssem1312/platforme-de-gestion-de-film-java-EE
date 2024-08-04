package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {
    private Factory daoFactory;

    public UtilisateurDaoImpl(Factory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void ajouter(Utilisateur utilisateur) throws SQLException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO utilisateur (email, password, birthdate, username) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, utilisateur.getEmail());
            preparedStatement.setString(2, utilisateur.getPassword());
            preparedStatement.setString(3, utilisateur.getBirthdate());
            preparedStatement.setString(4, utilisateur.getUsername());

            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connexion != null) connexion.close();
        }
    }

    @Override
    public Utilisateur getUtilisateurByEmailAndPassword(String email, String password) throws SQLException {
        Utilisateur utilisateur = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT * FROM utilisateur WHERE email = ? AND password = ?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                utilisateur = new Utilisateur();
                utilisateur.setId(resultSet.getInt("id"));
                utilisateur.setEmail(resultSet.getString("email"));
                utilisateur.setPassword(resultSet.getString("password"));
                utilisateur.setBirthdate(resultSet.getString("birthdate"));
                utilisateur.setUsername(resultSet.getString("username"));
                utilisateur.setRole(resultSet.getString("role"));
            }
        } finally {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connexion != null) connexion.close();
        }

        return utilisateur;
    }

    @Override
    public List<Utilisateur> lister() throws SQLException {
        // Implémentez cette méthode si nécessaire
        return new ArrayList<>();
    }
}
