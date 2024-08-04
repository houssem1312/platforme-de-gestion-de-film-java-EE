package DAO;

import java.sql.SQLException;
import java.util.List;
import model.Utilisateur;

public interface UtilisateurDao {
    void ajouter(Utilisateur utilisateur) throws SQLException;
    Utilisateur getUtilisateurByEmailAndPassword(String email, String password) throws SQLException;
    List<Utilisateur> lister() throws SQLException;
}
