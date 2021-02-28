<%--
  Created by IntelliJ IDEA.
  User: mr003
  Date: 26/02/2021
  Time: 09:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file='./parts/styles.jsp' %>
    <title>Mediatek login</title>
</head>
<body>
<section class="section">
    <div class='rows'>
        <div class='row is-full'>
            <div class='columns is-centered'>
                <div class='column is-half is-full-mobile'>
                    <h1 class="title"><%= "Mediatek login" %></h1>
                    <br/>
                    <div class="box">
                        <form action="${pageContext.request.contextPath}/login" method="POST">
                            <div class="field">
                                <label class="label">Username</label>
                                <div class="control">
                                    <input name="login" class="input" type="text" placeholder="Tibo18"/>
                                </div>
                                <!--<p class="help is-success">This username is available</p>-->
                            </div>
                            <div class="field">
                                <label class="label">Password</label>
                                <div class="control">
                                    <input name="password" class="input" type="password" placeholder="************"/>
                                </div>
                                <!--<p class="help is-success">This username is available</p>-->
                            </div>
                            <div class="field">
                                <div class="control">
                                    <label class="checkbox">
                                        <input type="checkbox">
                                        I agree to the <a href="#">terms and conditions</a>
                                    </label>
                                </div>
                            </div>
                            <div class="field is-grouped">
                                <div class="control">
                                    <button type="submit" class="button is-link">Submit</button>
                                </div>
                                <div class="control">
                                    <button class="button is-danger is-light" href="/index.jsp">Cancel</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
