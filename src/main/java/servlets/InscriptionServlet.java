package servlets;
import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.Factory;
import DAO.UtilisateurDao;
import model.Utilisateur;

@WebServlet("/InscriptionsServlet")
public class InscriptionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");
        String birthdate = request.getParameter("birthdate");
        String username = request.getParameter("username");

        if (!password.equals(confirmPassword)) {
            response.sendRedirect("inscription.html?error=password");
            return;
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail(email);
        utilisateur.setPassword(password);
        utilisateur.setBirthdate(birthdate);
        utilisateur.setUsername(username);

        Factory daoFactory = Factory.getInstance("jdbc:mysql://localhost:3306/film_database", "root", "Password!123");
        UtilisateurDao utilisateurDao = daoFactory.getUtilisateurDao();

        try {
            utilisateurDao.ajouter(utilisateur);
            response.sendRedirect("welcome.html");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("inscription.html?error=database");
        }
    }
}
