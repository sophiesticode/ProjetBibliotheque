<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <h1>Connexion</h1>
    <form action="./connexion" method="get" class="content cadre" id="cadre-connexion">
        <div>
            <label for="login">Login</label>
            <input type="text" name="login" id="login" />
        </div>
        <div>
            <label for="mdp">Password</label>
            <input type="password" name="mdp" id="mdp" />
        </div>
        <button>Valider</button>
        <div class="message">
            <% String msg = (String) request.getAttribute("msg"); %>
            <% if (msg!=null && !msg.equals("")) { %>⚠️<%= request.getAttribute("msg")%><%}%>
        </div>
    </form>
    
<%@ include file="footer.html"%>