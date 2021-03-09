<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file='parts/styles.jsp' %>
    <title>Mediatek menu</title>
</head>
<body>
<section class="section">
    <div class='rows'>
        <div class='row is-full'>
            <div class='columns is-centered'>
                <div class='column is-half is-full-mobile'>
                    <h1 class="title"><%= "Mediatek menu" %></h1>
                    <br/>
                    <div class="box">
                        <a class="button" href="login">login</a>
                        <a class="button" href="about.jsp">about</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>