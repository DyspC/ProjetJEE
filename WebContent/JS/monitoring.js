// Initialisation des varibles utilisées
var autorefresh = 60000;
d = new Date();
dChoix="rien";
dOrdre="not";
asc = "asc";
BackgroundOffsetH = 0;
dateB=d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate()+" ";
var config = {
    type: 'line',
    data: {
        labels: ["0:00", "1:00", "2:00", "3:00", "4:00", "5:00", "6:00", "7:00", "8:00", "9:00", "10:00",
                 "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00",
                 "21:00", "22:00", "23:00"],
        datasets: []
    },
    options: {
        responsive: true,
        title:{
            display:true,
            text:'Historique des pannes'
        },
        tooltips: {
            mode: 'index',
            intersect: false,
        },
        hover: {
            mode: 'nearest',
            intersect: true
        },
        scales: {
            xAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: 'Heure'
                }
            }],
            yAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: 'Nombre'
                }
            }]
        }
    }
};
var ctx = document.getElementById("myChart").getContext("2d");
myChart = new Chart(ctx, config);

//Création des fonctions js que nous allons utiliser

// À l'ouverture de la page, on met la variable de navigateur initialisée à "rien"

// Permet le rafraichissement automatique de la page 
function timer(){
	refresh();
	window.setTimeout(timer, autorefresh);		
}

// On simule un click sur le dernier bouton utilisé 
function refresh(){
	AGet(dChoix,dOrdre);
}

// On enregistre l'id du dernier bouton sur lequel on a cliqué, afin de refresh la page à partir de celui-ci
function dernierChoix(id){
	dChoix=id;
	dOrdre="not";
}

function switchOrder(){
	if(asc=="asc"){
		asc="desc";
	}
	else{
		asc="asc";
	}	
}
// Génère une couleur aléatoire pour le graph
function getRandomColor() {
    var letters = '0123456789ABCDEF'.split('');
    var color = '#';
    for (var i = 0; i < 6; i++ ) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

function AGet(id, order){
	$.get("AjaxServlet", { type : id, order : order, asc : asc},function(response){
		$("#tabPan").html(response.split("|")[0]);
		$("#nbpanMin").html(response.split("|")[1]);
		$("#nbpanH").html(response.split("|")[2]);
		$("#nbpanD").html(response.split("|")[3]);
		$("#nbpanMon").html(response.split("|")[4]);
		$(".cbox").click(function(event) { // Ici on ajoute dynamiquement une fonction a chaque checkbox que l'on a ajoutée dynamiquement 
			$.get("AjaxServlet", { upd : event.target.id},function(response){
				refresh();
			});
		});
		$(".order").click(function(event){ 
			dOrdre=event.target.id;
			AGet(dChoix, event.target.id);
			switchOrder();
		});
	});
}

// Partie JQuery

$("#tabPan").hide();
// Lorsqu'on click sur un élément avec la classe panneReq, on récupère son id et on l'envoie avec ajax pour récuperer le détail des pannes du bouton
// sur lequel on a cliqué (sauf si c'est le bouton rien, qui efface le tableau). On en profite pour actualiser le nombre de panne
$(".panneReq").click(function(event) {
	asc="asc";
	$("#tabPan").show();
	$("#divGraph").hide();
	AGet(event.target.id, "not");
});

$("#dispGraph").click(function(){
	$('#divGraph').show();
	$("#tabPan").hide();
});

// On attribue la fonction refresh quand on clique sur l'objet html avec l'id refresh2
$("#refresh2").click(refresh);

// Permet d'ajouter un dataset au graphique, on prend comme date la date courante, sauf si une date est fournie dans les champs disponible
$("#addGraph").click(function() {
	if($('#an').val()!="" && $('#mois').val()!="" && $('#jr').val()!=""){
		dateU = $('#an').val().trim() +"-"+$('#mois').val().trim()+"-"+$('#jr').val().trim()+" ";
	}
	else{
		dateU=dateB;
	}
	if(isNaN($('#an').val()) || isNaN($('#mois').val()) || isNaN($('#jr').val())){
		dateU=dateB;
	}
	dateH=dateU;
	myChart.update();
	deja = false;
	for(j = 0;j<config.data.datasets.length;j++){
		if(config.data.datasets[j].label=='Panne du '+dateH){
			deja=true;
		}    		
	}
	if(!deja){
		$.get("AjaxServlet", { date : dateH},function(response){
            var newColor = getRandomColor();
            var newDataset = {
                label: 'Panne du '+dateH,
                backgroundColor: newColor,
                borderColor: newColor,
                data: [],
                fill: false
            };

			for(var i=0; i<24;i++){
				newDataset.data.push(parseInt(response.split("/")[i]));
			}
            config.data.datasets.push(newDataset);
	    	myChart.update();				
		});
	}
});

// On supprime tout les dataset
$("#delGraph").click(function(){
	config.data.datasets=[];
	myChart.update();
});

Chart.pluginService.register({
    beforeDraw: function (chart, easing) {
        if (chart.config.options.chartArea && chart.config.options.chartArea.backgroundColor) {
            var ctx = chart.chart.ctx;
            var chartArea = chart.chartArea;

            ctx.save();
            ctx.fillStyle = chart.config.options.chartArea.backgroundColor;
            ctx.fillRect(chartArea.left, chartArea.top, chartArea.right - chartArea.left, chartArea.bottom - chartArea.top);
            ctx.restore();
        }
    }
});
rotation=0;BackgroundOffsetV=0;
jQuery.fn.rotate = function(degrees) {
    $(this).css({'transform' : 'rotate('+ degrees +'deg)'});
    return $(this);
};
function defilementBody() {
	"use strict"; 
	if(document.body.getAttribute("class")=="slow"){
		rotation+=4;
		$(".slow").rotate(rotation);
	}
	BackgroundOffsetH += (document.body.getAttribute("class")=="slow")?(Math.floor(2*Math.random())*2-1):1;
	BackgroundOffsetV += (document.body.getAttribute("class")=="slow")?(Math.floor(2*Math.random())*2-1):1;
	document.body.style.backgroundPosition =  BackgroundOffsetV + "px " + BackgroundOffsetH +"px";
	window.setTimeout(defilementBody,10);
}
if ( window.addEventListener ) {
    var kkeys = [], konami = "38,38,40,40,37,39,37,39,66,65";
    window.addEventListener("keydown", function(e){
        kkeys.push( e.keyCode );
        if ( kkeys.toString().indexOf( konami ) >= 0 ) {
        	if(document.body.getAttribute("class")=="slow"){
        		document.body.setAttribute("class", "");
        	}
        	else{
        		document.body.setAttribute("class", "slow");
        	}
			setTimeout(mdrmdr,500);
            kkeys = [];
        }
    }, true);
}
function mdrmdr(){
	var lolu = document.getElementsByClassName("panneReq");
	for(i=0;i<lolu.length;i++){
		lolu[i].innerHTML = "(ノಠ益ಠ)ノ彡┻━┻ 	\n ¯\\_(ツ)_/¯ ¯\\_(ツ)_/¯ ¯\\_(ツ)_/¯ 	\n 纏流子は一番のワイフです。  \n	ｶﾜ(・∀・)ｲｲ!!";
	}
}
window.setTimeout(timer, autorefresh);
defilementBody();