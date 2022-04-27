<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="mediatek2022.Utilisateur"%>
<% 
Utilisateur userCo = (Utilisateur) request.getSession(true).getAttribute("user");
if(userCo == null){%>   <!-- si personne n'est connecté => connexion -->
    <%@ include file="connexion.jsp"%>
<%} else {%>             <!-- sinon accueil -->
    <%@ include file="resultatConnexion.jsp"%>
<%}%>
