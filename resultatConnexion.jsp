<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="mediatek2022.Utilisateur"%>
<% 
	Utilisateur userCoC=(Utilisateur) request.getSession(true).getAttribute("user");
	String messageRCo; 

	if (userCoC != null) {
		messageRCo="Bonjour <strong style='text-transform: capitalize;'>" 
					+ userCoC.name() + "</strong>, que voulez-vous faire ?" ; 
	} else {
		messageRCo="L'utilisateur n'a pas été trouvé" ; 
		session.setAttribute("user", null);
	}
%>
    <h1>
		<%= messageRCo%>
	</h1>
	<% if(userCoC != null) {%>
		<ul class="content cadre" id="menu">
		<% if(userCoC.getClass().getSimpleName().equals("Abonne")){ %> <!-- si abonné -->
			<li><a href="vitrine">Emprunter un document</a></li>
			<li><a href="retourDoc.jsp">Retourner un document</a></li>
		<% } else {%> 											<!-- sinon : bibliothecaire -->
			<li><a href="ajoutChoixType.jsp">Ajouter un document</a></li>
		<% }%>
		</ul>
	<% }%>
    
<%@ include file="footer.html"%>