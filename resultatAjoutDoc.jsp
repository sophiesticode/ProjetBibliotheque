
<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="mediatek2022.Document"%>

	<h1><%= (String) request.getAttribute("msg")%></h1>

	<div style="float:center;" class="boutonRetour">
		<a href="index.jsp">Retour</a>
	</div>
    
<%@ include file="footer.html"%>