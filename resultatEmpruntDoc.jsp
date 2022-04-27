
<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="mediatek2022.Document"%>
<% 
	String messageREmprunt = (String) request.getAttribute("msg");
	Document docE=(Document) request.getAttribute("doc");
%>

    <% if((docE != null) && (messageREmprunt == null)) {%>
		<h1>Document emprunté !</h1>
		<%@ include file="vueDocument.jsp"%>
	<% } else { %>
		<h1><%= messageREmprunt%></h1>
	<% }%>

	<div style="float:center;" class="boutonRetour">
		<a href="index.jsp">Retour</a>
	</div>
    
<%@ include file="footer.html"%>