<%@ page import="java.util.List" %>
<%@ page import="mediatek2021.Document" %><%--
  Created by IntelliJ IDEA.
  User: mr003
  Date: 09/03/2021
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Document> liste_cd = (List<Document>) request.getAttribute("liste_cd"); %>
<% List<Document> liste_dvd = (List<Document>) request.getAttribute("liste_dvd"); %>
<% int ID = 0; %>
<% int TYPE = 1; %>
<% int NOM = 2; %>
<% int AUTEUR = 3; %>
<% int GENRE_CD = 4; %>
<% int GENRE_DVD = 4; %>
<html>
<head>
    <%@ include file='parts/styles.jsp' %>
    <title>Mediatek documents</title>
</head>
<body>
<section class="section">
    <div class='rows'>
        <div class='row is-full'>
            <div class='columns is-centered'>
                <div class='column is-half is-full-mobile'>
                    <h1 class="title"><%= "Mediatek documents" %></h1>
                    <br/>
                    <% if(liste_cd.size() > 0) { %>
                        <h1 class="title is-4">Tous les CD</h1>
                    <% } else { %>
                        <h1 class="title is-4">Aucun CD :(</h1>
                    <% } %>
                    <form id="add-form" action="${pageContext.request.contextPath}/ajouter_cd" method="GET">
                        <div class="field is-grouped">
                            <div class="control">
                                <button type="submit" form="add-form" class="button is-primary">Ajouter CD</button>
                            </div>
                        </div>
                    </form>
                    <% for(Document cd:liste_cd) { %>
                        <div class="box">
                            <h2 class="title is-2"><%= cd.data()[NOM] %></h2>
                            <p>Compositeur : <%= cd.data()[AUTEUR] %></p>
                            <p>Genre : <%= cd.data()[GENRE_CD] %></p>
                            <br/>
                            <form
                                    id="delete-<%=cd.data()[ID]%>"
                                    action="${pageContext.request.contextPath}/documents?id=<%=cd.data()[ID]%>&action=delete"
                                    method="POST"
                            >
                                <button type="submit" form="delete-<%=cd.data()[ID]%>" class="button is-danger">Supprimer</button>
                            </form>
                        </div>
                    <% } %>
                    <% if(liste_dvd.size() > 0) { %>
                        <h1 class="title is-4">Tous les DVD</h1>
                    <% } else { %>
                        <h1 class="title is-4">Aucun DVD :(</h1>
                    <% } %>
                    <form id="add-form" action="${pageContext.request.contextPath}/ajouter_dvd" method="GET">
                        <div class="field is-grouped">
                            <div class="control">
                                <button type="submit" form="add-form" class="button is-primary">Ajouter DVD</button>
                            </div>
                        </div>
                    </form>
                    <% for(Document dvd:liste_dvd) { %>
                    <div class="box">
                        <h2 class="title is-2"><%= dvd.data()[NOM] %></h2>
                        <p>Compositeur : <%= dvd.data()[AUTEUR] %></p>
                        <p>Genre : <%= dvd.data()[GENRE_DVD] %></p>
                        <br/>
                        <form
                                id="delete-<%=dvd.data()[ID]%>"
                                action="${pageContext.request.contextPath}/documents?id=<%=dvd.data()[ID]%>&action=delete"
                                method="POST"
                        >
                            <button type="submit" form="delete-<%=dvd.data()[ID]%>" class="button is-danger">Supprimer</button>
                        </form>
                    </div>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
