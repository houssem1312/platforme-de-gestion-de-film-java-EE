package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Factory {
    private String url;
    private String username;
    private String password;

    private UtilisateurDao utilisateurDao;
    private FilmDao filmDao;


    private Factory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static Factory getInstance(String url, String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Factory(url, username, password);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public UtilisateurDao getUtilisateurDao() {
        if (utilisateurDao == null) {
            utilisateurDao = new UtilisateurDaoImpl(this);
        }
        return utilisateurDao;
    }
    
    public FilmDao getFilmDao() {
        if (filmDao == null) {
            filmDao = new FilmDaoImpl(this);
        }
        return filmDao;
    }
    
}
