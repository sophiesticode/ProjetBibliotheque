
<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List"%>
<%@ page import="mediatek2022.Document"%>

<% 
Utilisateur userCo = (Utilisateur) request.getSession(true).getAttribute("user");
if(userCo == null){%>   <!-- si personne n'est connecté => connexion -->
    <% request.getRequestDispatcher("connexion.jsp").forward(request, response);%>
<%} else {%> 
<%
	List<Document> listeDocsDispo=(List<Document>) request.getAttribute("listeDocs");
%>
	<h1>Documents Disponibles</h1>

    <% if(listeDocsDispo != null) {
		for(Document d : listeDocsDispo) { 
			request.setAttribute("doc", d);%>
			<hr/>
			<form action="./empruntDoc" method="get">
				<%@ include file="vueDocument.jsp"%>
				<input type="hidden" name="numDoc" value="<%= listeDocsDispo.indexOf(d)%>">
				<div style="text-align: center;">
					<input type="submit" value="emprunter" style="align: center; font-size: 3em;"/>
				</div>
			</form>
	<% }} else { %>
		<h1>Aucun document ne correspond à votre recherche</h1>
	<% }%>
<%}%>
    
<%@ include file="footer.html"%>

	