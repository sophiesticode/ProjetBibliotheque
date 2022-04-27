
<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="mediatek2022.Document"%>
<% 
	Document docR=(Document) request.getAttribute("doc");
%>
    <% if(docR != null) {%>
		<h1>Document rendu !</h1>
		<%@ include file="vueDocument.jsp"%>
	<% } else { %>
		<h1>Aucun document portant ce numéro n'est retournable</h1>
	<% }%>

	<div style="float:center;" class="boutonRetour">
		<a href="index.jsp">Retour</a>
	</div>
    
<%@ include file="footer.html"%>