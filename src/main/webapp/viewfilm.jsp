<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Films</title>
</head>
<body>
    <h1>Liste des Films</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Titre</th>
                <th>Description</th>
                <th>Durée</th>
                <th>Année de Sortie</th>
                <th>Pays de Production</th>
                <th>Acteurs Principaux</th>
                <th>Genre</th>
                <th>Note</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${films}" var="film">
                <tr>
                    <td>${film.id}</td>
                    <td>${film.titre}</td>
                    <td>${film.description}</td>
                    <td>${film.duree}</td>
                    <td>${film.anneeSortie}</td>
                    <td>${film.paysProduction}</td>
                    <td>${film.acteursPrincipaux}</td>
                    <td>${film.genre}</td>
                    <td>${film.note}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty films}">
                <tr>
                    <td colspan="9">Aucun film trouvé.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</body>
</html>
