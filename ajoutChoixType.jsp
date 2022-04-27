<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
Utilisateur userCo = (Utilisateur) request.getSession(true).getAttribute("user");
if(userCo == null){%>   <!-- si personne n'est connecté => connexion -->
     <% request.getRequestDispatcher("connexion.jsp").forward(request, response);%>
<%} else {%> 

<div class="content">
    <h1>Ajout de document</h1>
    <form action="./choixType" method="get" class="content cadre" id="cadre-ajoutDoc">
        <div>
            <label for="type">Type *</label>
            <select name="type" id="type">
                <option value="">--Choisissez un type--</option>
                <option value="1">CD</option>
                <option value="2">DVD</option>
                <option value="3">Livre</option>
            </select>
        </div>
        <input type="submit" value="Choisir"/>
    </form>
    <div class="message">
        <% String msg = (String) request.getAttribute("msg"); %>
        <% if (msg!=null && !msg.equals("")) { %>⚠️<%= request.getAttribute("msg")%><%}%>
    </div>
</div> 
<%}%>
<%@ include file="footer.html"%>
