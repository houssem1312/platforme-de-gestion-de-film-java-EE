<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Accueil</title>
    <style>
        /* Your CSS styles */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .film-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            padding-top: 20px;
        }
        .film {
            margin: 20px;
            text-align: center;
        }
        .film img {
            width: 200px;
            height: auto;
            cursor: pointer;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease;
        }
        .film img:hover {
            transform: scale(1.05);
        }
        .film-title {
            font-weight: bold;
            margin-top: 10px;
        }
        .search-bar {
            text-align: center;
            padding: 20px;
        }
        .user-info {
            text-align: right;
            padding: 10px;
        }
    </style>
</head>
<body>
    <div class="user-info">
        Bienvenue, <span id="username"><c:out value="${sessionScope.username}" /></span> | 
        <a href="logout.html">Déconnexion</a>
    </div>

    <div class="search-bar">
        <input type="text" id="search" placeholder="Rechercher par titre">
        <button onclick="searchFilms()">Rechercher</button>
    </div>

    <div class="film-container">
        <!-- Use JSP tags to display films -->
        <c:forEach var="film" items="${films}">
            <div class="film" onclick="redirectToFilmPage(<c:out value="${film.id}" />)">
                <img src="<c:out value="${film.image}" />" alt="<c:out value="${film.titre}" />">
                <p class="film-title"><c:out value="${film.titre}" /></p>
                <p>Description: <c:out value="${film.description}" /></p>
                <p>Durée: <c:out value="${film.duree}" /> minutes</p>
                <p>Année de sortie: <c:out value="${film.anneeSortie}" /></p>
                <p>Pays de production: <c:out value="${film.paysProduction}" /></p>
                <p>Acteurs principaux: <c:out value="${film.acteursPrincipaux}" /></p>
                <p>Genre: <c:out value="${film.genre}" /></p>
                <p>Note: <c:out value="${film.note}" /></p>
            </div>
        </c:forEach>
    </div>

    <script>
        // Function to redirect to the film details page
        function redirectToFilmPage(filmId) {
            // Check if the user is logged in
            var loggedIn = true; // Modify based on your authentication logic

            if (loggedIn) {
                // Redirect to the film details page
                window.location.href = "film_details.jsp?id=" + filmId;
            } else {
                // Redirect to the login page
                window.location.href = "login.html";
            }
        }

        // Function to search for films
        function searchFilms() {
            var searchInput = document.getElementById("search").value;
            // Implement film search logic here
            console.log("Searching for films with title: " + searchInput);
        }
    </script>
</body>
</html>
