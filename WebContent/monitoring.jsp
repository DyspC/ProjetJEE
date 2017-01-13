<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.List,base.metier.Panne"%>
    <%
    Integer panneminute = (Integer)request.getAttribute("PanneMinute");
    Integer panneheure = (Integer)request.getAttribute("PanneHeure");
    Integer pannejour = (Integer)request.getAttribute("PanneJour");
    Integer pannemois = (Integer)request.getAttribute("PanneMois");
//     List<Panne> listPanne = (List<Panne>)request.getAttribute("listPanne");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="./JS/Chart.bundle.min.js"></script>
<link rel="stylesheet" type="text/css" href="./CSS/index.css">
<title>Monitoring des pannes</title>
<style>
		
		@keyframes rotateRight{
		    0%{ transform:rotate(0deg); transform-origin:50% 180%; }
		    100%{ transform:rotate(360deg); }
		}
	</style>
</head>
<body>
		<div id="monitoring">  
		<h1>Monitoring des pannes</h1>  
	</div>
	<div id="case1">
		<a class ="round-button-circle" href="InputServlet">Insertion de pannes</a>
		<a class ="round-button-circle" id="refresh2">Mettre à jour la page</a>  
	</div>	
	<div id="case2">
		<a onClick="dernierChoix(this.id)" class="panneReq round-button-circle" id="minute">Détails des pannes (minute)</a>
		<a onClick="dernierChoix(this.id)" class="panneReq round-button-circle" id="hour">Détails des pannes (heure)</a>	
		<a onClick="dernierChoix(this.id)" class="panneReq round-button-circle" id="day">Détails des pannes (jour)</a>	
		<a onClick="dernierChoix(this.id)" class="panneReq round-button-circle" id="month">Détails des pannes (mois)</a>	
		<a class="panneReq round-button-circle" id="dispGraph">Afficher le graphe</a>	
		
	</div><div id="case3">
		<p>Pannes enregistrées la dernière minute</p>
		<p id="nbpanMin" ><%=panneminute %></p><br/>
		<p>Pannes enregistrées la dernière heure</p>
		<p id="nbpanH" ><%=panneheure %></p><br/>
		<p>Pannes enregistrées la dernière jour</p>
		<p id="nbpanD" ><%=pannejour %></p><br/>
		<p>Pannes enregistrées la dernière mois</p>
		<p id="nbpanMon" ><%=pannemois %></p><br/>
	</div>
	<div id = "case4">
		<div id="tabPan" scrolling="yes">
	
		</div>
		<div id= "divGraph">
			Année : <input id = "an" type="text"/> 
			Mois : <input id="mois" type="text"/> 
			Jour : <input id="jr" type="text"/>
		
			<a class ="round-button-circle graphe" id="addGraph">Ajouter ce jour</a>
			<a class ="round-button-circle graphe" id="delGraph">Supprimer les jours insérés</a> 
			<canvas id="myChart"></canvas>		
		</div>
	</div>
</body>
<script src="./JS/monitoring.js"></script>
</html>