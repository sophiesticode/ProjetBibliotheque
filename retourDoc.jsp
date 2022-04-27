<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="header.jsp"%>
<% 
Utilisateur userCo = (Utilisateur) request.getSession(true).getAttribute("user");
if(userCo == null){%>   <!-- si personne n'est connecté => connexion -->
     <% request.getRequestDispatcher("connexion.jsp").forward(request, response);%>
<%} else {%> 
    <h1>Rendre un document</h1>
    <form action="./retourDoc" method="get">
        <div>
            <label for="numDoc">Numéro</label>
            <input type="text" name="numDoc" id="numDoc" />
        </div>
        <button>valider</button>
        
    </form>

    <div class="message">
        <% String msgRetour = (String) request.getAttribute("msg"); %>
        <% if ((msgRetour!=null) && (!msgRetour.equals(""))) { %><%= msgRetour%><%}%>
    </div>
<% }%>    
<%@ include file="footer.html"%>