<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List"%>
<%
Utilisateur userCo = (Utilisateur) request.getSession(true).getAttribute("user");

if(userCo == null){%>   <!-- si personne n'est connecté => connexion -->
     <% request.getRequestDispatcher("connexion.jsp").forward(request, response);%>
<%} else {%> 

<div class="content">
    <h1>Ajout de CD</h1>
    <form action="./ajoutDoc_CD" method="get" class="content cadre" id="cadre-ajoutDoc">
        <input type="hidden" name="type" value="<%= request.getParameter("type")%>">

        <div>
            <label for="titre">Titre</label>
            <input type="text" name="titre" id="titre" value='<%= (request.getParameter("titre")==null?"":request.getParameter("titre"))%>'/>
        </div>
        <div>
            <label for="de">Artiste</label>
            <input type="text" name="de" id="de" value='<%= (request.getParameter("de")==null?"":request.getParameter("de"))%>'/>
        </div>
        <div>
            <label for="categorie">Catégorie</label>
            <input type="text" name="categorie" id="categorie" value='<%= (request.getParameter("categorie")==null?"":request.getParameter("categorie"))%>'/>
        </div>
        <div>
            <label for="anneeParution">Année de parution</label>
            <input type="text" name="anneeParution" id="anneeParution" value='<%= (request.getParameter("anneeParution")==null?"":request.getParameter("anneeParution"))%>'/>
        </div>
        <div>
            <label for="nbMorceaux">Nombre de morceaux</label>
            <input type="text" name="nbMorceaux" id="nbMorceaux" value='<%= (request.getParameter("nbMorceaux")==null?"":request.getParameter("nbMorceaux"))%>'/>
        </div>
        <input type="submit" value="Ajouter"/>
    </form>
    <div class="message">
        <% String msg = (String) request.getAttribute("msg"); %>
        <% if (msg!=null && !msg.equals("")) { %>⚠️<%= request.getAttribute("msg")%><%}%>
    </div>
</div> 
<%}%>
<%@ include file="footer.html"%>
