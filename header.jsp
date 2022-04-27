<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="mediatek2022.Utilisateur"%>
<%
	Utilisateur userCoH=(Utilisateur) request.getSession(true).getAttribute("user");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mediatek</title>
    <link rel="stylesheet" href="style.css" type="text/css" />
</head>

<body>
<div style="float:left; text-align: center; position: fixed;" class="boutonRetour">
    <a href="index.jsp">Menu</a>
</div>

<% if(userCoH != null){ %>
    <div style="float:left;" id="info-user">
        <img src= "./images/ico-<%= (String) userCoH.getClass().getSimpleName() %>.png" alt="user"/>
        <h4><%= userCoH.name()%></h4>
        <a href="deconnexion">DECONNEXION</a>
    </div>
<% } %>

<div class="content" id="content-gen">