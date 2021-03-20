<%--
  Created by IntelliJ IDEA.
  User: mr003
  Date: 26/02/2021
  Time: 09:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String erreur_ajout = (String) request.getAttribute("erreur_ajout"); %>
<html>
<head>
    <%@ include file='parts/styles.jsp' %>
    <title>Mediatek login</title>
</head>
<body>
<section class="section">
    <div class='rows'>
        <div class='row is-full'>
            <div class='columns is-centered'>
                <div class='column is-half is-full-mobile'>
                    <h1 class="title"><%= "Mediatek ajouter DVD" %></h1>
                    <br/>
                    <div class="box">
                        <% if(erreur_ajout != null) {%>
                            <article class="message is-danger">
                                <div class="message-header">
                                    <p>L'ajout a échoué !</p>
                                </div>
                                <div class="message-body">
                                    <%= erreur_ajout %>
                                </div>
                            </article>
                        <% } %>
                        <form id="ajout-form" action="${pageContext.request.contextPath}/ajouter_dvd" method="POST">
                            <div class="field">
                                <label class="label">Nom</label>
                                <div class="control">
                                    <input name="nom" class="input" type="text" placeholder=""/>
                                </div>
                            </div>
                            <div class="field">
                                <label class="label">Réalisateur</label>
                                <div class="control">
                                    <input name="auteur" class="input" type="text" placeholder=""/>
                                </div>
                            </div>
                            <div class="field">
                                <label class="label">Genre</label>
                                <div class="control">
                                    <select name="genre" class="select">
                                        <option value="HORROR">Horror</option>
                                        <option value="ROMANTIC">Romantic</option>
                                        <option value="COMEDY">Comedy</option>
                                        <option value="SCIENCE_FICTION">Science-fiction</option>
                                    </select>
                                </div>
                            </div>
                            <div class="field">
                                <label class="label">Est pour adulte ?</label>
                                <div class="control">
                                    <input type="checkbox" class="checkbox" name="est_adulte" value="true">
                                </div>
                            </div>
                        </form>
                        <div class="field is-grouped">
                            <div class="control">
                                <button type="submit" form="ajout-form" class="button is-link">Ajouter</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
