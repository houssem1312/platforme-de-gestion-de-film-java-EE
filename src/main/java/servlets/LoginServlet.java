package servlets;

import java.io.IOException;

import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Utilisateur;
import DAO.Factory;
import DAO.UtilisateurDao;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Factory daoFactory = Factory.getInstance("jdbc:mysql://localhost:3306/film_database", "root", "Password!123");
        UtilisateurDao utilisateurDao = daoFactory.getUtilisateurDao();

        try {
            Utilisateur utilisateur = utilisateurDao.getUtilisateurByEmailAndPassword(email, password);
            if (utilisateur != null) {
                String role = utilisateur.getRole();
                if ("admin".equals(role)) {
                    System.out.println("Redirecting to admin dashboard...");
                    response.sendRedirect("acceuil_adminstrateur.html");
                } else {
                    System.out.println("Redirecting to user dashboard...");
                    response.sendRedirect("accueil_utlisateur.jsp");
                }
            } else {
                System.out.println("Invalid email or password...");
                response.sendRedirect("login.html?error=invalid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.html?error=database");
        }


}
}