<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="./CSS/index.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<title>Insertion des pannes</title>
	<style>
	
		*{
			z-index:3;
		}
		#loading{ 
			position:absolute;
			margin-left:30%;
			z-index:5;
		    animation:0.8s rotateRight infinite linear; 
		}
		
		@keyframes rotateRight{
		    0%{ transform:rotate(0deg); transform-origin:50% 50%; }
		    100%{ transform:rotate(360deg); }
		}
	</style>
</head>
<body>
	<div id="input">  
		<h1>Insertion des pannes</h1>  
	</div>
	
	<div class="divtransp">
		<a class ="round-button-circle" href="MonitoringServlet">Monitoring des pannes</a>
	</div>
	<img id="loading" style="display:none" src="http://static.boredpanda.com/blog/wp-content/uploads/2016/08/cute-kittens-69-57b32c431e8a7__605.jpg"/>
	

	
	<form name="inputForm" action="InputServlet" method="POST" onSubmit="loading()">	
		<input type="hidden" name="action" value="saisie"/>
		<div class="divtransp">
				<a class ="round-button-circle" id="formModeSaisie" onClick="formModeHandler(this.id)">Saisie d'une panne</a>
				<a class ="round-button-circle" id="formModeRandom" onClick="formModeHandler(this.id)">Génération aléatoire de panne</a>	
		</div>
		<div class="divtransp">
			<span id="formInputSaisie">
				<table style = "border-collapse: collapse;">
					<td align="left" width="43%">
						<span>Nom de la machine : </span>
						<input type="text" name="nom"/>
					</td>
					<td align="left" width="43%">
			       			 <span>Type de machine : </span><br/>
						<input type="radio" name="typeMachine" value="serveur" onClick="casPanneHandler()"><label for="Serveur">Serveur</label><br/>
						<input type="radio" name="typeMachine" value="pare-feu" onClick="casPanneHandler()"><label for="Pare-feu">Pare-feux</label><br/>
						<input type="radio" name="typeMachine" value="routeur" onClick="casPanneHandler()"><label for="Routeur">Routeur</label><br/>
					</td>
					<td align="left" width="43%">
						<span>Type de la panne du serveur : </span><br/>
						<input type="radio" name="typePanne" value="reseau"><label for="reseaux">Réseaux</label><br/>
			       			<input type="radio" name="typePanne" value="crash disque"><label for="crash disque">Crash disque</label><br/>
						<input type="radio" name="typePanne" value="problème mémoire"><label for="probleme memoire">Problème mémoire</label><br/>
					</td>
				</table>
			</span>
		    <span id="formInputRandom" style="display:none">
		    	<select name="modeAuto">
		    		<option value="Cascade" onClick="modeAutoHandler()"> Cascade </option>
		    		<option value="Rafale" onClick="modeAutoHandler()"> Rafale </option>
		    	</select>
			        	Nombre de pannes à générer :  
			        	<input name="qteRandom" type="number" value=1 > 
			        	Secondes entre les pannes : 
			        	<input name="delaiRandom" type="number" value=0 >
			</span>
		</div>
		<div class="divtransp">
			<div class ="round-button-circle" value="Valider" onClick="loading()"> Valider </div>
		</div>
	</form>
	
	
    
    
	<script src="./JS/input.js"></script>
</body>
</html>